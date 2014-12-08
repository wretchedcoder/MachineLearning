/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.algorithms;

import edu.data.AlgorithmResults;
import edu.data.DataSet;
import edu.data.Pattern;
import edu.kernel.IKernel;
import edu.kernel.KernelBuilder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lee
 */
public class KernelKMeansAlgorithm implements AlgorithmInterface
{
    public static KernelKMeansAlgorithm getKernelKMeansAlgorithm()
    {
        return new KernelKMeansAlgorithm();
    }
    
    public KernelKMeansAlgorithm()
    {
        
    }
    
    @Override
    public AlgorithmResults executeAlgorithm(DataSet dataSet, HttpServletRequest request, HttpServletResponse response) 
    {
        // Get All Kernel K-Means Options
        int clusters = Integer.parseInt(request.getParameter("kernelKMmeansClusterOption"));
        long maxWaitTime = Long.parseLong(request.getParameter("kernelKMeansMaxTimeOption"));
        String kernelOpt = request.getParameter("kernelKMmeansKernel");
        maxWaitTime *= 1000;
        
        int d = dataSet.getPatterns().size();
        
        // Time Variables
        long startTime = System.currentTimeMillis();
        long stopTime = 0L;
        long elapsedTime = 0L;    
        
        // Initialize Centroids
        Pattern[] centroids = new Pattern[clusters];
        DataSet[] featureRegions = new DataSet[clusters];
        DataSet[] voronoiRegions = new DataSet[clusters];
        for (int i = 0; i < centroids.length; i++)
        {
            centroids[i] = dataSet.getPattern(i);
        }
        
        // Get Variance for Gaussian Kernel
        double variance = 0.00;
        if (kernelOpt.equals("gaussian"))
        {
            Pattern meanPattern = Pattern.getPattern(d);            
            meanPattern = AlgorithmUtil.getMeanForDataSet(dataSet);
            variance = AlgorithmUtil.getVarianceForDataSet(meanPattern, dataSet);
        }
        
        // Get Kernel
        request.setAttribute("variance", variance);
        IKernel kernel = KernelBuilder.getKernel(kernelOpt, variance);
        
        int iterations = 0;
        boolean centroidsChanged = false;
        boolean firstPass = true;
        while (elapsedTime <= maxWaitTime
                && !centroidsChanged) 
        {
            // Initialize or Clear Regions
            for (int i = 0; i < centroids.length; i++)
            {
                featureRegions[i] = null;
                featureRegions[i] = DataSet.getDataSet();
                voronoiRegions[i] = null;
                voronoiRegions[i] = DataSet.getDataSet();
            }           
            
            centroidsChanged = false;
            for (int i = 0; i < dataSet.getPatterns().size(); i++)
            {
                int minCluster = -1;
                double minDistance = 0.0;
                double thisDistance = 0.0;
                for (int j = 0; j < centroids.length; j++)
                {
                    thisDistance = AlgorithmUtil.getKernelDistance(kernel, 
                            dataSet.getPattern(i), centroids[j]);                    
                    
                    if (minCluster == -1
                            || thisDistance < minDistance)
                    {
                        minDistance = thisDistance;
                        minCluster = j;
                    }                    
                }
                featureRegions[minCluster].addPattern(dataSet.getPattern(i));
                minCluster = -1;
                minDistance = 0.0;
                thisDistance = 0.0;
                for (int j = 0; j < centroids.length; j++)
                {
                    thisDistance = AlgorithmUtil.getDistanceInFeatureSpace(kernel, 
                          dataSet, featureRegions, centroids, j);
                    if (minCluster == -1
                            || thisDistance < minDistance)
                    {
                        minDistance = thisDistance;
                        minCluster = j;
                    }
                }
                voronoiRegions[minCluster].addPattern(dataSet.getPattern(i));
            } // assign patterns to regions loop
            
            // Calculate New Centroids
            for (int i = 0; i < centroids.length; i++)
            {
                Pattern newCentroid = Pattern.getPattern();
                newCentroid.initAttributes(centroids[0].getAttributes().size());
                for (int j = 0; j < voronoiRegions[i].getPatterns().size(); j++)
                {
                    newCentroid = AlgorithmUtil.addPatterns(newCentroid, voronoiRegions[i].getPattern(j));
                }
                newCentroid = AlgorithmUtil.divide(newCentroid, voronoiRegions[i].getPatterns().size());
                if (!newCentroid.equals(centroids[i]))
                {
                    centroids[i] = newCentroid;
                    if(!firstPass)
                    {
                        centroidsChanged = true;
                    }
                }                
            } // calculate new centroids loop              
            firstPass = false;
            elapsedTime = System.currentTimeMillis() - startTime;  
            iterations++;
        } // while
        
        AlgorithmResults algResults = new AlgorithmResults();
        algResults.setAlgorithmName("Kernel K-Means Results");
        algResults.setAlgorithmId("kernelKMeans");     
        algResults.setRegions(voronoiRegions);
        algResults.setCentroids(centroids);
        
        return algResults;
    }
}

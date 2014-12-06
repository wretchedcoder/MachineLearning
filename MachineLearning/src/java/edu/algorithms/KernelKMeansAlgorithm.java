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
        DataSet[] regions = new DataSet[clusters];
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
        IKernel kernel = KernelBuilder.getKernel(request);
        
        int iterations = 0;
        boolean centroidsChanged = false;
        while (elapsedTime <= maxWaitTime
                && !centroidsChanged) 
        {
            // Initialize or Clear Regions
            for (int i = 0; i < centroids.length; i++)
            {
                regions[i] = null;
                regions[i] = DataSet.getDataSet();
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
                regions[minCluster].addPattern(dataSet.getPattern(i));
            } // assign patterns to regions loop
            
            for (int i = 0; i < centroids.length; i++)
            {
                Pattern newCentroid = Pattern.getPattern();
                newCentroid.initAttributes(centroids[0].getAttributes().size());
                for (int j = 0; j < regions[i].getPatterns().size(); j++)
                {
                    newCentroid = AlgorithmUtil.addPatterns(newCentroid, regions[i].getPattern(j));
                }
                newCentroid = AlgorithmUtil.divide(newCentroid, regions[i].getPatterns().size());
                if (!newCentroid.equals(centroids[i]))
                {
                    centroids[i] = newCentroid;
                    centroidsChanged = true;
                }
            } // calculate new centroids loop              
            
            elapsedTime = System.currentTimeMillis() - startTime;  
            iterations++;
        } // while
        
        AlgorithmResults algResults = new AlgorithmResults();
        algResults.setAlgorithmName("Kernel K-Means Results");
        algResults.addItem("Dimensions", Integer.toString(centroids[0].getDimensionality()));
        algResults.addItem("Iterations", Integer.toString(iterations));
        algResults.addItem("Elapsed Time", Double.toString(elapsedTime / 1000.0));        
        algResults.setRegions(regions);
        algResults.setCentroids(centroids);
        
        return algResults;
    }
}

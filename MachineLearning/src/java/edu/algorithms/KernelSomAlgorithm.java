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
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lee
 */
public class KernelSomAlgorithm implements AlgorithmInterface 
{
    private static Random random;
    private double minSigma = 1.00;
    private double maxSigma = 1.00;
    private double minEpsilon = 1.00;
    private double maxEpsilon = 1.00;
    private long maxTimeStep = 1;
    
    static
    {
        random = new Random();
    }
    
    public static KernelSomAlgorithm getKernelSomAlgorithm()
    {
        return new KernelSomAlgorithm();
    }
    
    @Override
    public AlgorithmResults executeAlgorithm(DataSet dataSet, 
            HttpServletRequest request, HttpServletResponse response) {
        // Get All Options
        int clusters = Integer.parseInt(request.getParameter("kernelSomClusterOption"));
        long maxWaitTime = Long.parseLong(request.getParameter("kernelSomMaxTimeOption"));
        maxWaitTime *= 1000;
        this.maxTimeStep = Integer.parseInt(request.getParameter("kernelSomMaxTimeStep"));
        this.minEpsilon = Double.parseDouble(request.getParameter("kernelSomMinEpsilon"));
        this.maxEpsilon = Double.parseDouble(request.getParameter("kernelSomMaxEpsilon"));
        this.minSigma = Double.parseDouble(request.getParameter("kernelSomMinSigma"));
        this.maxSigma = Double.parseDouble(request.getParameter("kernelSomMaxSigma"));
        String kernelOpt = request.getParameter("kernelSomKernel");
        
        int d = dataSet.getPatterns().size();
        
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
        
        // Initialize t
        int t = 0;
        
        while (t < maxTimeStep)
        {
            // Get Random Pattern
            int x = random.nextInt(dataSet.getPatterns().size());
            
            // Initialize or Clear Regions
            for (int i = 0; i < centroids.length; i++)
            {
                featureRegions[i] = null;
                featureRegions[i] = DataSet.getDataSet();
                voronoiRegions[i] = null;
                voronoiRegions[i] = DataSet.getDataSet();
            }           
            
            int minCluster = -1;
            for (int i = 0; i < dataSet.getPatterns().size(); i++)
            {
                minCluster = -1;
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
                        
            int closestIdx = AlgorithmUtil.getRegion(dataSet.getPattern(x), voronoiRegions);
                        
            // Get Closest Centroid
            Pattern closestCentroid = 
                    AlgorithmUtil.getClosestCentroidInFeatureSpace(kernel, dataSet.getPattern(x), 
                            centroids, dataSet, voronoiRegions, closestIdx);
            
            // Adapt each codevector
            Pattern delta = Pattern.getPattern(d);
            for (int i = 0; i < clusters; i++)
            {
                delta = AlgorithmUtil.subtract(
                        dataSet.getPattern(x), centroids[i]);
                delta = AlgorithmUtil.multiply(delta, 
                        this.h(closestCentroid, dataSet.getPattern(x),t));
                delta = AlgorithmUtil.multiply(delta, 
                        this.epsilon(t));
                centroids[i] = AlgorithmUtil.subtract(centroids[i], delta);
            }
            
            // Increment t
            t++;
        }
        
        // Output Results
        AlgorithmResults algResults = new AlgorithmResults();
        algResults.setAlgorithmName("Kernel SOM Results");
        algResults.setAlgorithmId("kernelSom");
        algResults.setRegions(voronoiRegions);
        algResults.setCentroids(centroids);
        return algResults;
    }
    
    private double h(Pattern r, Pattern s, int t)
    {
        double top = AlgorithmUtil.getManhattanDistance(r, s);
        top = Math.pow(top, 2.00);
        
        double bottom = 2 * Math.pow(this.sigma(t), 2.00);
        
        top = top / bottom;
        top *= -1.00;
        
        double value = Math.exp(top);              
        return value;
    }
    
    private double epsilon(int t)
    {
        double value = 1.00;
        
        value = minEpsilon * Math.pow((maxEpsilon / minEpsilon), ((double)t / (double)this.maxTimeStep));
        
        return value;
    }
    
    private double sigma(int t)
    {
        double value = 1.00;
        
        value = minSigma * Math.pow((maxSigma / minSigma), ((double)t / (double)this.maxTimeStep));
        
        return value;
    }
}

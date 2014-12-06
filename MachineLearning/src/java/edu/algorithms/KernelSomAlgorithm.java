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
        
        // Initialize Centroids Codebook
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
        
        // Initialize t
        int t = 0;
        
        while (t < maxTimeStep)
        {
            // Get Random Pattern
            int x = random.nextInt(dataSet.getPatterns().size());
            
            // Get Closest Centroid
            Pattern closestCentroid = AlgorithmUtil.getKernelClosestCentroid(
                    kernel, dataSet.getPattern(x), centroids);
            
            // Adapt each codevector
            for (int i = 0; i < clusters; i++)
            {
                centroids[i] = AlgorithmUtil.subtract(
                        dataSet.getPattern(x), centroids[i]);
                centroids[i] = AlgorithmUtil.multiply(centroids[i], 
                        this.h(closestCentroid, dataSet.getPattern(x),t));
                centroids[i] = AlgorithmUtil.multiply(centroids[i], 
                        this.epsilon(t));
            }
            
            // Increment t
            t++;
        }
        
        for (int i = 0; i < centroids.length; i++)
        {
            regions[i] = DataSet.getDataSet();
        }

        for (int i = 0; i < dataSet.getPatterns().size(); i++)
        {
            int index = AlgorithmUtil.getClosestCentroidIndex(1.00, 
                dataSet.getPattern(i), centroids);
            regions[index].addPattern(dataSet.getPattern(i));
        }
        
        // Output Results
        AlgorithmResults algResults = new AlgorithmResults();
        algResults.setAlgorithmName("Kernel SOM Results");
        algResults.setRegions(regions);
        algResults.setCentroids(centroids);
        return algResults;
    }
    
    private double sigma(int t)
    {
        double value = 1.00;
        if (t == 0)
        {
            return minSigma;
        }
        
        value = minSigma * Math.pow((maxSigma / minSigma), (t / this.maxTimeStep));
        
        return value;
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
        if (t == 0)
        {
            return minEpsilon;
        }
        
        value = minEpsilon * Math.pow((maxEpsilon / minEpsilon), (t / this.maxTimeStep));
        
        return value;
    }
}

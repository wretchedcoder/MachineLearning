/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.algorithms;

import edu.data.AlgorithmResults;
import edu.data.DataSet;
import edu.data.Pattern;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lee
 */
public class SomAlgorithm implements AlgorithmInterface 
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
    
    public static SomAlgorithm getSomAlgorithm()
    {
        return new SomAlgorithm();
    }
    
    @Override
    public AlgorithmResults executeAlgorithm(DataSet dataSet, 
            HttpServletRequest request, HttpServletResponse response) {
        // Get All Options
        int clusters = Integer.parseInt(request.getParameter("somClusterOption"));
        long maxWaitTime = Long.parseLong(request.getParameter("somMaxTimeOption"));
        maxWaitTime *= 1000;
        this.maxTimeStep = Integer.parseInt(request.getParameter("somMaxTimeStep"));
        this.minEpsilon = Double.parseDouble(request.getParameter("somMinEpsilon"));
        this.maxEpsilon = Double.parseDouble(request.getParameter("somMaxEpsilon"));
        this.minSigma = Double.parseDouble(request.getParameter("somMinSigma"));
        this.maxSigma = Double.parseDouble(request.getParameter("somMaxSigma"));
                
        // Time Variables
        long startTime = System.currentTimeMillis();
        long stopTime = 0L;
        long elapsedTime = 0L;
        
        // Get Dimensionality
        int d = dataSet.getPattern(0).getDimensionality();
        
        // Initialize Centroids Codebook
        Pattern[] centroids = new Pattern[clusters];
        DataSet[] regions = new DataSet[clusters];
        for (int i = 0; i < centroids.length; i++)
        {
            centroids[i] = dataSet.getPattern(i).copy();
        }
        
        // Initialize t
        int t = 0;
        
        while (t < maxTimeStep)
        {
            // Get Random Pattern
            int x = random.nextInt(dataSet.getPatterns().size());
            
            // Get Closest Centroid
            Pattern closestCentroid = AlgorithmUtil.getClosestCentroid(2.00, 
                    dataSet.getPattern(x), centroids);
            
            // Adapt each codevector
            Pattern delta = Pattern.getPattern(d);
            for (int i = 0; i < clusters; i++)
            {                
                delta = AlgorithmUtil.subtract(
                        dataSet.getPattern(x), centroids[i]);
                delta = AlgorithmUtil.multiply(delta, 
                        this.h(dataSet.getPattern(x), centroids[i],t));
                delta = AlgorithmUtil.multiply(delta, 
                        this.epsilon(t));
                centroids[i] = AlgorithmUtil.subtract(centroids[i], delta);
            }
            
            // Increment t and Elapsed Time
            elapsedTime = System.currentTimeMillis() - startTime;
            t++;
        }
        
        for (int i = 0; i < centroids.length; i++)
        {
            regions[i] = DataSet.getDataSet();
        }

        for (int i = 0; i < dataSet.getPatterns().size(); i++)
        {
            int index = AlgorithmUtil.getClosestCentroidIndex(2.00, 
                dataSet.getPattern(i), centroids);
            regions[index].addPattern(dataSet.getPattern(i));
        }
        
        // Output Results
        AlgorithmResults algResults = new AlgorithmResults();
        algResults.setAlgorithmName("SOM Results");
        algResults.setAlgorithmId("som");
        algResults.setRegions(regions);
        algResults.setCentroids(centroids);
        return algResults;
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
    
}

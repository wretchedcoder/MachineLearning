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
    private final double minSigma = 1.00;
    private final double minEpsilon = 1.00;
    
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
        int maxTimeStep = Integer.parseInt(request.getParameter("somMaxTimeStep"));
        maxWaitTime *= 1000;        
        
        // Initialize Centroids Codebook
        Pattern[] centroids = new Pattern[clusters];
        DataSet[] regions = new DataSet[clusters];
        for (int i = 0; i < centroids.length; i++)
        {
            centroids[i] = dataSet.getPattern(i);
        }
        
        // Initialize t
        int t = 0;
        
        while (t < maxTimeStep)
        {
            // Get Random Pattern
            int x = random.nextInt(dataSet.getPatterns().size());
            
            // Get Closest Centroid
            Pattern closestCentroid = AlgorithmUtil.getClosestCentroid(1.00, 
                    dataSet.getPattern(x), centroids);
            
            // Adapt each codevector
            for (int i = 0; i < clusters; i++)
            {
                centroids[i] = AlgorithmUtil.subtract(
                        dataSet.getPattern(x), centroids[i]);
                centroids[i] = AlgorithmUtil.multiply(centroids[i], 
                        this.h(closestCentroid, dataSet.getPattern(x)));
                centroids[i] = AlgorithmUtil.multiply(centroids[i], 
                        this.sigma(t));
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
        AlgorithmResults results = new AlgorithmResults();
        return results;
    }
    
    private double epsilon(int t)
    {
        double value = 1.00;
        if (t == 0)
        {
            return minEpsilon;
        }
        
        
        
        return value;
    }
    
    private double sigma(int t)
    {
        double value = 1.00;
        if (t == 0)
        {
            return minSigma;
        }
        
        return value;
    }
    
    private double h(Pattern r, Pattern s)
    {
        double value = 1.00;

        
        return value;
    }
    
}

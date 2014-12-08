/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.algorithms;

import edu.data.AlgorithmResults;
import edu.data.DataSet;
import edu.data.Pattern;
import java.util.ArrayList;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lee
 */
public class PossFuzzyCMeansAlgorithm implements AlgorithmInterface
{
    private static Random random;
    
    static
    {
        random = new Random();
    }
    
    private double l = 1.00;
    
    public static PossFuzzyCMeansAlgorithm getProbFuzzyCMeansAlgorithm()
    {
        return new PossFuzzyCMeansAlgorithm();
    }
    
    private PossFuzzyCMeansAlgorithm()
    {
        
    }
    
    @Override
    public AlgorithmResults executeAlgorithm(DataSet dataSet, 
            HttpServletRequest request, HttpServletResponse response) 
    {
        // Get All Options
        int clusters = Integer.parseInt(request.getParameter("probFuzzyCMeansClusterOption"));
        long maxWaitTime = Long.parseLong(request.getParameter("probFuzzyCMeansMaxTimeOption"));
        double m = Double.parseDouble(request.getParameter("probFuzzyCMeansMembership"));
        this.l = 1.00;
        maxWaitTime *= 1000;
        
        // Time Variables
        long startTime = System.currentTimeMillis();
        long stopTime = 0L;
        long elapsedTime = 0L;
        
        int n = dataSet.getPatterns().size();
        int d = dataSet.getPattern(0).getDimensionality();
        
        // Initialize Membership Array [cluster][pattern]
        double[][] membership = new double[clusters][n];
        int sum = AlgorithmUtil.getSummation(clusters);
        for (int i = 0; i < membership.length; i++)
        {
            for (int j = 0; j < membership[i].length; j++)
            {
                membership[i][j] = (double)(i+1) / (double)sum;
            }
        }
        
        // Initialize Centroids
        Pattern[] centroids = new Pattern[clusters];
        DataSet[] regions = new DataSet[clusters];
        for(int i = 0; i < membership.length; i++)
        {
            ArrayList<Integer> alreadyChosen = new ArrayList<Integer>();
            int idx = random.nextInt(n);
            if (alreadyChosen.contains(idx))
            {
                continue;
            }
            centroids[i] = dataSet.getPattern(i).copy();                    
        }
        
        int iterations = 0;
        boolean centroidsChanged = true;
        double change = 11.00;
        while (elapsedTime <= maxWaitTime
                && centroidsChanged
                && change <= 1.50) 
        {
            centroidsChanged = false;
            
            // Update Memberships
            for(int i = 0; i < membership.length; i++)
            {
                double top = 0.00;
                double bottom = 0.00;
                double value = 0.00;
                for(int j = 0; j < membership[i].length; j++)
                {
                    value = AlgorithmUtil.getNorm(2.00, dataSet.getPattern(j), 
                            centroids[i]);
                    value = Math.pow(value, 2.00);
                    value = value / this.eta(i, membership, centroids, dataSet);
                    value *= -1;
                    value = Math.exp(value);
                    
                    membership[i][j] = value;
                }                
            }
            
            // Update Centroids
            for(int i = 0; i < membership.length; i++)
            {
                Pattern top = Pattern.getPattern();
                top.initAttributes(dataSet.getPattern(0).getDimensionality());
                double bottom = 1.00;
                for(int h = 0; h < membership[i].length; h++)
                {
                    top = AlgorithmUtil.addPatterns(top, 
                            AlgorithmUtil.multiply(dataSet.getPattern(h), 
                              membership[i][h]));
                    bottom += membership[i][h];
                }
                Pattern newCentroid = AlgorithmUtil.divide(top, bottom);
                if (!newCentroid.equals(centroids[i]))
                {
                    change += AlgorithmUtil.getChange(centroids[i], newCentroid);
                    centroids[i] = newCentroid;
                    centroidsChanged = true;
                }                
            }            
            
            elapsedTime = System.currentTimeMillis() - startTime;  
            iterations++;
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
        
        AlgorithmResults algResults = new AlgorithmResults();
        algResults.setAlgorithmName("Probabilistic Fuzzy C-Means Results");
        algResults.setAlgorithmId("possFuzzyCMeans");
        algResults.setRegions(regions);
        algResults.setCentroids(centroids);
        return algResults;
    }
    
    private double eta(int i, double[][] membership, Pattern[] centroids, 
            DataSet dataSet)
    {
        double value = 1.00;
        
        double top = 0.00;
        double bottom = 0.00;
        for (int h = 0; h < membership.length; h++)
        {
            top += Math.pow(membership[i][h], 2.00) * 
              Math.pow(
                AlgorithmUtil.getNorm(2.00, dataSet.getPattern(h), 
                        centroids[i]), 2.00);
            bottom += Math.pow(membership[i][h], 2.00);
        }
        value = (top / bottom) * this.l;
        
        return value;
    }
}

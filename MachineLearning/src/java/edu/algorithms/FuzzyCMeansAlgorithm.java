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
public class FuzzyCMeansAlgorithm implements AlgorithmInterface 
{
    private static Random random;
    
    static
    {
        random = new Random();
    }
    
    public static FuzzyCMeansAlgorithm getFuzzyCMeansAlgorithm()
    {
        return new FuzzyCMeansAlgorithm();
    }
    
    private FuzzyCMeansAlgorithm()
    {
        
    }
    
    @Override
    public AlgorithmResults executeAlgorithm(DataSet dataSet, 
            HttpServletRequest request, HttpServletResponse response) 
    {
        // Get All Options
        int clusters = Integer.parseInt(request.getParameter("fuzzyCMeansClusterOption"));
        long maxWaitTime = Long.parseLong(request.getParameter("fuzzyCMeansMaxTimeOption"));
        double m = Double.parseDouble(request.getParameter("fuzzyCMeansMembership"));
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
        /*
        for (int i = 0; i < clusters; i++)
        {
            regions[i] = null;
            regions[i] = DataSet.getDataSet();
        }
        */
        for(int i = 0; i < membership.length; i++)
        {
            ArrayList<Integer> alreadyChosen = new ArrayList<Integer>();
            int idx = random.nextInt(n);
            if (alreadyChosen.contains(idx))
            {
                continue;
            }
            alreadyChosen.add(idx);
            centroids[i] = dataSet.getPattern(idx).copy();                    
        }
        /*
        for (int i = 0; i < n; i++)
        {
            regions[i % clusters].addPattern(dataSet.getPattern(i).copy());
        }
        for (int i = 0; i < clusters; i++)
        {
            Pattern meanPattern = Pattern.getPattern(d);
            for (int j = 0; j < regions[i].getPatterns().size(); j++)
            {
                meanPattern = AlgorithmUtil.addPatterns(meanPattern, regions[i].getPattern(j));
            }
            centroids[i] = AlgorithmUtil.divide(meanPattern, regions[i].getPatterns().size());
        }
        for (int i = 0; i < clusters; i++)
        {
            regions[i].addPattern(dataSet.getPattern(i));
        }
        */
        
        int iterations = 0;
        double change = 11.00;
        boolean centroidsChanged = true;
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
                    top = 0.00;
                    bottom = 0.00;
                    value = 0.00;
                    for (int k = 0; k < membership.length; k++)
                    {
                        top += AlgorithmUtil.getNorm(1.00, dataSet.getPattern(j), centroids[i]);
                        bottom += AlgorithmUtil.getNorm(1.00, dataSet.getPattern(j), centroids[k]);
                        if (bottom == 0.00)
                        {
                            top += 1.00;
                        }
                        else
                        {
                            top = top / bottom; 
                        }

                        value += top;   
                        //value += Math.pow(top, (2.00/(m-1.00)));
                    }
                    value = Math.pow(value, (2.00/(m - 1.00)));                    
                    
                    membership[i][j] = 1.00 / value;
                }                     
            }
            
            // Update Centroids
            for(int i = 0; i < membership.length; i++)
            {
                Pattern top = Pattern.getPattern();
                top.initAttributes(dataSet.getPattern(0).getDimensionality());
                double bottom = 0.00;
                double u = 1.00;
                for(int j = 0; j < membership[i].length; j++)
                {
                    u = Math.pow(membership[i][j], m);
                    top = AlgorithmUtil.addPatterns(top, 
                            AlgorithmUtil.multiply(dataSet.getPattern(j), u));
                    bottom += u;
                    if (bottom == Double.POSITIVE_INFINITY)
                    {
                        bottom = bottom;
                    }
                }
                Pattern newCentroid = AlgorithmUtil.divide(top, bottom);
                if (!newCentroid.equals(centroids[i]))
                {
                    change += AlgorithmUtil.getChange(centroids[i], newCentroid);
                    centroids[i] = newCentroid.copy();
                    centroidsChanged = true;
                }                
            }            
            
            elapsedTime = System.currentTimeMillis() - startTime;  
            elapsedTime = 1;
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
        algResults.setAlgorithmName("Fuzzy C-Means Results");
        algResults.setRegions(regions);
        algResults.setCentroids(centroids);
        return algResults;
    }
    
}

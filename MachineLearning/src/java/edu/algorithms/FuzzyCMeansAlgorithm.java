/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.algorithms;

import edu.data.AlgorithmResults;
import edu.data.DataSet;
import edu.data.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lee
 */
public class FuzzyCMeansAlgorithm implements AlgorithmInterface 
{
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
        
        int d = dataSet.getPatterns().size();
        
        // Initialize Membership Array [cluster][pattern]
        double initMembership = 1.00 / (double)clusters;
        double[][] membership = new double[clusters][dataSet.getPatterns().size()];
        for(int i = 0; i < membership.length; i++)
        {
            for(int j = 0; j < membership[i].length; j++)
            {
                membership[i][j] = initMembership;
            }
        }
        
        // Initialize Centroids
        // Initialize Centroids Codebook
        Pattern[] centroids = new Pattern[clusters];
        DataSet[] regions = new DataSet[clusters];
        for(int i = 0; i < membership.length; i++)
        {
            Pattern top = Pattern.getPattern();
            top.initAttributes(dataSet.getPattern(0).getDimensionality());
            double bottom = 1.00;
            double u = 1.00;
            for(int j = 0; j < membership[i].length; j++)
            {
                u = Math.pow(membership[i][j], m);
                top = AlgorithmUtil.addPatterns(top, 
                        AlgorithmUtil.multiply(dataSet.getPattern(j), u));
                bottom += u;
            }
            centroids[i] = AlgorithmUtil.divide(top, bottom);
        }
        
        int iterations = 0;
        boolean centroidsChanged = true;
        while (elapsedTime <= maxWaitTime
                && centroidsChanged) 
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
                        top += AlgorithmUtil.getNorm(2.00, dataSet.getPattern(j), centroids[i]);
                        bottom += AlgorithmUtil.getNorm(2.00, dataSet.getPattern(j), centroids[k]);
                        top = top / bottom;
                        value += top;    
                        // value += Math.pow(top, (2/(m-1)))
                    }
                    value = Math.pow(value, (2/(m-1)));               
                    
                    membership[i][j] = value;
                }                
            }
            
            // Update Centroids
            for(int i = 0; i < membership.length; i++)
            {
                Pattern top = Pattern.getPattern();
                top.initAttributes(dataSet.getPattern(0).getDimensionality());
                double bottom = 1.00;
                double u = 1.00;
                for(int j = 0; j < membership[i].length; j++)
                {
                    u = Math.pow(membership[i][j], m);
                    top = AlgorithmUtil.addPatterns(top, 
                            AlgorithmUtil.multiply(dataSet.getPattern(j), u));
                    bottom += u;
                }
                Pattern newCentroid = AlgorithmUtil.divide(top, bottom);
                if (!newCentroid.equals(centroids[i]))
                {
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
        algResults.setAlgorithmName("Fuzzy C-Means Results");
        algResults.addItem("Iterations", Integer.toString(iterations));
        algResults.addItem("Elapsed Time", Double.toString(elapsedTime / 1000.0)); 
        algResults.addItem("Dimensions",Integer.toString(centroids[0].getDimensionality()));
        algResults.setRegions(
                AlgorithmUtil.convertRegionsToJson(regions));
        algResults.setCentroids(
                AlgorithmUtil.convertCentroidsToJson(centroids));
        return algResults;
    }
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.algorithms;

import edu.data.AlgorithmMetaBean;
import edu.data.AlgorithmResults;
import edu.data.DataSet;
import edu.data.Pattern;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lee
 */
public class KMeansAlgorithm implements AlgorithmInterface
{
    public static KMeansAlgorithm getKMeansAlgorithm()
    {
        return new KMeansAlgorithm();
    }
    
    @Override
    public AlgorithmResults executeAlgorithm(DataSet dataSet, HttpServletRequest request, HttpServletResponse response) 
    {
        // Get All K-Means Options
        int clusters = Integer.parseInt(request.getParameter("clusterOption"));
        long maxWaitTime = Long.parseLong(request.getParameter("maxTimeOption"));
        maxWaitTime *= 1000;
        
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
        
        int iterations = 0;
        boolean centroidsChanged = true;
        while (elapsedTime <= maxWaitTime
                && centroidsChanged) 
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
                    thisDistance = Pattern.getNorm(2.00, 
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
                    newCentroid = Pattern.addPatterns(newCentroid, regions[i].getPattern(j));
                }
                newCentroid = Pattern.divide(newCentroid, regions[i].getPatterns().size());
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
        algResults.setAlgorithmName("K-Means Results");
        algResults.setDimensions(Integer.toString(centroids[0].getDimensionality()));
        algResults.setIterations(Integer.toString(iterations));
        algResults.setElapsedTime(Double.toString(elapsedTime / 1000.0));
        algResults.setRegions(convertRegionsToJson(regions));
        algResults.setCentroids(convertCentroidsToJson(centroids));
        
        return algResults;
    }
    
    public String[] convertRegionsToJson(DataSet[] regions)
    {
        String[] jsonRegions = new String[regions.length];
        
        for(int i = 0; i < regions.length; i++)
        {
            jsonRegions[i] = regions[i].toJson();
        }
        
        return jsonRegions;
    }
    
    public String[] convertCentroidsToJson(Pattern[] centroids)
    {
        String[] jsonRegions = new String[centroids.length];
        
        for(int i = 0; i < centroids.length; i++)
        {
            jsonRegions[i] = centroids[i].toJson();
        }
        
        return jsonRegions;
    }
    
}

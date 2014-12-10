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
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lee
 */
public class KMeansAlgorithm implements AlgorithmInterface
{
    private static Random random;
    
    static
    {
        random = new Random();
    }
    
    public static KMeansAlgorithm getKMeansAlgorithm()
    {
        return new KMeansAlgorithm();
    }
    
    @Override
    public AlgorithmResults executeAlgorithm(DataSet dataSet, HttpServletRequest request, HttpServletResponse response) 
    {
        // Get All K-Means Options
        int clusters = Integer.parseInt(request.getParameter("kmeansClusterOption"));
        long maxWaitTime = Long.parseLong(request.getParameter("kmeansMaxTimeOption"));
        maxWaitTime *= 1000;
        
        // Time Variables
        long startTime = System.currentTimeMillis();
        long stopTime = 0L;
        long elapsedTime = 0L;
        
        int n = dataSet.getPatterns().size();
        int d = dataSet.getPattern(0).getDimensionality();
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
        
        for (int i = 0; i < centroids.length; i++)
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
            regions[i % clusters].addPattern(dataSet.getPattern(i));
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
        */
        
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
                    thisDistance = AlgorithmUtil.getNorm(2.00, 
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
        algResults.setAlgorithmName("K-Means Results");
        algResults.setAlgorithmId("kmeans");    
        algResults.setRegions(regions);
        algResults.setCentroids(centroids);
        
        return algResults;
    }
}

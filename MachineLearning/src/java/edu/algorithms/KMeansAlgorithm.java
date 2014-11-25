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
    public static AlgorithmMetaBean getMetaData()
    {
        AlgorithmMetaBean bean = new AlgorithmMetaBean();
        bean.setName("K-Means");
        
        ArrayList<String> optionNames = new ArrayList<>();
        ArrayList<String> optionHtml = new ArrayList<>();
        
        optionNames.add("Clusters");
        optionHtml.add("<input ");
        
        return bean;
    }
    
    @Override
    public HttpServletResponse executeAlgorithm(DataSet dataSet, HttpServletRequest request) 
    {
        // Get Starting Time
        long startTime = System.currentTimeMillis();
        
        // Initialize Centroids
        int clusters = Integer.parseInt(request.getParameter("clusters"));
        Pattern[] centroids = new Pattern[clusters];
        DataSet[] regions = new DataSet[clusters];
        for (int i = 0; i < centroids.length; i++)
        {
            centroids[i] = dataSet.getPattern(i);
        }
        
        int maxIterations  = Integer.parseInt(
                request.getParameter("maxIterations"));
        int iterations = 0;
        boolean centroidsChanged = true;
        while (iterations < maxIterations
                && centroidsChanged) 
        {
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
        } // while
        
        // Get Stop Time and Calculate Elapsed Time
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        
        
        
        return null;
    }
    
}

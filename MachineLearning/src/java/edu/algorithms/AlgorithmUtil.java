/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.algorithms;

import edu.data.DataSet;
import edu.data.Pattern;
import java.util.ArrayList;

/**
 *
 * @author Lee
 */
public class AlgorithmUtil 
{
    public static Pattern getClosestCentroid(double p, 
            Pattern pattern, Pattern[] centroids)
    {
        int index = getClosestCentroidIndex(p, pattern, centroids);
        return centroids[index];
    }
    
    public static int getClosestCentroidIndex(double p, 
            Pattern pattern, Pattern[] centroids)
    {
        // Get Closest Centroid to Pattern X
        int minCluster = -1;
        double thisDistance = 0;
        double minDistance = 0;
        for (int j = 0; j < centroids.length; j++)
        {
            thisDistance = AlgorithmUtil.getNorm(2.00, 
                    pattern, centroids[j]);
            if (minCluster == -1
                    || thisDistance < minDistance)
            {
                minDistance = thisDistance;
                minCluster = j;
            }
        }
        return minCluster;
    }
    
    public static Pattern subtract(Pattern a, Pattern b)
    {
        Pattern result = Pattern.getPattern();
        result.initAttributes(a.getDimensionality());
        for (int i = 0; i < a.getDimensionality(); i++)
        {
            result.setAttribute(i, a.getAttribute(i) - b.getAttribute(i));
        }
        return result;
    }
    
    public static double getNorm(double p, Pattern fromPattern, Pattern toPattern)
    {
        double normValue = 0.0f;
        double attrDistance = 0.0f;
        for (int i = 0; i < fromPattern.getAttributes().size(); i++)
        {
            attrDistance = fromPattern.getAttribute(i) - toPattern.getAttribute(i);
            attrDistance = Math.pow(attrDistance, p);
            normValue += attrDistance;
        }
        normValue = Math.sqrt(normValue);        
        return normValue;
    }
    
    public static Pattern multiply(Pattern pattern, double scalar)
    {
        for (int i = 0; i < pattern.getAttributes().size(); i++)
        {
            pattern.setAttribute(i, pattern.getAttribute(i) * scalar);
        }
        return pattern;
    }
    
    public static String[] convertRegionsToJson(DataSet[] regions)
    {
        String[] jsonRegions = new String[regions.length];
        
        for(int i = 0; i < regions.length; i++)
        {
            jsonRegions[i] = regions[i].toJson();
        }
        
        return jsonRegions;
    }
    
    public static String[] convertCentroidsToJson(Pattern[] centroids)
    {
        String[] jsonRegions = new String[centroids.length];
        
        for(int i = 0; i < centroids.length; i++)
        {
            jsonRegions[i] = centroids[i].toJson();
        }
        
        return jsonRegions;
    }
    
    public static double getManhattanDistance(Pattern patternOne, Pattern patternTwo)
    {
        double distance = 0.00;
        for (int i = 0; i < patternOne.getDimensionality(); i++)
        {
            distance += Math.abs(patternOne.getAttribute(i) - patternTwo.getAttribute(i));
        }
        return distance;
    }
    
    public static Pattern divide(Pattern pattern, double scalar)
    {
        for (int i = 0; i < pattern.getAttributes().size(); i++)
        {
            pattern.setAttribute(i, pattern.getAttribute(i) / scalar);
        }
        return pattern;
    }
    
    public static Pattern addPatterns(Pattern patternOne, Pattern patternTwo)
    {
        ArrayList<Double> values = new ArrayList<>();
        for (int i = 0; i < patternOne.getAttributes().size(); i++)
        {
            double value = patternOne.getAttribute(i) 
                    + patternTwo.getAttribute(i);
            values.add(value);
        }
        Double[] newPattern = new Double[values.size()];
        return Pattern.getPattern(values.toArray(newPattern));
    }
}

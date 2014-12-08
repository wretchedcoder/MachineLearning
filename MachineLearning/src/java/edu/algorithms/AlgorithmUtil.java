/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.algorithms;

import edu.data.DataSet;
import edu.data.Pattern;
import edu.kernel.IKernel;
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
            attrDistance = Math.abs( fromPattern.getAttribute(i) - toPattern.getAttribute(i) );
            attrDistance = Math.pow(attrDistance, p);
            
            
            normValue += attrDistance;
        }
        normValue = Math.sqrt(normValue);        
        return normValue;
    }
    
    public static Pattern multiply(Pattern pattern, double scalar)
    {
        Pattern newPattern = Pattern.getPattern(pattern.getDimensionality());
        newPattern = pattern.copy();
        for (int i = 0; i < pattern.getAttributes().size(); i++)
        {
            newPattern.setAttribute(i, newPattern.getAttribute(i) * scalar);
        }
        return newPattern;
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
        Pattern newPattern = Pattern.getPattern(pattern.getDimensionality());
//        newPattern = pattern.copy();
        for (int i = 0; i < pattern.getAttributes().size(); i++)
        {
            newPattern.setAttribute(i, pattern.getAttribute(i) / scalar);
        }
        return newPattern;
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
    
    public static double dotProduct(Pattern p1, Pattern p2)
    {
        double value = 0.00;
        int d = p1.getDimensionality();
        
        for (int i = 0; i < d; i++)
        {
            value += p1.getAttribute(i) * p2.getAttribute(i);
        }
        
        return value;
    }
    
    public static Pattern getMeanForDataSet(DataSet dataSet)
    {
        double d = dataSet.getPattern(0).getDimensionality();
        Pattern meanPattern = Pattern.getPattern((int)d);
        for (int i = 0; i < dataSet.getPatterns().size(); i++)
        {
            meanPattern = AlgorithmUtil.addPatterns(meanPattern, 
                    dataSet.getPattern(i));
        }
        meanPattern = AlgorithmUtil.divide(meanPattern, dataSet.getPatterns().size());
        return meanPattern;
    }
    
    public static double getVarianceForDataSet(Pattern meanPattern, 
            DataSet dataSet)
    {
        double value = 0.00;
        double d = dataSet.getPattern(0).getDimensionality();
        double n = dataSet.getPatterns().size();
        Pattern sumPattern = Pattern.getPattern((int)d);
        for (int i = 0; i < n; i++)
        {
            value += AlgorithmUtil.getNorm(2.00, meanPattern, 
                    dataSet.getPattern(i));            
        }
        value = value / n;        
        return value;
    }
    
    public static double getKernelDistance(IKernel kernel, Pattern p1, 
            Pattern p2)
    {
        double value = 0.00;
        
        value = kernel.apply(p1, p1) + kernel.apply(p2, p2) 
                - (2 * kernel.apply(p1, p2));
        
        return value;
    }
    
    public static Pattern getKernelClosestCentroid(IKernel kernel,
            Pattern pattern, Pattern[] centroids)
    {
        int index = getKernelClosestCentroidIndex(kernel, pattern, centroids);
        return centroids[index];
    }
    
    public static int getKernelClosestCentroidIndex(IKernel kernel, 
            Pattern pattern, Pattern[] centroids)
    {
        // Get Closest Centroid to Pattern X
        int minCluster = -1;
        double thisDistance = 0;
        double minDistance = 0;
        for (int j = 0; j < centroids.length; j++)
        {
            thisDistance = AlgorithmUtil.getKernelDistance(kernel, pattern, centroids[j]);
            if (minCluster == -1
                    || thisDistance < minDistance)
            {
                minDistance = thisDistance;
                minCluster = j;
            }
        }
        return minCluster;
    }
    
    /*
     * Method Finds the value of K(i,i) - 2*K(i,h) + K(r,s)
     */
    public static double getDistanceInFeatureSpace(IKernel kernel, 
            DataSet dataSet, DataSet[] regions, Pattern[] currCentroid, 
            int centroidIdx)
    {        
        int n = dataSet.getPatterns().size();
        
        double value = 0.00;    
        double midValue = 0.00; // K(ih)
        double endValue = 0.00; // K(rs)
        for (int i = 0; i < n; i++)
        {
            value = 0.00;
            // Get K(i,i)
            value = kernel.apply(dataSet.getPattern(i), dataSet.getPattern(i));
            
            // Get Middle value
            for (int h = 0; h < n; h++)
            {
                if (isPatternInRegion(dataSet.getPattern(h), regions[centroidIdx]))
                {
                    midValue += kernel.apply(dataSet.getPattern(i), dataSet.getPattern(h));
                }
            }
            midValue *= 2;
            value -= midValue;
            
            for (int r = 0; r < n; r++)
            {
                for (int s = 0; s < n; s++)
                {
                    if (isPatternInRegion(dataSet.getPattern(r), regions[centroidIdx])
                        && isPatternInRegion(dataSet.getPattern(s), regions[centroidIdx]))
                    {
                        endValue += kernel.apply(dataSet.getPattern(r), dataSet.getPattern(s));
                    }
                }
            }
            value += endValue;
        } // End of main loop
        return value;
    }
    
    public static boolean isPatternInRegion(Pattern p, DataSet region)
    {
        for (int i = 0; i < region.getPatterns().size(); i++)
        {
            if (region.getPattern(i).equals(p))
            {
                return true;
            }
        }            
        return false;
    }
    
    public static int getRegion(Pattern p, DataSet[] region)
    {
        for (int i = 0; i < region.length; i++)
        {
            for (int j = 0; j < region[i].getPatterns().size(); j++)
            {
                if (region[i].getPattern(j).equals(p))
                {
                    return i;
                }
            }
        }   
        return -1;
    }
    
    public static Pattern[] emptyPattern()
    {
        Pattern[] patterns = new Pattern[1];
        patterns[0].initAttributes(1);
        return patterns;
    }
    
    public static DataSet[] emptyDataSet()
    {
        DataSet[] dataSets = new DataSet[1];
        dataSets[0].addPattern(Pattern.getPattern(1));
        return dataSets;
    }
    
    public static int getSummation(int s)
    {
        return (s * (s-1)) / 2;
    }
    
    public static double getChange(Pattern p1, Pattern p2)
    {
        double value = 0.00;
        for (int i = 0; i < p1.getDimensionality(); i++)
        {
            value += p1.getAttribute(i) - p2.getAttribute(i);
        }
        return Math.abs(value);
    }
    
    public static Pattern getClosestCentroidInFeatureSpace(IKernel kernel, 
            Pattern pattern, Pattern[] centroids, DataSet dataSet, DataSet[] regions, 
            int centroidIdx)
    {
       int index = AlgorithmUtil.getClosestCentroidIndexInFeatureSpace(kernel, 
               pattern, centroids, dataSet, regions, centroidIdx);
       return centroids[index];
    }
    
    public static int getClosestCentroidIndexInFeatureSpace(IKernel kernel, 
            Pattern pattern, Pattern[] centroids, DataSet dataSet, DataSet[] regions, 
            int centroidIdx)
    {
        // Get Closest Centroid to Pattern X
        int minCluster = -1;
        double thisDistance = 0;
        double minDistance = 0;
        for (int j = 0; j < centroids.length; j++)
        {
            thisDistance = AlgorithmUtil.getDistanceInFeatureSpace(kernel, 
                    dataSet, regions, centroids, centroidIdx);
            if (minCluster == -1
                    || thisDistance < minDistance)
            {
                minDistance = thisDistance;
                minCluster = j;
            }
        }
        return minCluster;
            }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.algorithms;

import edu.data.AlgorithmResults;
import edu.data.DataSet;
import edu.data.Pattern;
import edu.kernel.IKernel;
import edu.kernel.KernelBuilder;
import java.util.ArrayList;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lee
 */
public class KernelFuzzyCMeansFeatureSpace implements AlgorithmInterface 
{
    private static Random random;
    
    static
    {
        random = new Random();
    }
    
    public static KernelFuzzyCMeansFeatureSpace getKernelFuzzyCMeansFeatureSpace()
    {
        return new KernelFuzzyCMeansFeatureSpace();
    }
    
    private KernelFuzzyCMeansFeatureSpace()
    {
        
    }
    
    @Override
    public AlgorithmResults executeAlgorithm(DataSet dataSet, 
            HttpServletRequest request, HttpServletResponse response) 
    {
        // Get All Options
        int clusters = Integer.parseInt(request.getParameter("kernelFuzzyCMeansKernelMetricClusterOption"));
        long maxWaitTime = Long.parseLong(request.getParameter("kernelFuzzyCMeansKernelMetricMaxTimeOption"));
        double m = Double.parseDouble(request.getParameter("kernelFuzzyCMeansKernelMetricMembership"));
        maxWaitTime *= 1000;
        String kernelOpt = request.getParameter("kernelSomKernel");
        
        // Time Variables
        long startTime = System.currentTimeMillis();
        long stopTime = 0L;
        long elapsedTime = 0L;
        
        int n = dataSet.getPatterns().size();
        int d = dataSet.getPattern(0).getDimensionality();
        
        // Get Variance for Gaussian Kernel
        double variance = 0.00;
        if (kernelOpt.equals("gaussian"))
        {
            Pattern meanPattern = Pattern.getPattern(d);            
            meanPattern = AlgorithmUtil.getMeanForDataSet(dataSet);
            variance = AlgorithmUtil.getVarianceForDataSet(meanPattern, dataSet);
        }
        
        // Get Kernel
        request.setAttribute("variance", variance);
        IKernel kernel = KernelBuilder.getKernel(kernelOpt, variance);
        
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
                        top += 1.00 - AlgorithmUtil.getKernelDistance(kernel, dataSet.getPattern(j), centroids[i]);
                        bottom += 1.00 - AlgorithmUtil.getKernelDistance(kernel, dataSet.getPattern(j), centroids[k]);
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
                    value = Math.pow(value, (1.00/(m - 1.00)));                    
                    
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
                    
                    top = dataSet.getPattern(i).copy();
                    top = AlgorithmUtil.multiply(top, 
                            AlgorithmUtil.getKernelDistance(kernel, 
                                    dataSet.getPattern(j), centroids[i]));
                    top = AlgorithmUtil.multiply(top, u);
                    
                    bottom += u * AlgorithmUtil.getKernelDistance(kernel, 
                                    dataSet.getPattern(j), centroids[i]);
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
        algResults.setAlgorithmName("Kernel Fuzzy C-Means w/ Kernelized Metric Results");
        algResults.setAlgorithmId("kernelFuzzyCMeansKernelMetric");
        algResults.setRegions(regions);
        algResults.setCentroids(centroids);
        return algResults;
    }
}

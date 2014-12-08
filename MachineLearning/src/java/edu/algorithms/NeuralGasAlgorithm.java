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
public class NeuralGasAlgorithm implements AlgorithmInterface 
{
    private static Random random;
    private double minEpsilon = 1.00;
    private double maxEpsilon = 1.00;
    private long maxTimeStep = 1;
    private double decay = 1.00;
    
    static
    {
        random = new Random();
    }
    
    public static NeuralGasAlgorithm getNeuralGasAlgorithm()
    {
        return new NeuralGasAlgorithm();
    }
    
    private NeuralGasAlgorithm()
    {
        
    }

    @Override
    public AlgorithmResults executeAlgorithm(DataSet dataSet, 
            HttpServletRequest request, HttpServletResponse response) 
    {
        // Get All Options
        int clusters = Integer.parseInt(request.getParameter("neuralGasClusterOption"));
        long maxWaitTime = Long.parseLong(request.getParameter("neuralGasMaxTimeOption"));
        maxWaitTime *= 1000;
        this.maxTimeStep = Integer.parseInt(request.getParameter("neuralGasMaxTimeStep"));
        this.minEpsilon = Double.parseDouble(request.getParameter("neuralGasMinEpsilon"));
        this.maxEpsilon = Double.parseDouble(request.getParameter("neuralGasMaxEpsilon"));
        this.decay = Double.parseDouble(request.getParameter("neuralGasDecay"));
        
        // Set Dimensionality
        int d = dataSet.getPattern(0).getDimensionality();
        
        // Initialize Centroids Codebook
        Pattern[] centroids = new Pattern[clusters];
        DataSet[] regions = new DataSet[clusters];
        for (int i = 0; i < centroids.length; i++)
        {
            centroids[i] = dataSet.getPattern(i).copy();
        }
        
        // Initialize t
        int t = 0;
        
        while (t < maxTimeStep)
        {
            // Get Random Pattern
            int x = random.nextInt(dataSet.getPatterns().size());
            
            double[] rank = new double[clusters];
            
            for (int i = 0; i < clusters; i++)
            {
                rank[i] = AlgorithmUtil.getNorm(2.00, dataSet.getPattern(x), centroids[i]);
            }
            
            // Adapt each codevector
            Pattern delta = Pattern.getPattern(d);
            for (int i = 0; i < clusters; i++)
            {
                delta = AlgorithmUtil.subtract(
                        dataSet.getPattern(x), centroids[i]);
                delta = AlgorithmUtil.multiply(delta, 
                        this.h(rank[i]));
                delta = AlgorithmUtil.multiply(delta, 
                        this.epsilon(t));
                centroids[i] = AlgorithmUtil.subtract(centroids[i], delta);
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
        
        AlgorithmResults algResults = new AlgorithmResults();
        algResults.setAlgorithmName("Neural Gas Results");
        algResults.setAlgorithmId("neuralGas");
        algResults.setRegions(regions);
        algResults.setCentroids(centroids);
        return algResults;
    }
    
    private double epsilon(int t)
    {
        double value = 1.00;
        
        value = minEpsilon * Math.pow((maxEpsilon / minEpsilon), ((double)t / (double)this.maxTimeStep));
        
        return value;
    }
    
    private double h(double p)
    {
        return Math.exp((-1.00 * p) / this.decay);
    }
}

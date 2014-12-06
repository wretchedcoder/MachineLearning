/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.kernel;

import edu.algorithms.AlgorithmUtil;
import edu.data.Pattern;

/**
 *
 * @author Lee
 */
public class GaussianKernel implements IKernel
{
    private double variance = 0.00;
    
    public static GaussianKernel getGaussianKernel()
    {
        return new GaussianKernel();
    }
    
    public static GaussianKernel getGaussianKernel(double variance)
    {
        return new GaussianKernel(variance);
    }
    
    private GaussianKernel()
    { }
    
    private GaussianKernel(double variance)
    {
        this.variance = variance;
    }
    
    @Override
    public double apply(Pattern p1, Pattern p2) 
    {
        double value = 0.00;
        
        value = AlgorithmUtil.getNorm(2.00, p1, p2);
        value = Math.pow(value, 2.00);
        value = value / (2.00 * variance);
        value = value * (-1.00);
        value = Math.exp(value);
        
        return value;
    }    
}

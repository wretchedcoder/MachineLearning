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
public class PolynomialKernel implements IKernel
{
    private double p = 1.00;
    
    public static PolynomialKernel getPolynomialKernel()
    {
        return new PolynomialKernel();
    }
    
    public static PolynomialKernel getPolynomialKernel(double p)
    {
        return new PolynomialKernel(p);
    }
    
    private PolynomialKernel()
    { }
    
    private PolynomialKernel(double p)
    {
        this.p = p;
    }

    @Override
    public double apply(Pattern p1, Pattern p2) 
    {
        double value = 0.00;
        
        value = AlgorithmUtil.dotProduct(p1, p2);
        value = value + 1.00;
        value = Math.pow(value, p);
        
        return value;
    }
}

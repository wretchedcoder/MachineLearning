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
public class LinearKernel implements IKernel
{
    public static LinearKernel getLinearKernel()
    {
        return new LinearKernel();
    }
    
    private LinearKernel()
    { }    

    @Override
    public double apply(Pattern p1, Pattern p2) 
    {
        double value = 0.00;
        
        value = AlgorithmUtil.dotProduct(p1, p2);
        
        return value;
    }
    
}

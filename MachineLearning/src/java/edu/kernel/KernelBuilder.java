/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.kernel;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Lee
 */
public class KernelBuilder 
{
    public static IKernel getKernel(String kernelType, double variance)
    {
        if (kernelType.equals("linear"))
        {
            return LinearKernel.getLinearKernel();
        }
        else if (kernelType.equals("polynomial degree 2"))
        {
            return PolynomialKernel.getPolynomialKernel(2);
        }
        else
        {
            return GaussianKernel.getGaussianKernel(variance);
        }
    }    
}

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
    public static IKernel getKernel(HttpServletRequest request)
    {
        String kernelType = request.getParameter("kernelType");
        if (kernelType.equals("linear"))
        {
            return LinearKernel.getLinearKernel();
        }
        else if (kernelType.equals("polynomial"))
        {
            double degree = Double.parseDouble(request.getParameter("degree"));
            return PolynomialKernel.getPolynomialKernel(degree);
        }
        else
        {
            double variance = (Double)request.getAttribute("variance");
            return GaussianKernel.getGaussianKernel(variance);
        }
    }    
}

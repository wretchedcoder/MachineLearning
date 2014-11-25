/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.algorithms;

import edu.data.DataSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lee
 */
public interface AlgorithmInterface 
{
    public HttpServletResponse executeAlgorithm(DataSet dataSet, 
            HttpServletRequest request);
}

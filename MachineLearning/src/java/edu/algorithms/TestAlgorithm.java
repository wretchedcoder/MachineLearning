/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.algorithms;

import edu.data.AlgorithmResults;
import edu.data.DataSet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lee
 */
public class TestAlgorithm implements AlgorithmInterface {

    public static TestAlgorithm getTestAlgorithm()
    {
        return new TestAlgorithm();
    }
    
    @Override
    public AlgorithmResults executeAlgorithm(DataSet dataSet, 
            HttpServletRequest request, HttpServletResponse response) {
        
        AlgorithmResults results = new AlgorithmResults();
        results.setIterations("5");
        
        return results;
    } // End of executeAlgorithm()
    
}

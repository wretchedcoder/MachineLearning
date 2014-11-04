/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.algorithms;

import edu.data.AlgorithmResults;
import edu.data.DataSource;
import java.util.Dictionary;

/**
 *
 * @author Lee
 */
public interface AlgorithmInterface 
{
    public AlgorithmResults executeAlgorithm(DataSource dataSource, 
            Dictionary<String, Object> options);
}

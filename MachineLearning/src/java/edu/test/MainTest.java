/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.test;

import edu.data.AlgorithmResults;
import edu.data.DataSet;
import edu.data.Pattern;

/**
 *
 * @author Lee
 */
public class MainTest 
{
    public static void main(String[] args)
    {
        
        
        AlgorithmResults results = new AlgorithmResults();
        results.addItem("testKey", "testItem");
        results.addItem("testKey2", "testItem2");
        
        System.out.println(results.toJson());
    }
}

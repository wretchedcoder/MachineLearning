/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.data;

import java.io.BufferedReader;
import java.io.File;
import java.nio.file.Files;

/**
 *
 * @author Lee
 */
public class DataSource 
{
    float[][] patterns;
    
    public static DataSource getDataSource(String name)
    {
        DataSource dataSource = new DataSource(name);
        
        
        
        return dataSource;
    }
    
    private DataSource(String name)
    {
        
        
        String filePath = "./datasources/" + name;
        File dataFile = new File(filePath);
        BufferedReader reader = null;
        try
        {
            reader = Files.newBufferedReader(dataFile.toPath());
            String line = null;
            while ((line = reader.readLine()) != null )
            {
                
            }
            
        }
        catch (Exception e)
        {
            
        }
        finally
        {
            if (reader != null)
            {
                reader.close();
            }
        }
        
    }
}

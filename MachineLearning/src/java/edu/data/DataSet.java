/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.data;

import java.io.BufferedReader;
import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;

/**
 *
 * @author Lee
 */
public class DataSet 
{
    ArrayList<Pattern> patterns;
    
    public static DataSet getDataSet(String name)
    {
        DataSet dataSet;
        try
        {
            dataSet = new DataSet(name);
            return dataSet;
        }
        catch (Exception e)
        {
            return null;
        }
    }
    
    private DataSet(String name) throws Exception
    {       
        patterns = new ArrayList<>();
        
        String filePath = "./datasources/" + name;
        File dataFile = new File(filePath);
        BufferedReader reader = null;
        try
        {
            reader = Files.newBufferedReader(dataFile.toPath());
            String line = null;
            while ((line = reader.readLine()) != null )
            {
                String[] attributes = line.split(",");
                ArrayList<Float> newAttributes = new ArrayList<>();
                for (int i = 0; i < attributes.length; i++)
                {                    
                    newAttributes.add(this.getFloat(attributes[i]));
                }
                patterns.add(
                        Pattern.getPattern((Double[])newAttributes.toArray()));
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
    } // private DataSet(String name)
    
    public Float getFloat(String newFloat)
    {
        try
        {
            return Float.parseFloat(newFloat);
        }
        catch (Exception e)
        {
            return 0.0f;
        }
    }
    
    public Pattern getPattern(int i)
    {
        return patterns.get(i);
    }
    
    public ArrayList<Pattern> getPatterns()
    {
        return patterns;
    }
    
    public void addPattern(Pattern newPattern)
    {
        patterns.add(newPattern);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

/**
 *
 * @author Lee
 */
public class DataSet 
{
    private String name = "";
    private String path = "";
    private ArrayList<Pattern> patterns;
    
    public static DataSet getDataSet()
    {
        return new DataSet();
    }           
    
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
    
    public void loadDataSet(File file) throws IOException
    {
        BufferedReader reader = null;
        try
        {
            reader = Files.newBufferedReader(file.toPath());
            String line;
            while ((line = reader.readLine()) != null )
            {
                String[] attributes = line.split(",");
                ArrayList<Double> newAttributes = new ArrayList<>();
                for (int i = 0; i < attributes.length; i++)
                {                    
                    newAttributes.add(this.getDouble(attributes[i]));
                }
                Double[] newPattern = new Double[attributes.length];
                patterns.add(
                        Pattern.getPattern(newAttributes.toArray(newPattern)));
            }
        }
        catch (Exception e)
        {
            System.out.println("Could not load datasource");
        }
        finally
        {
            if (reader != null)
            {
                reader.close();
            }
        }
    }
    
    private DataSet()
    {
        patterns = new ArrayList<>();
    }
    
    private DataSet(String name) throws Exception
    {       
        patterns = new ArrayList<>();
        
        String filePath = name;
        File dataFile = new File(filePath);
        this.name = dataFile.getName();
        this.name = this.name.substring(0, this.name.lastIndexOf("."));
        this.path = dataFile.getName();
        BufferedReader reader = null;
        try
        {
            reader = Files.newBufferedReader(dataFile.toPath());
            String line;
            while ((line = reader.readLine()) != null )
            {
                String[] attributes = line.split(",");
                ArrayList<Double> newAttributes = new ArrayList<>();
                for (int i = 0; i < attributes.length; i++)
                {                    
                    newAttributes.add(this.getDouble(attributes[i]));
                }
                Double[] newPattern = new Double[attributes.length];
                patterns.add(
                        Pattern.getPattern(newAttributes.toArray(newPattern)));
            }
            
        }
        catch (Exception e)
        {
            System.out.println("Could not find datasource");
        }
        finally
        {
            if (reader != null)
            {
                reader.close();
            }
        }        
    } // private DataSet(String name)
    
    public final Double getDouble(String newDouble)
    {
        try
        {
            return Double.parseDouble(newDouble);
        }
        catch (Exception e)
        {
            return 0.0;
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
    
    public void clearDataSet()
    {
        patterns.clear();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    
    
    
    public String toJson()
    {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String json = gson.toJson(this);
        return json;
    }
}

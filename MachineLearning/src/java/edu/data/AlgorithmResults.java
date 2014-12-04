/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.HashMap;

/**
 *
 * @author Lee
 */
public class AlgorithmResults 
{
    private String algorithmName = "";
    private String dimensions = "";
    private String iterations = "";
    private String elapsedTime = "";
    private String[] regions;
    private String[] centroids;
    private HashMap<String,String> items;
    
    public AlgorithmResults()
    {
        
    }
    
    public String toJson()
    {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String json = gson.toJson(this);
        return json;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }
    
    

    public String getIterations() {
        return iterations;
    }

    public void setIterations(String iterations) {
        this.iterations = iterations;
    }

    public String getElapsedTime() {
        return elapsedTime;
    }

    public void setElapsedTime(String elapsedTime) {
        this.elapsedTime = elapsedTime;
    }

    public String[] getRegions() {
        return regions;
    }

    public void setRegions(String[] regions) {
        this.regions = regions;
    }

    public String[] getCentroids() {
        return centroids;
    }

    public void setCentroids(String[] centroids) {
        this.centroids = centroids;
    }
    
    public HashMap<String,String> getItems()
    {
        if (items == null)
        {
            items = new HashMap<>();
        }
        return items;
    }
    
    public void setItems(HashMap<String,String> items)
    {
        this.items = items;
    }
    
    public void addItem(String key, String value)
    {
        if (items == null)
        {
            items = new HashMap<>();
        }
        items.put(key, value);
    }
    
}

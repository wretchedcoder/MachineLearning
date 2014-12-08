/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.ArrayList;

/**
 *
 * @author Lee
 */
public class Pattern 
{
    private ArrayList<Double> attributes;
    
    public static Pattern getPattern()
    {
        Pattern pattern = new Pattern();
        return pattern;
    }
    
    public static Pattern getPattern(int d)
    {
        Pattern pattern = new Pattern(d);
        return pattern;
    }
    
    public static Pattern getPattern(Double[] newAttributes)
    {
        Pattern pattern = new Pattern(newAttributes);
        return pattern;
    }
    
    private Pattern()
    {
        this.attributes = new ArrayList<>();
    }    
    
    private Pattern(int d)
    {
        this.attributes = new ArrayList<>();
        this.initAttributes(d);
    }  
    
    private Pattern(Double[] newAttributes)
    {
        this.attributes = new ArrayList<>();
        if (newAttributes != null)
        {
            for (int i = 0; i < newAttributes.length; i++)
            {
                this.attributes.add(newAttributes[i]);
            }
        }
    }
    
    public void initAttributes(int count)
    {
        for (int i = 0; i < count; i++)
        {
            this.attributes.add(0.0);
        }
    }
    
    public double getAttribute(int i)
    {
        if (i >= this.attributes.size())
        {
            return 0;
        }        
        return this.attributes.get(i);
    }
    
    public void setAttribute(int i, double d)
    {
        this.attributes.set(i, d);
    }
    
    public ArrayList<Double> getAttributes()
    {
        return this.attributes;
    }
    
    public void setAttributes(ArrayList<Double> attributes)
    {
        this.attributes = attributes;
    }
    
    public int getDimensionality()
    {
        return this.attributes.size();
    }
    
    @Override
    public boolean equals(Object obj)
    {
        Pattern pattern = (Pattern)obj;
        if (this.attributes.size() != pattern.getAttributes().size())
        {
            return false;
        }
        for (int i = 0; i < this.attributes.size(); i++)
        {
            if (this.getAttribute(i) != pattern.getAttribute(i))
            {
                return false;
            }
        }
        return true;
    }
    
    public String toJson()
    {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String json = gson.toJson(this);
        return json;
    }
    
    public Pattern copy()
    {
        Pattern copy = new Pattern(this.getDimensionality());
        for (int i = 0; i < copy.getDimensionality(); i++)
        {
            copy.setAttribute(i, this.getAttribute(i));
        }
        return copy;
    }
}

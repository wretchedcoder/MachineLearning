/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.data;

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
    
    public static Pattern getPattern(Double[] newAttributes)
    {
        Pattern pattern = new Pattern(newAttributes);
        return pattern;
    }
    
    private Pattern()
    {
        this.attributes = new ArrayList<>();
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
    
    public static double getNorm(double p, Pattern fromPattern, Pattern toPattern)
    {
        double normValue = 0.0f;
        double attrDistance = 0.0f;
        for (int i = 0; i < fromPattern.getAttributes().size(); i++)
        {
            attrDistance = fromPattern.getAttribute(i) - toPattern.getAttribute(i);
            attrDistance = Math.pow(attrDistance, p);
            normValue += attrDistance;
        }
        normValue = Math.sqrt(normValue);        
        return normValue;
    }
    
    public static Pattern addPatterns(Pattern patternOne, Pattern patternTwo)
    {
        ArrayList<Double> values = new ArrayList<>();
        for (int i = 0; i < patternOne.getAttributes().size(); i++)
        {
            double value = patternOne.getAttribute(i) 
                    + patternTwo.getAttribute(i);
            values.add(value);
        }
        return new Pattern((Double[])values.toArray());
    }
    
    public static Pattern divide(Pattern pattern, double scalar)
    {
        for (int i = 0; i < pattern.getAttributes().size(); i++)
        {
            pattern.attributes.set(i, pattern.attributes.get(i) / scalar);
        }
        return pattern;
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
}

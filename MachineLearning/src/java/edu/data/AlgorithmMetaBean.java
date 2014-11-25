/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.data;

/**
 *
 * @author Lee
 */
public class AlgorithmMetaBean 
{
    private String name;
    private String[] optionName;
    private String[] optionHtml;
    
    public AlgorithmMetaBean()
    {
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getOptionName() {
        return optionName;
    }

    public void setOptionName(String[] optionName) {
        this.optionName = optionName;
    }

    public String[] getOptionHtml() {
        return optionHtml;
    }

    public void setOptionHtml(String[] optionHtml) {
        this.optionHtml = optionHtml;
    }
    
    
}

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
public class DataSetMetaBean 
{
    private String filePath;
    private String name;
    
    public DataSetMetaBean()
    {
        
    }
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void stripExtension()
    {
        int index = this.name.indexOf('.');
        if (index != -1)
        {
            this.name = this.name.substring(0, index);
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.servlets;


/**
 *
 * @author Lee
 */
public class LoggingConfigurator 
{
    private static boolean isConfigured = false;
    
    public static void configureLogging()
    {
        if (isConfigured)
        {
            return;
        }
        isConfigured = true;
    }
}

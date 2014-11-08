/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.servlets;


import edu.data.DataSetNameBean;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lee
 */
@WebServlet(name = "IndexServlet", urlPatterns = {"/dashboard"})
public class IndexServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        String url = "/dashboard.jsp";        
        
        URL fileUrl = this.getServletContext().getResource("/datasources");
        File dataDir = new File(fileUrl.getPath());
        if (dataDir.isDirectory())
        {
            File[] fileList = dataDir.listFiles();
            DataSetNameBean[] datasources = 
                    new DataSetNameBean[fileList.length];
            for (int i = 0; i < fileList.length; i++)
            {
                datasources[i] = new DataSetNameBean();
                datasources[i].setFilePath(fileList[i].getAbsolutePath());
                datasources[i].setName(fileList[i].getName());
                datasources[i].stripExtension();
            }            
            request.setAttribute("datasources", datasources);               
        }
        
        this.getServletContext().
                getRequestDispatcher(url).
                forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

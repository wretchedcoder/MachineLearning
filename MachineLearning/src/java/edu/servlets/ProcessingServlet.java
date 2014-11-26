/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.servlets;

import edu.algorithms.TestAlgorithm;
import edu.data.AlgorithmResults;
import edu.data.DataSet;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Lee
 */
@WebServlet(name = "ProcessingServlet", urlPatterns = {"/Processing"})
public class ProcessingServlet extends HttpServlet {

    // Algorithms
    private TestAlgorithm testAlgorithm = null;
    
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
            throws ServletException, IOException {
        // Needed Params:
        // 1. Data Source
        // 2. Algorithm(s)
        // 3. Each Algorithm's Options
        String dataSourceName = (String) request.getParameter("datasource");
        
        DataSet dataSource = DataSet.getDataSet(dataSourceName);
        
        ArrayList<AlgorithmResults> algorithmResults = 
                new ArrayList<AlgorithmResults>();
        
        String testAlgorithmEnabled = (String) request.getParameter("enableTestAlg");
        if (testAlgorithmEnabled.equals("on"))
        {
            algorithmResults.add(
                    TestAlgorithm.getTestAlgorithm()
                    .executeAlgorithm(dataSource, request, response));
        }
        request.setAttribute("algorithmResults", algorithmResults);
        
        request.getServletContext().getRequestDispatcher("/results.jsp")
                .forward(request, response);
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

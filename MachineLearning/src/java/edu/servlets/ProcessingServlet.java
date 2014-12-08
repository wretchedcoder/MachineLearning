/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.servlets;

import edu.algorithms.AlgorithmUtil;
import edu.algorithms.FuzzyCMeansAlgorithm;
import edu.algorithms.KMeansAlgorithm;
import edu.algorithms.KernelFuzzyCMeansKernelMetric;
import edu.algorithms.KernelKMeansAlgorithm;
import edu.algorithms.KernelNeuralGasAlgorithm;
import edu.algorithms.KernelSomAlgorithm;
import edu.algorithms.NeuralGasAlgorithm;
import edu.algorithms.PossFuzzyCMeansAlgorithm;
import edu.algorithms.SomAlgorithm;
import edu.algorithms.TestAlgorithm;
import edu.data.AlgorithmResults;
import edu.data.DataSet;
import java.io.File;
import java.io.IOException;
import java.net.URL;
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
        URL fileUrl = this.getServletContext().getResource("/datasources/" + dataSourceName);
        if (fileUrl == null)
        {
            System.out.println("NULL");
        }
        File dataFile = new File(fileUrl.getPath());
        
        DataSet dataSource = DataSet.getDataSet();
        dataSource.loadDataSet(dataFile);
        
        ArrayList<AlgorithmResults> algorithmResults = 
                new ArrayList<>();
        
        try
        {
            String testAlgorithmEnabled = (String) request.getParameter("enableKmeansAlg");
            if (testAlgorithmEnabled != null && testAlgorithmEnabled.equals("on"))
            {
                algorithmResults.add(
                        KMeansAlgorithm.getKMeansAlgorithm()
                        .executeAlgorithm(dataSource, request, response));
            }
            testAlgorithmEnabled = (String) request.getParameter("enableSomAlg");
            if (testAlgorithmEnabled != null && testAlgorithmEnabled.equals("on"))
            {
                algorithmResults.add(
                        SomAlgorithm.getSomAlgorithm()
                        .executeAlgorithm(dataSource, request, response));
            }
            testAlgorithmEnabled = (String) request.getParameter("enableNeuralGasAlg");
            if (testAlgorithmEnabled != null && testAlgorithmEnabled.equals("on"))
            {
                algorithmResults.add(
                        NeuralGasAlgorithm.getNeuralGasAlgorithm()
                        .executeAlgorithm(dataSource, request, response));
            }
            testAlgorithmEnabled = (String) request.getParameter("enableFuzzyCMeansAlg");
            if (testAlgorithmEnabled != null && testAlgorithmEnabled.equals("on"))
            {
                algorithmResults.add(
                        FuzzyCMeansAlgorithm.getFuzzyCMeansAlgorithm()
                        .executeAlgorithm(dataSource, request, response));
            }
            testAlgorithmEnabled = (String) request.getParameter("enableProbFuzzyCMeansAlg");
            if (testAlgorithmEnabled != null && testAlgorithmEnabled.equals("on"))
            {
                algorithmResults.add(
                        PossFuzzyCMeansAlgorithm.getProbFuzzyCMeansAlgorithm()
                        .executeAlgorithm(dataSource, request, response));
            }
            testAlgorithmEnabled = (String) request.getParameter("enableKernelKmeansAlg");
            if (testAlgorithmEnabled != null && testAlgorithmEnabled.equals("on"))
            {
                algorithmResults.add(
                        KernelKMeansAlgorithm.getKernelKMeansAlgorithm()
                        .executeAlgorithm(dataSource, request, response));
            }
            testAlgorithmEnabled = (String) request.getParameter("enableKernelSomAlg");
            if (testAlgorithmEnabled != null && testAlgorithmEnabled.equals("on"))
            {
                algorithmResults.add(
                        KernelSomAlgorithm.getKernelSomAlgorithm()
                        .executeAlgorithm(dataSource, request, response));
            }
            testAlgorithmEnabled = (String) request.getParameter("enableKernelNeuralGasAlg");
            if (testAlgorithmEnabled != null && testAlgorithmEnabled.equals("on"))
            {
                algorithmResults.add(
                        KernelNeuralGasAlgorithm.getKernelNeuralGasAlgorithm()
                        .executeAlgorithm(dataSource, request, response));
            }
            testAlgorithmEnabled = (String) request.getParameter("enableKernelFuzzyCMeansKernelMetricAlg");
            if (testAlgorithmEnabled != null && testAlgorithmEnabled.equals("on"))
            {
                algorithmResults.add(
                        KernelFuzzyCMeansKernelMetric.getKernelFuzzyCMeansKernelMetric()
                        .executeAlgorithm(dataSource, request, response));
            }
            
        }
        catch (Exception e)
        {
            AlgorithmResults errorResults = new AlgorithmResults();
            errorResults.setAlgorithmName("Error Report");
            errorResults.addItem("Error Message", e.getClass() + ": " + e.getMessage());
            errorResults.addItem("Stack Trace", e.getStackTrace().toString());
            errorResults.setCentroids(AlgorithmUtil.emptyPattern());
            errorResults.setRegions(AlgorithmUtil.emptyDataSet());
            algorithmResults.add(errorResults);
        }
        
        StringBuffer json = new StringBuffer();
        json.append("[");
        for (int i = 0; i < algorithmResults.size(); i++)
        {
            try
            {
                json.append(algorithmResults.get(i).toJson());
            }
            catch (Exception e)
            {
                json.append("{\"algorithmName\": \"Error when parsing " 
                        + algorithmResults.get(i).getAlgorithmName() + "\"}");
            }
            
            json.append(",");
        }     
        json.deleteCharAt(json.length() - 1);
        json.append("]");
        
        response.getWriter().print(json);
    }
    
    private String parseAlgorithmResults(AlgorithmResults algorithmResult)
    {
        String json = "";
        return json;
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

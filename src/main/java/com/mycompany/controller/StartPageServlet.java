/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.model.StationDataBase;
import com.mycompany.model.ValidDataModel;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This servlet is responsible for start page
 *
 * @author Agata Pietrzycka
 * @version 1.0
 */
@WebServlet(name = "StartPageServlet", urlPatterns = {"/StartPageServlet"})
public class StartPageServlet extends HttpServlet {

    /**
     * This is an object with base which contain list with all station
     */
    private StationDataBase base;
    /**
     * This is an object with validator to valid data which user introduce to
     * program
     */
    private ValidDataModel validator;
    /**
     * This is an object with session
     *
     */
    private HttpSession session;
    /**
     * This is a field with file where base is save.
     */
    private String file;
    /**
     * This is a field with flag to load file only once
     */
    boolean ifFileReadFlags;

    /**
     * Class constructor
     */
    public StartPageServlet() {
        base = new StationDataBase();
        validator = new ValidDataModel();
        ifFileReadFlags = true;

    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        if (file == null) {
            file = getServletContext().getRealPath("/WEB-INF/plikzBaza.txt");
        }
        try {
            if (ifFileReadFlags == true) {
                base.loadFromFile(file);
                ifFileReadFlags = false;
            }
        } catch (IOException e) {
        }
        session = request.getSession();
        session.setAttribute("BASE", base);
        session.setAttribute("VALIDATOR", validator);
        session.setAttribute("FILE", file);

        getServletContext().getRequestDispatcher("/startPage.html").include(request, response);
    }

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
        processRequest(request, response);
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
        processRequest(request, response);
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

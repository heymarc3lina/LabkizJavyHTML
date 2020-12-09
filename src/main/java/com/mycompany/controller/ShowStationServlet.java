/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.model.StationDataBase;
import com.mycompany.model.WheatherStation;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This servlet is responsible for show to user list with all station which
 * exist in base.
 *
 * @author Agata Pietrzycka
 * @version 2.0
 */
@WebServlet(name = "ShowStationServlet", urlPatterns = {"/ShowStationServlet"})
public class ShowStationServlet extends HttpServlet {

    /**
     * This is an object with base which contain list with all station
     */
    private StationDataBase base;
    /**
     * This is an object with session
     *
     */
    private HttpSession session;

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

        session = request.getSession();
        base = (StationDataBase) session.getAttribute("BASE");

        response.setContentType("text/html;charset=UTF-8");

        String htmlPage = "<!DOCTYPE html><html><head>";
        htmlPage += "<title>Stacje pogodowe zapisane w bazie</title>";
        htmlPage += "<style> table, th, td { border: 1px solid; } </style>";
        htmlPage += "</head><body>";
        htmlPage += "<h1>Stacje pogodowe zapisane w bazie</h1>";
        htmlPage += "<table style=\"width:100%\">";
        htmlPage += "<tr>";
        htmlPage += "<th>Misto</th>";
        htmlPage += "<th>Temperatura</th>";
        htmlPage += "<th>Wilgotność</th>";
        htmlPage += "<th>Ciśnienie</th>";
        htmlPage += "<th>Widoczność</th>";
        htmlPage += "<th>Opad atmosferyczny</th>";
        htmlPage += "<th>Promieniowanie słoneczne</th>";
        htmlPage += "<th>Poziom smogu</th>";
        htmlPage += "</tr>";

        Comparator<WheatherStation> compareByTown = Comparator.comparing(WheatherStation::getTown);
        for (WheatherStation db : base.getList().stream().sorted(compareByTown).collect(Collectors.toList())) {
            htmlPage += "<tr>";
            htmlPage += "<td>" + db.getTown() + "</td>";
            htmlPage += "<td>" + db.getMeasurement().getTemperature() + " stopni Celsjusza." + "</td>";
            htmlPage += "<td>" + db.getMeasurement().getHumidity() + "%" + "</td>";
            htmlPage += "<td>" + db.getMeasurement().getPressure() + " hPa" + "</td>";
            htmlPage += "<td>" + db.getMeasurement().getVisibility() + " m" + "</td>";
            htmlPage += "<td>" + db.getMeasurement().getTheAmountOfPrecipitation() + " mm" + "</td>";
            htmlPage += "<td>" + db.getMeasurement().getSunlight() + " W/m2" + "</td>";
            htmlPage += "<td>" + db.getMeasurement().getSmog() + "%" + "</td>";
            htmlPage += "</tr>";
        }
        htmlPage += "</table><br><form action=\"StartPage\" method=\"GET\"> <input type=\"submit\" value=\"Wróć\" /> </form>";
        htmlPage += "</body></html>";

        try (PrintWriter out = response.getWriter()) {
            out.println(htmlPage);
        }

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

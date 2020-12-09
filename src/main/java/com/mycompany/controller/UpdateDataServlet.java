/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.model.Exceptions.StationDoesNotExistException;
import com.mycompany.model.Measurement;
import com.mycompany.model.StationDataBase;
import com.mycompany.model.ValidDataModel;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This servlet is responsible for handle data from user and update choosen
 * station by user
 *
 * @author Agata Pietrzycka
 * @version 2.0
 */
@WebServlet(name = "UpdateDataServlet", urlPatterns = {"/UpdateDataServlet"})
public class UpdateDataServlet extends HttpServlet {

    /**
     * This is an object with base which contain list with all station
     */
    StationDataBase base;
    /**
     * This is an object with validator to valid data which user introduce to
     * program
     */
    ValidDataModel validator;
    /**
     * This is an object with session
     *
     */
    HttpSession session;

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

        try (PrintWriter out = response.getWriter()) {

            boolean authenticated = false;
            if (request.getCookies() != null) {
                for (Cookie cookie : request.getCookies()) {
                    if (cookie.getName().equals("Authenticated")) {
                        cookie.setMaxAge(60);
                        response.addCookie(cookie);
                        authenticated = true;
                    }
                }
            }
            if (!authenticated) {
                out.println("<p style='color:orange;'>Baza nie została zaktualizowana, zaloguj się ponownie!</p>");
                out.println("<meta http-equiv='refresh' content='2;URL=index.html'>");
                return;
            }

            session = request.getSession();
            base = (StationDataBase) session.getAttribute("BASE");
            validator = (ValidDataModel) session.getAttribute("VALIDATOR");
            String file = (String) session.getAttribute("FILE");

            String town = request.getParameter("chooseTown");
            String temperature = request.getParameter("temperatureTextField");
            String humidity = request.getParameter("humidityTextField");
            String smog = request.getParameter("smogTextField");
            String rainfall = request.getParameter("rainfallTextField");
            String visability = request.getParameter("visabilityTextField");
            String sunlight = request.getParameter("sunlightTextField");
            String pressure = request.getParameter("pressureTextField");
            if (validator.temperatureValidator(temperature) == false || validator.smogOrHumidityValidator(humidity) == false
                    || validator.smogOrHumidityValidator(smog) == false || validator.visabilityValidator(visability) == false || validator.pressureValidator(pressure) == false
                    || validator.sunlightValidator(sunlight) == false || validator.theAmmoutofPrecipitationValidator(rainfall) == false) {

                out.println("<meta http-equiv='refresh' content='2;URL=ChooseTown'>");
                out.println("<p style='color:red;'>Niepoprawne dane!</p>");
            } else {

                try {
                    Measurement measure = base.getStationByTown(town).getMeasurement();
                    measure.setAmountOfPrecipitation(Double.parseDouble(rainfall));
                    measure.setHumidity(Double.parseDouble(humidity));
                    measure.setPressure(Double.parseDouble(pressure));
                    measure.setSmog(Double.parseDouble(smog));
                    measure.setSunlight(Double.parseDouble(sunlight));
                    measure.setTemperature(Double.parseDouble(temperature));
                    measure.setVisibility(Double.parseDouble(visability));
                } catch (StationDoesNotExistException exception) {
                    out.println("<meta http-equiv='refresh' content='2;URL=StartPage'>");//redirects after 3 seconds
                    out.println("<p style='color:red;'>Nie udalo sie zaktualizowac danych!</p>");
                }

                out.println("<meta http-equiv='refresh' content='2;URL=StartPage'>");//redirects after 3 seconds
                out.println("<p style='color:green;'>Zaktualizowano stacje!</p>");
                try {

                    base.saveBaseToFile(file);

                } catch (IOException e) {
                    out.println("<meta http-equiv='refresh' content='2;URL=StartPage'>");
                    out.println("<p style='color:red;'>Przepraszamy! Nie udalo się uaktualnic bazy!</p>");
                }
            }
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

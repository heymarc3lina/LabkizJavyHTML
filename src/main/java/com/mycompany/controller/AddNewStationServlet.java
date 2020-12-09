/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.controller;

import com.mycompany.model.Measurement;
import com.mycompany.model.StationDataBase;
import com.mycompany.model.ValidDataModel;
import com.mycompany.model.WheatherStation;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This servlet is responsible for handle data from user and create a new
 * station adding it to base.
 *
 * @author Agata Pietrzycka
 * @version 2.0
 */
@WebServlet(name = "AddNewStationServlet", urlPatterns = {"/AddNewStationServlet"})
public class AddNewStationServlet extends HttpServlet {

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
     * This is an object with file where base is save.
     */
    private String file;

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

        session = request.getSession();
        base = (StationDataBase) session.getAttribute("BASE");
        validator = (ValidDataModel) session.getAttribute("VALIDATOR");
        file = (String) session.getAttribute("FILE");

        try (PrintWriter out = response.getWriter()) {

            updateSessionInCookies(request, response);

            if (request.getCookies().length == 1) {
                out.println("<p style='color:orange;'>Baza nie została zapisana, zaloguj się ponownie!</p>");
                out.println("<meta http-equiv='refresh' content='2;URL=index.html'>");
                return;
            }

            response.setContentType("text/html;charset=UTF-8");

            String town = request.getParameter("townTextField");
            String temperature = request.getParameter("temperatureTextField");
            String humidity = request.getParameter("humidityTextField");
            String smog = request.getParameter("smogTextField");
            String rainfall = request.getParameter("rainfallTextField");
            String visability = request.getParameter("visabilityTextField");
            String sunlight = request.getParameter("sunlightTextField");
            String pressure = request.getParameter("pressureTextField");

            if (validator.townValidator(town) == false || validator.temperatureValidator(temperature) == false || validator.smogOrHumidityValidator(humidity) == false
                    || validator.smogOrHumidityValidator(smog) == false || validator.visabilityValidator(visability) == false || validator.pressureValidator(pressure) == false
                    || validator.sunlightValidator(sunlight) == false || validator.theAmmoutofPrecipitationValidator(rainfall) == false) {

                out.println("<meta http-equiv='refresh' content='2;URL=addStationForm.html'>");
                out.println("<p style='color:red;'>Niepoprawne dane!</p>");

            } else {
                if (base.findStation(town)) {
                    out.println("<meta http-equiv='refresh' content='2;URL=addStationForm.html'>");
                    out.println("<p style='color:red;'>Stacja w podanym miejscu juz istnieje!</p>");

                } else {
                    base.addStation(createStation(town, Double.parseDouble(temperature), Double.parseDouble(humidity), Double.parseDouble(pressure),
                            Double.parseDouble(visability), Double.parseDouble(rainfall), Double.parseDouble(sunlight), Double.parseDouble(smog)));
                    out.println("<meta http-equiv='refresh' content='2;URL=StartPage'>");
                    out.println("<p style='color:green;'>Dodano nowa stacje do bazy!</p>");
                    try {

                        base.saveBaseToFile(file);
                    } catch (IOException e) {
                        out.println("<meta http-equiv='refresh' content='2;URL=StartPage'>");
                        out.println("<p style='color:red;'>Przepraszamy! Nie udalo się uaktualnic bazy!</p>");
                    }
                }
            }
        }
    }

    /**
     * Method responsible for create new station with recived parameters.
     *
     * @param town it's a town where station is located
     * @param parameters it's paremetrs which station measure
     * @return WheatherStation which is null or station
     */
    public WheatherStation createStation(String town, double... parameters) {

        if (parameters.length != 7 || town == null || town.isEmpty()) {
            return null;
        }

        ArrayList<Double> listOfParameters = new ArrayList<>();
        for (int i = 0; i < parameters.length; i++) {
            listOfParameters.add(parameters[i]);
        }

        WheatherStation station = new WheatherStation(town, new Measurement(listOfParameters));
        return station;

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

    /**
     * This method is responsible for refresing session.
     *
     * @param request servlet request
     * @param response servlet response
     */
    private void updateSessionInCookies(HttpServletRequest request, HttpServletResponse response) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("Authenticated")) {
                    cookie.setMaxAge(60);
                    response.addCookie(cookie);
                }
            }
        }
    }

}

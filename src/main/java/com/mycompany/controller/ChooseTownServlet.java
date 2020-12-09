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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * This servlet is responsible for giving to user possibility to choose station
 * which user want to change.
 *
 * @author Agata Pietrzycka
 * @version 1.0
 */
@WebServlet(name = "ChooseTownServlet", urlPatterns = {"/ChooseTownServlet"})
public class ChooseTownServlet extends HttpServlet {

    /**
     * This is an object with base which contain list with all station
     */
    StationDataBase base;
    /**
     * This is an object with session
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

        session = request.getSession();
        base = (StationDataBase) session.getAttribute("BASE");

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
                out.println("<p style='color:orange;'>Dostęp tylko dla zalogowanych!</p>");
                out.println("<meta http-equiv='refresh' content='2;URL=index.html'>");
                return;
            }

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Pierwzy etap: Wybierz miasto</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Pierwzy etap: Wybierz miasto</h1>");
            out.println("<form action=\"UpdateDataForm\"  metod=\"GET\">");
            out.println("<select name=\"chooseTown\">");
            Comparator<WheatherStation> compareByTown = Comparator.comparing(WheatherStation::getTown);
            for (WheatherStation db : base.getList().stream().sorted(compareByTown).collect(Collectors.toList())) {
                out.println("<option value=\"" + db.getTown() + "\">" + db.getTown() + "</option>");
            }
            out.println("</select>");
            out.println("<input type=\"submit\" value=\"Zatwierdż\" />");
            out.println(" </form><br>");
            out.println("<form action=\"StartPage\">");
            out.println("<input type=\"submit\" value=\"Wróć\" />");
            out.println(" </form> ");
            out.println("</body>");
            out.println("</html>");
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cmu.capstone.dashboard;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Marshall An <marshall.an at cloudcomputing.scs.cmu.edu>
 */
@WebServlet(name = "DashboardServlet", urlPatterns = {"/"})
public class DashboardServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Aggregation Analytics
        Location[] locations = SpatialGetMarkers.getLocations();
        request.setAttribute("sum-visits",
                LocationUtils.getSumVisits(locations)
        );
        request.setAttribute("avg-visits",
                LocationUtils.getAvgVisits(locations)
        );
        request.setAttribute("dst-visits",
                LocationUtils.getDstVisits(locations)
        );
        request.setAttribute("percentage-dst-visits",
                (double)LocationUtils.getDstVisits(locations) / 
                        LocationUtils.getStartVisits(locations)
        );
        request.setAttribute("mostly-visited-place",
                LocationUtils.getMostPopularLocation(locations)
        );
        // charts
        request.setAttribute("location-names",
                LocationUtils.getLocationNames(locations)
        );
        request.setAttribute("location-visits",
                LocationUtils.getLocationVisits(locations)
        );
        request.setAttribute("time-to-finish", 
                String.format("%.1f", 
                        LocationUtils.getAvgTimeToFinishTour(locations))
                
        );        
        request.setAttribute("locations-to-travel",
                LocationUtils.getLocationsToTravel(locations)
        );
        request.setAttribute("time-to-travel",
                LocationUtils.getAvgTimeToTravel(locations)
        );
        // Location-Specific Data
        request.setAttribute("locations",
                HTMLUtils.toHtml(locations));
        request.setAttribute("dump",
                HTMLUtils.toHtml(SpatialGetMarkers.getResponse()));
        RequestDispatcher view = request.getRequestDispatcher("dashboard.jsp");
        view.forward(request, response);
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

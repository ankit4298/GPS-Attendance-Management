/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remote_attendance_servlets;

import database.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import servlets.InsertAttendanceDetails;

/**
 *
 * @author ANKIT
 */
public class AddRemoteSite extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String lat = request.getParameter("latitude");
            String lng = request.getParameter("longitude");
            String rad = request.getParameter("radius");
            String site_name = request.getParameter("site_name");
            String loc_id = request.getParameter("locID");
            
            try {

                con = DBConnection.getDBConnection();
                String sql = "insert into remote_sites values (?,?,?,?,?)";
                ps = con.prepareStatement(sql);
                
                ps.setInt(1, Integer.parseInt(loc_id));
                ps.setString(2, site_name);
                ps.setDouble(3, Double.parseDouble(lat));
                ps.setDouble(4, Double.parseDouble(lng));
                ps.setDouble(5, Double.parseDouble(rad));
                
                int i = ps.executeUpdate();
                out.print("Successfully Added a remote site.");

            } catch (SQLException ex) {
                out.print("Adding Remote site failed. . .");
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

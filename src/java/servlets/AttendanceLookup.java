/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import serializable_classes.AttendanceRecords;
import serializable_classes.EmployeeInfo;

/**
 *
 * @author ANKIT
 */
public class AttendanceLookup extends HttpServlet {

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

        String filterBox = request.getParameter("filterBox");
        String filterChoice = request.getParameter("filterChoice");

        System.out.println(filterBox + " " + filterChoice);

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        String sql=null;

        if (filterBox == null) {
            sql = "select * from attendance_details";
        } else if(filterChoice.equals("1")){    // eid
            sql = "select * from attendance_details where eid='"+filterBox+"'";
        } else if(filterChoice.equals("2")){    // date
            sql = "select * from attendance_details where date='"+filterBox+"'";
        }

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            ArrayList<AttendanceRecords> attendRecords = new ArrayList<>();
            //Add attendance Records to dispatcher
            request.setAttribute("attendRec", attendRecords);

            con = DBConnection.getDBConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                AttendanceRecords ar = new AttendanceRecords();

                ar.setEid(rs.getString("eid"));
                ar.setDate(rs.getString("date"));
                ar.setIntime(rs.getString("intime"));
                ar.setOuttime(rs.getString("outtime"));
                ar.setDuration(Integer.toString(rs.getInt("duration")));
                ar.setOutstatus(Integer.toString(rs.getInt("out_status")));
                ar.setLastupdate(rs.getString("lastupdate_time"));

                attendRecords.add(ar);

            }

            RequestDispatcher disp = request.getRequestDispatcher("/displayAttendanceRecords.jsp");
            disp.forward(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(LoginRecordsLookup.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                rs.close();
                stmt.close();
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoginRecordsLookup.class.getName()).log(Level.SEVERE, null, ex);
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

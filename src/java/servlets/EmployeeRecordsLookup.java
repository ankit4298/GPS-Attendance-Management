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
import serializable_classes.EmployeeInfo;
import serializable_classes.LoginInfo;

/**
 *
 * @author ANKIT
 */
public class EmployeeRecordsLookup extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            ArrayList<EmployeeInfo> empInfo = new ArrayList<>();
            //Add empInfo to dispatcher
            request.setAttribute("empInfo", empInfo);

            con = DBConnection.getDBConnection();
            stmt = con.createStatement();
            String sql = "select * from employee_details";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                EmployeeInfo ei=new EmployeeInfo();
                
                ei.setEid(rs.getString("eid"));
                ei.setFirstname(rs.getString("firstname"));
                ei.setMiddlename(rs.getString("middlename"));
                ei.setLastname(rs.getString("lastname"));
                ei.setGender(rs.getString("gender"));
                ei.setEmail(rs.getString("email"));
                ei.setWorkdept(rs.getString("workdept"));
                ei.setAddress(rs.getString("address"));
                ei.setPhno(rs.getString("phno"));
                
                empInfo.add(ei);

            }

            RequestDispatcher disp = request.getRequestDispatcher("/displayEmployeeRecords.jsp");
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

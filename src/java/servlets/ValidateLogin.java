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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import pure_java.CreateJSON;

/**
 *
 * @author ANKIT
 */
public class ValidateLogin extends HttpServlet {

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

            String username = request.getParameter("username").toLowerCase();
            String password = request.getParameter("password");

            int validate = checkLogin(username, password);
            CreateJSON cj = new CreateJSON();
            String jsonResponse = "";

            if (validate == 1) {
                
                ArrayList al = getEmployeeDetails(username);

                jsonResponse = cj.loginResponse(username, 1, 0, al);  //login success
            } else if (validate == -1) {
                jsonResponse = cj.loginResponse(username, 1, 1);  //login fails at client w/ double login msg
            } else {
                jsonResponse = cj.loginResponse(username, 0, 0);  //login fails at client
            }

            out.print(jsonResponse);

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

    public int checkLogin(String uname, String pass) {
        try {
            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            //username and password from android

            con = DBConnection.getDBConnection();
            String sql = "select * from logincredentials where uname=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, uname);
            rs = ps.executeQuery();

            String dbpass = "";
            int loginstatus = -1;

            while (rs.next()) {
                dbpass = rs.getString("pass");
                loginstatus = rs.getInt("loginstatus");
            }

            if (dbpass.equals(pass) && loginstatus == 0) {
                return 1;   // success
            } else if (dbpass.equals(pass) && loginstatus == 1) {
                return -1;  // double login
            } else {
                return 0;   // fails
            }

        } catch (SQLException ex) {
            Logger.getLogger(ValidateLogin.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

    }

    public ArrayList getEmployeeDetails(String username) {

        ArrayList al = new ArrayList();

        try {

            Connection con = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            //username and password from android

            con = DBConnection.getDBConnection();
            String sql = "select * from employee_details where eid=?";
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();

            while (rs.next()) {

                al.add(rs.getString("eid"));
                al.add(rs.getString("firstname"));
                al.add(rs.getString("middlename"));
                al.add(rs.getString("lastname"));
                al.add(rs.getString("gender"));
                al.add(rs.getString("email"));
                al.add(rs.getString("Address"));
                al.add(rs.getString("phno"));
            }

            rs.close();
            ps.close();
            con.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(ValidateLogin.class.getName()).log(Level.SEVERE, null, ex);
        }

        return al;
    }

}

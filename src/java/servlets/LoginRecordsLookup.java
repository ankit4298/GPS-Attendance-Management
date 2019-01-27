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
import serializable_classes.LoginInfo;

/**
 *
 * @author ANKIT
 */
public class LoginRecordsLookup extends HttpServlet {

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

        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            ArrayList<LoginInfo> logininfo = new ArrayList<>();
            //Add logininfo to dispatcher
            request.setAttribute("loginInfo", logininfo);

            con = DBConnection.getDBConnection();
            stmt = con.createStatement();
            String sql = "select * from logincredentials";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String uname, pass, loginstatus;
                LoginInfo li = new LoginInfo();

                uname = rs.getString(1);
                li.setUname(uname);
                pass = rs.getString(2);
                li.setPass(pass);
                loginstatus = rs.getString(3);
                li.setLoginstatus(loginstatus);

                logininfo.add(li);

            }

            RequestDispatcher disp = request.getRequestDispatcher("/displayLoginRecords.jsp");
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

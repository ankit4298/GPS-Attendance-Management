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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ANKIT
 */
public class LoginWebsite extends HttpServlet {

    int checkFlag = 0;
    int validate=0;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String eid = request.getParameter("eid").toLowerCase();
            String pass = request.getParameter("password");

            if (!eid.contains("e")) {
                checkFlag = 1;
            }else{
                validate=2;
            }

            if (checkFlag == 1) {
                validate = checkLogin(eid, pass);
            }
            
            if (validate == 1) { // success
                HttpSession session = request.getSession();
                session.setAttribute("eid", eid);

                RequestDispatcher disp = request.getRequestDispatcher("/index2.jsp");
                disp.forward(request, response);
            } else if(validate == 0) { // fails
                request.setAttribute("failedAttempt", "1");
                RequestDispatcher disp = request.getRequestDispatcher("/LoginPortal.jsp");
                disp.forward(request, response);

            } else if(validate == 2){
                request.setAttribute("failedAttempt", "2");
                RequestDispatcher disp = request.getRequestDispatcher("/LoginPortal.jsp");
                disp.forward(request, response);
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
            }

            if (dbpass.equals(pass)) {
                return 1;   // success
            } else {
                return 0;   // fails
            }

        } catch (SQLException ex) {
            Logger.getLogger(ValidateLogin.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }

    }

}

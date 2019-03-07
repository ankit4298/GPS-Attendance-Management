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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ANKIT
 */
public class RegisterPortal extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */

            String fn, mn, ln, gender, email, phno, address, workdept;
            String eid, pass;

            eid = request.getParameter("eid");
            pass = request.getParameter("password");

            fn = request.getParameter("firstname");
            mn = request.getParameter("middlename");
            ln = request.getParameter("lastname");
            gender = request.getParameter("gender");
            email = request.getParameter("email");
            workdept = request.getParameter("workdept");
            address = request.getParameter("address");
            phno = request.getParameter("phno");

            int regFlag = 0, loginFlag = 0;
            int alreadyRegistered = 0;

            try {
                loginFlag = registerLoginCredentials(eid, pass);

                if (loginFlag == 0) {
                    regFlag = registerEmployeeDetails(eid, fn, mn, ln, gender, email, workdept, address, phno);
                } else {
                    alreadyRegistered = 1;
                }

                
            } catch (SQLException ex) {
                Logger.getLogger(RegisterPortal.class.getName()).log(Level.SEVERE, null, ex);
            } finally {

                request.setAttribute("alreadyRegistered", alreadyRegistered);
                
                if (alreadyRegistered == 1) {
                    request.setAttribute("eid", null);
                } else {
                    request.setAttribute("eid", eid);
                }
                RequestDispatcher disp = request.getRequestDispatcher("/registrationSuccess.jsp");
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

    public int registerEmployeeDetails(String eid, String fn, String mn, String ln, String gender, String email, String workdept, String address, String phno) throws SQLException {

        Connection con = null;
        PreparedStatement ps = null;
        int clearFlag = 0;

        try {
            con = DBConnection.getDBConnection();
            String insertEmp_details = "insert into employee_details values (?,?,?,?,?,?,?,?,?)";
            ps = con.prepareStatement(insertEmp_details);
            ps.setString(1, eid);
            ps.setString(2, fn);
            ps.setString(3, mn);
            ps.setString(4, ln);
            ps.setString(5, gender);
            ps.setString(6, email);
            ps.setString(7, workdept);
            ps.setString(8, address);
            ps.setString(9, phno);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(RegisterPortal.class.getName()).log(Level.SEVERE, null, ex);
            clearFlag = 1;
        } finally {
            ps.close();
            con.close();
        }
        return clearFlag;
    }

    public int registerLoginCredentials(String eid, String pass) throws SQLException {
        Connection con = null;
        PreparedStatement ps = null;
        int clearFlag = 0;

        try {
            con = DBConnection.getDBConnection();
            String insertEmp_details = "insert into logincredentials (uname,pass) values (?,?)";
            ps = con.prepareStatement(insertEmp_details);
            ps.setString(1, eid);
            ps.setString(2, pass);
            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(RegisterPortal.class.getName()).log(Level.SEVERE, null, ex);
            clearFlag = 1;
        } finally {
            ps.close();
            con.close();
        }

        return clearFlag;
    }

}

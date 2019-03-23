/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;

/**
 *
 * @author ANKIT
 */
public class MarkingOutAttendance extends HttpServlet {
    
    Connection con;
    Statement stmt;
    PreparedStatement ps;
    
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            String eid=request.getParameter("eid");
            String latitude=request.getParameter("latitude");
            String longitude=request.getParameter("longitude");
            String outStatus=request.getParameter("outstatus");
            String jsonResponse="";
            
            try{
                // insert time and date from server
                TimeZone tz=TimeZone.getTimeZone("GMT+5:30");
                Calendar c=Calendar.getInstance(tz);
                
                String strTime=c.get(Calendar.HOUR_OF_DAY)+":"+c.get(Calendar.MINUTE)+":"+c.get(Calendar.SECOND);
                String strDate=c.get(Calendar.DAY_OF_MONTH)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.YEAR);

                con=DBConnection.getDBConnection();
                String sql="update attendance_details set latitude=?, longitude=?, lastupdate_time=?, outtime=?, out_status=? where eid=? and date=?";
                ps=con.prepareStatement(sql);
                ps.setDouble(1, Double.parseDouble(latitude));
                ps.setDouble(2, Double.parseDouble(longitude));
                ps.setString(3, strTime);   // last time
                ps.setString(4, strTime);   // out time
                ps.setInt(5, Integer.parseInt(outStatus));
                ps.setString(6, eid);
                ps.setString(7, strDate);
                int i=ps.executeUpdate();
                
                jsonResponse=outResponse(i);
                out.print(jsonResponse);
                
                
            } catch (SQLException ex) {
                Logger.getLogger(UpdateAttendance.class.getName()).log(Level.SEVERE, null, ex);
                
                out.print(ex);
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

    
    public String outResponse(int responseStatus) throws IOException{
        String jsonText;
        
        JSONObject jo=new JSONObject();
        jo.put("response",responseStatus);
        
        StringWriter out=new StringWriter();
        jo.writeJSONString(out);
        
        jsonText=out.toString();
        
        return jsonText;
    }
    
}

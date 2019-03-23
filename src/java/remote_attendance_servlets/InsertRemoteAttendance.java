/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package remote_attendance_servlets;

import database.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONObject;
import servlets.InsertAttendanceDetails;

/**
 *
 * @author ANKIT
 */
public class InsertRemoteAttendance extends HttpServlet {

    Connection con;
    Statement stmt;
    PreparedStatement ps;
    ResultSet rs;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String eid=request.getParameter("eid");
            String latitude=request.getParameter("latitude");
            String longitude=request.getParameter("longitude");
            String jsonResponse="";
            
            try{
                
                // insert date from server
                TimeZone tz=TimeZone.getTimeZone("GMT+5:30");
                Calendar c=Calendar.getInstance(tz);
                
                String strTime=c.get(Calendar.HOUR_OF_DAY)+":"+c.get(Calendar.MINUTE)+":"+c.get(Calendar.SECOND);
                String strDate=c.get(Calendar.DAY_OF_MONTH)+"/"+(c.get(Calendar.MONTH)+1)+"/"+c.get(Calendar.YEAR);
                
                con=DBConnection.getDBConnection();
                String sql="insert into remote_details (eid,date,intime,latitude,longitude) values (?,?,?,?,?)";
                ps=con.prepareStatement(sql);
                ps.setString(1,eid);
                ps.setString(2,strDate);
                ps.setString(3,strTime);
                ps.setDouble(4,Double.parseDouble(latitude));
                ps.setDouble(5,Double.parseDouble(longitude));
                int i = ps.executeUpdate();
                
                jsonResponse=insertResponse(i);
                
                out.print(jsonResponse);
                
            } catch (SQLException ex) {
                Logger.getLogger(InsertAttendanceDetails.class.getName()).log(Level.SEVERE, null, ex);
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

    public String insertResponse(int responseStatus) throws IOException{
        String jsonText;
        
        JSONObject jo=new JSONObject();
        jo.put("response",responseStatus);
        
        StringWriter out=new StringWriter();
        jo.writeJSONString(out);
        
        jsonText=out.toString();
        
        return jsonText;
    }
    
}

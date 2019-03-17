/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pure_java;

import database.DBConnection;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author ANKIT
 */
public class GetProfile {

    static Connection con;
    static Statement stmt;
    static ResultSet rs;

    public static ArrayList getProfile(String eid) {

        ArrayList ei = new ArrayList();
        try {
            con = DBConnection.getDBConnection();
            stmt = con.createStatement();
            String sql = "select * from employee_details";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {

                ei.add(rs.getString("eid"));
                ei.add(rs.getString("firstname"));
                ei.add(rs.getString("middlename"));
                ei.add(rs.getString("lastname"));
                ei.add(rs.getString("gender"));
                ei.add(rs.getString("email"));
                ei.add(rs.getString("workdept"));
                ei.add(rs.getString("address"));
                ei.add(rs.getString("phno"));

            }

        } catch (Exception e) {
            e.printStackTrace();
            ei=null;
        }

        return ei;
    }
}

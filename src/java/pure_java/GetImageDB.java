/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pure_java;

import database.DBConnection;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author ANKIT
 */
public class GetImageDB {

    public static byte[] getPhoto(String eid) {
        Blob image = null;
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        byte[] imgData = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            con = DBConnection.getDBConnection();

            stmt = con.createStatement();
            rs = stmt.executeQuery("select * from photo_db where eid='" + eid + "'");

            while (rs.next()) {
                image = rs.getBlob("image");
                imgData = image.getBytes(1, (int) image.length());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return imgData;
    }

}

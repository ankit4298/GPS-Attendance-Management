package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FetchRemoteEmployeeLocation {

    ArrayList<ArrayList> al;

    Connection con;
    Statement stmt;
    ResultSet rs;

    public FetchRemoteEmployeeLocation() {
        al = new ArrayList<>();
        con = DBConnection.getDBConnection();
    }

    public ArrayList<ArrayList> returnLocations() {

        ArrayList eid = new ArrayList();
        ArrayList lat = new ArrayList();
        ArrayList lng = new ArrayList();
        ArrayList locID = new ArrayList();

        try {
            stmt = con.createStatement();

            TimeZone tz = TimeZone.getTimeZone("GMT+5:30");
            Calendar c = Calendar.getInstance(tz);
            String strDate = c.get(Calendar.DAY_OF_MONTH) + "/" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.YEAR);

            String sql = "select eid,latitude,longitude,locationID from remote_details where date='" + strDate + "' and outtime=-1";
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                String db_eid = rs.getString("eid");
                double db_lat = rs.getDouble("latitude");
                double db_lng = rs.getDouble("longitude");
                int db_locID = rs.getInt("locationID");

                eid.add(db_eid);
                lat.add(db_lat);
                lng.add(db_lng);
                locID.add(db_locID);
            }

            al.add(eid);
            al.add(lat);
            al.add(lng);
            al.add(locID);

        } catch (SQLException ex) {
            Logger.getLogger(FetchEmployeeLocations.class.getName()).log(Level.SEVERE, null, ex);
        }

        return al;
    }

    public ArrayList<ArrayList> getCircleBounds() {

        ArrayList main = new ArrayList();
        ArrayList locID = new ArrayList();
        ArrayList sitename = new ArrayList();
        ArrayList lat = new ArrayList();
        ArrayList lng = new ArrayList();
        ArrayList radius = new ArrayList();

        try {
            con = DBConnection.getDBConnection();
            stmt = con.createStatement();
            
            String sql = "select * from remote_sites";
            rs = stmt.executeQuery(sql);
            
            while (rs.next()) {
                int db_locID = rs.getInt("locationID");
                String db_sitename = rs.getString("site_name");
                double db_lat = rs.getDouble("latitude");
                double db_lng = rs.getDouble("longitude");
                double db_radius = rs.getDouble("radius");

                locID.add(db_locID);
                sitename.add(db_sitename);
                lat.add(db_lat);
                lng.add(db_lng);
                radius.add(db_radius);
            }

            main.add(locID);
            main.add(sitename);
            main.add(lat);
            main.add(lng);
            main.add(radius);
            
        } catch (Exception e) {
            Logger.getLogger(FetchEmployeeLocations.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return main;
    }

}

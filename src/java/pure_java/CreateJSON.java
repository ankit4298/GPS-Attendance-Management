/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pure_java;

import database.FetchEmployeeLocations;
import database.FetchRemoteEmployeeLocation;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author ANKIT
 */
public class CreateJSON {

    public String createJSONemployees() throws IOException {

        ArrayList<ArrayList> al = new ArrayList<>();
        ArrayList tempList = new ArrayList();

        FetchEmployeeLocations fetchLoc = new FetchEmployeeLocations();
        al = fetchLoc.returnLocations();

        tempList = al.get(0);
        Object eid[] = tempList.toArray();

        tempList = al.get(1);
        Object lat[] = tempList.toArray();

        tempList = al.get(2);
        Object lng[] = tempList.toArray();

        Object[] latitude = lat;
        Object[] longitude = lng;
        Object[] empid = eid;

        String jsonText = "";

        JSONObject jo = new JSONObject();
        for (int i = 0; i < eid.length; i++) {

            JSONArray ja = new JSONArray();
            Map m = new LinkedHashMap(1);
            m.put("lat", latitude[i]);
            m.put("lng", longitude[i]);
            ja.add(m);  // put lat long in array of emp id
            jo.put(empid[i], ja); // put empid in json obj

        }

        StringWriter out = new StringWriter();
        jo.writeJSONString(out);
        jsonText = out.toString();

        return jsonText;
    }

    public String createJSONremoteEmployees() throws IOException {

        ArrayList<ArrayList> al = new ArrayList<>();
        ArrayList tempList = new ArrayList();

        FetchRemoteEmployeeLocation fetchRemote = new FetchRemoteEmployeeLocation();
        al = fetchRemote.returnLocations();

        tempList = al.get(0);
        Object eid[] = tempList.toArray();

        tempList = al.get(1);
        Object lat[] = tempList.toArray();

        tempList = al.get(2);
        Object lng[] = tempList.toArray();

        tempList = al.get(3);
        Object locID[] = tempList.toArray();

        Object[] latitude = lat;
        Object[] longitude = lng;
        Object[] empid = eid;
        Object[] locationID = locID;

        String jsonText = "";

        JSONObject jo = new JSONObject();
        for (int i = 0; i < eid.length; i++) {

            JSONArray ja = new JSONArray();
            Map m = new LinkedHashMap(2);
            m.put("lat", latitude[i]);
            m.put("lng", longitude[i]);
            m.put("locID", locationID[i]);
            ja.add(m);  // put lat long in array of emp id
            jo.put(empid[i], ja); // put empid in json obj

        }

        StringWriter out = new StringWriter();
        jo.writeJSONString(out);
        jsonText = out.toString();

        return jsonText;
    }

    public String createJSONremoteCoords() throws IOException {

        ArrayList<ArrayList> al = new ArrayList<>();
        ArrayList tempList = new ArrayList();

        FetchRemoteEmployeeLocation fetchRemote = new FetchRemoteEmployeeLocation();
        al = fetchRemote.getCircleBounds();

        tempList = al.get(0);
        Object locID[] = tempList.toArray();

        tempList = al.get(1);
        Object siteName[] = tempList.toArray();

        tempList = al.get(2);
        Object lat[] = tempList.toArray();

        tempList = al.get(3);
        Object lng[] = tempList.toArray();

        tempList = al.get(4);
        Object rad[] = tempList.toArray();

        Object[] locationID = locID;
        Object[] site_name = siteName;
        Object[] latitude = lat;
        Object[] longitude = lng;
        Object[] radius = rad;

        String jsonText = "";

//        int ctr = 0;
        JSONObject mainJO = new JSONObject();
        for (int i = 0; i < locationID.length; i++) {

            JSONArray innerJA = new JSONArray();
            Map m = new LinkedHashMap(3);
//            m.put("locationID", locationID[i]);
            m.put("site_name", site_name[i]);
            m.put("latitude", latitude[i]);
            m.put("longitude", longitude[i]);
            m.put("radius", radius[i]);
            innerJA.add(m);
            mainJO.put(locationID[i], innerJA);
//            ctr++;

//            JSONArray ja = new JSONArray();
//            Map m = new LinkedHashMap(2);
//            m.put("lat", latitude[i]);
//            m.put("lng", longitude[i]);
//            m.put("radius", radius[i]);
//            ja.add(m);  // put lat long in array of emp id
//            jo.put(locationID[i], ja); // put empid in json obj
        }

        StringWriter out = new StringWriter();
        mainJO.writeJSONString(out);
        jsonText = out.toString();

        return jsonText;
    }

    public String loginResponse(String username, int verify, int loginstatus) throws IOException {
        String jsonText;

        JSONObject mainJO = new JSONObject();
        JSONObject loginInfo = new JSONObject();

        loginInfo.put("username", username);
        loginInfo.put("loginverified", verify);
        loginInfo.put("loginstatus", loginstatus);

        mainJO.put("loginInfo", loginInfo);

        StringWriter out = new StringWriter();
        mainJO.writeJSONString(out);

        jsonText = out.toString();

        return jsonText;
    }

    public String loginResponse(String username, int verify, int loginstatus, ArrayList al) throws IOException {
        String jsonText;

        // columns to fetch and stored in JSON from employee_details table needs to be entered here
        String key[] = {"eid", "firstname", "middlename", "lastname", "gender", "email", "address", "phno"};
        int keyc = 0;

        JSONObject mainJO = new JSONObject();
        JSONObject loginInfo = new JSONObject();
        JSONObject userInfo = new JSONObject();

        loginInfo.put("username", username);
        loginInfo.put("loginverified", verify);
        loginInfo.put("loginstatus", loginstatus);

        for (Object x : al) {
            userInfo.put(key[keyc], x);
            keyc++;
        }

        mainJO.put("loginInfo", loginInfo);
        mainJO.put("userInfo", userInfo);

        StringWriter out = new StringWriter();
        mainJO.writeJSONString(out);

        jsonText = out.toString();

        return jsonText;
    }

    public String imageResponse(String eid, String endcodedImage) throws IOException {
        String jsonText;

        JSONObject jo = new JSONObject();
        jo.put("eid", eid);
        jo.put("encoded image", endcodedImage);

        StringWriter out = new StringWriter();
        jo.writeJSONString(out);

        jsonText = out.toString();

        return jsonText;
    }
}

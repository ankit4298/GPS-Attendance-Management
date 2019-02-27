/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pure_java;

import database.FetchEmployeeLocations;
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
    
//    double lat[] = {20.013601,  20.014282, 20.014316, 20.013951, 20.01434923};
//    double lng[] = {73.764862, 73.762663, 73.764120, 73.763843, 73.76441761};
//    String eid[] = {"e100", "e101", "e102", "e103", "e104"};
    
    
    // Pass DATABASE lat,lng,eid to instance variables
    
    public String createJSONemployees() throws IOException {

        ArrayList<ArrayList> al=new ArrayList<>();
        ArrayList tempList=new ArrayList();
        
        FetchEmployeeLocations fetchLoc=new FetchEmployeeLocations();
        al=fetchLoc.returnLocations();
        
        
        tempList=al.get(0);
        Object eid[]=tempList.toArray();
        
        tempList=al.get(1);
        Object lat[]=tempList.toArray();
        
        tempList=al.get(2);
        Object lng[]=tempList.toArray();
        
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
    
    public String createJSONresponse(String name) throws IOException{
        String jsonText;
        JSONObject jo=new JSONObject();
        jo.put("name", name);
        jo.put("ID", new Integer(400));
        
        //for address storing
        Map m=new LinkedHashMap(3);
        m.put("country", "INDIA");
        m.put("state", "Maharashtra");
        m.put("city", "Nashik");
        
        jo.put("address", m);
        
        StringWriter out=new StringWriter();
        jo.writeJSONString(out);
        
        jsonText=out.toString();
        
        return jsonText;
    }
    
    public String loginResponse(String username,int verify,int loginstatus) throws IOException{
        String jsonText;
        
        JSONObject jo=new JSONObject();
        jo.put("username",username);
        jo.put("loginverified", verify);
        jo.put("loginstatus", loginstatus);
        
        StringWriter out=new StringWriter();
        jo.writeJSONString(out);
        
        jsonText=out.toString();
        
        return jsonText;
    }
    
    public String imageResponse(String eid,String endcodedImage) throws IOException{
        String jsonText;
        
        JSONObject jo=new JSONObject();
        jo.put("eid",eid);
        jo.put("encoded image", endcodedImage);
        
        StringWriter out=new StringWriter();
        jo.writeJSONString(out);
        
        jsonText=out.toString();
        
        return jsonText;
    }
}

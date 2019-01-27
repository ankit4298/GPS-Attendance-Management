/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pure_java;

import java.io.IOException;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
/**
 *
 * @author ANKIT
 */
public class CreateJSON {
    
    double lat[] = {20.013601,  20.014282, 20.014316, 20.013951};
    double lng[] = {73.764862, 73.762663, 73.764120, 73.763843};
    String eid[] = {"e100", "e101", "e102", "e103"};
    
    
    // Pass DATABASE lat,lng,eid to instance variables
    
    public String createJSONemployees() throws IOException {

        double[] latitude = lat;
        double[] longitude = lng;
        String[] empid = eid;

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
    
    public String loginResponse(int verify,int loginstatus) throws IOException{
        String jsonText;
        
        JSONObject jo=new JSONObject();
        jo.put("loginverified", verify);
        jo.put("loginstatus", loginstatus);
        
        StringWriter out=new StringWriter();
        jo.writeJSONString(out);
        
        jsonText=out.toString();
        
        return jsonText;
    }
}

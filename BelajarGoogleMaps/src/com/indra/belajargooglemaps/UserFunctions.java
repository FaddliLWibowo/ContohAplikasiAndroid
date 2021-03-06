package com.indra.belajargooglemaps;

import java.util.ArrayList;
import java.util.List;
 
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
 
import android.content.Context;
import android.util.Log;
 
public class UserFunctions {
     
    private JSONParser jsonParser;
     
    private static String loginURL = "http://bluespy.ws/ta/";
    private static String registerURL = "http://bluespy.ws/ta/";
    private static String updateLokasiURL = "http://bluespy.ws/ta/update.php";
     
    private static String login_tag = "login";
    private static String register_tag = "register";
    private static String update_lokasi = "updatelokasi";
     
    // constructor
    public UserFunctions(){
        jsonParser = new JSONParser();
    }
     
    public JSONObject updateLokasi(String id, String latitude, String longitude){
    	List<NameValuePair> params= new ArrayList<NameValuePair>();
    	params.add(new BasicNameValuePair("tag", update_lokasi));
    	params.add(new BasicNameValuePair("id", id));
    	params.add(new BasicNameValuePair("latitude", latitude));
    	params.add(new BasicNameValuePair("longitude", longitude));
    	JSONObject json = jsonParser.getJSONFromUrl(updateLokasiURL, params);
    	
    	return json;
    }
    
    /**
     * function make Login Request
     * @param email
     * @param password
     * */
    public JSONObject loginUser(String email, String password){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", login_tag));
        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("password", password));
        JSONObject json = jsonParser.getJSONFromUrl(loginURL, params);
        // return json
        //Log.e("JSON", json.toString());
        return json;
    }
     
    /**
     * function make Login Request
     * @param name
     * @param email
     * @param password
     * */
    public JSONObject registerUser(String email, String password, String nama, String alamat, String no_telp){
        // Building Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("tag", register_tag));
        params.add(new BasicNameValuePair("email", email));
        params.add(new BasicNameValuePair("password", password));
        params.add(new BasicNameValuePair("nama", nama));
        params.add(new BasicNameValuePair("alamat", alamat));
        params.add(new BasicNameValuePair("no_telp", no_telp));
         
        // getting JSON Object
        JSONObject json = jsonParser.getJSONFromUrl(registerURL, params);
        // return json
        return json;
    }
     
    /**
     * Function get Login status
     * */
    public boolean isUserLoggedIn(Context context){
        DatabaseHandler db = new DatabaseHandler(context);
        int count = db.getRowCount();
        if(count > 0){
            // user logged in
            return true;
        }
        return false;
    }
     
    /**
     * Function to logout user
     * Reset Database
     * */
    public boolean logoutUser(Context context){
        DatabaseHandler db = new DatabaseHandler(context);
        db.resetTables();
        return true;
    }
     
}
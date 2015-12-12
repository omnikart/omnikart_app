package com.omnikart.www.omnikartseller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.omnikart.www.omnikartseller.Engine.Auth_Token;
import com.omnikart.www.omnikartseller.Helper.Preference_Helper;
import com.omnikart.www.omnikartseller.Network.Volley.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Yash Gupta on 07-12-2015.
 */
public class Main_Activity extends AppCompatActivity {
    Preference_Helper prefs;
    Boolean isClicked;
 protected void onCreate(Bundle savedInstanceState){
     super.onCreate(savedInstanceState);
     setContentView(R.layout.main_activity);
     prefs = new Preference_Helper(this);
     isClicked = prefs.load("isClicked",false);
     String url = "http://testing.omnikart.com/index.php?route=rest/tokencheck/check";
     if(isClicked){
         // Token check
         VolleySingleton.getInstance(Main_Activity.this).post(url, new Response.Listener<JSONObject>() {
             @Override
             public void onResponse(JSONObject response) {
                 String success = "";
                 try {
                     if (response.has("success")) {
                         success = response.getString("success");
                         Log.d("Volley main success", success);


                         if (success.equals("true")) {
                             Log.d("Volley main success", "starting main login");
                             Intent i = new Intent(Main_Activity.this, Main_Login_Page.class);
                             startActivity(i);
                         } else {
                             Log.d("Volley main success", "starting auth");
                             Auth_Token auth_token = new Auth_Token(Main_Activity.this);
                         }
                     }

                 } catch (JSONException e) {
                     e.printStackTrace();
                 }
             }
         }, new Response.ErrorListener() {
             @Override
             public void onErrorResponse(VolleyError error) {
                 Toast.makeText(getApplicationContext(), "Please check your internet connection", Toast.LENGTH_SHORT).show();
                 Intent intent = new Intent(Main_Activity.this,Activity_Error.class);
                 startActivity(intent);
                 finish();

             }
         });
     }
     else {
         Intent intent = new Intent(Main_Activity.this, Activity_Fragments_Firsttime.class);
         startActivity(intent);
     }
     setContentView(R.layout.main_activity);
      Main_Activity.this.finish();

 }
}

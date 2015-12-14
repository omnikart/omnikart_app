package com.omnikart.www.omnikartseller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.omnikart.www.omnikartseller.Engine.Auth_Token;
import com.omnikart.www.omnikartseller.Engine.Background_Task;
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
     prefs = new Preference_Helper(Main_Activity.this);
     isClicked = prefs.load("isClicked", false);
     if (isClicked) {
         Background_Task background_task = new Background_Task(Main_Activity.this);
         //finish();
     }
     else {
         Intent intent = new Intent(Main_Activity.this, Activity_Fragments_Firsttime.class);
         startActivity(intent);
         finish();
     }

 }
}

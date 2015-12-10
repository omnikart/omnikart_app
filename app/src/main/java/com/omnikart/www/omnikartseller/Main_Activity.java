package com.omnikart.www.omnikartseller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.omnikart.www.omnikartseller.Engine.Auth_Token;
import com.omnikart.www.omnikartseller.Helper.Preference_Helper;

/**
 * Created by Yash Gupta on 07-12-2015.
 */
public class Main_Activity extends AppCompatActivity {
    Preference_Helper prefs;
    Boolean isClicked;
 protected void onCreate(Bundle savedInstanceState){
     super.onCreate(savedInstanceState);
     prefs = new Preference_Helper(this);
     isClicked = prefs.load("isClicked",false);
     if(isClicked){
       //  Auth_Token auth_token = new Auth_Token(Main_Activity.this);
         Intent i = new Intent(this,Main_Login_Page.class);
         startActivity(i);
                      }
     else {
         Intent intent = new Intent(Main_Activity.this, Activity_Fragments_Firsttime.class);
         startActivity(intent);
     }
     setContentView(R.layout.main_activity);
      Main_Activity.this.finish();

 }
}

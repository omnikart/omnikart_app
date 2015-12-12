package com.omnikart.www.omnikartseller.Helper;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.omnikart.www.omnikartseller.Activity_Listview_Orders;
import com.omnikart.www.omnikartseller.Main_Login_Page;

public class Authorization extends AppCompatActivity{

    String authorization;
    Preference_Helper preference_helper;
    public  Authorization(Context context) {
        preference_helper = new Preference_Helper(context);
    }

    public String getAuthorization(){
        String access_token = preference_helper.load("access_token","");
        String token_type = preference_helper.load("token_type","");
        authorization = (token_type + " " + access_token);
        Log.d("volley auth getauth",authorization);
            return authorization;
    }

    public void setAuthorization(String access_token,String expires_in,String token_type){
        preference_helper.save("access_token", access_token);
        preference_helper.save("expires_in",expires_in);
        preference_helper.save("token_type",token_type);
        Log.d("volley auth setauth", access_token + expires_in + token_type);
        Log.d("volley set auth",preference_helper.load("access_token","")+preference_helper.load("token_type",""));
    }
}

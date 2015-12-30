package com.omnikart.www.omnikartseller.Engine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.omnikart.www.omnikartseller.Front_Page;
import com.omnikart.www.omnikartseller.Helper.Authorization;
import com.omnikart.www.omnikartseller.Helper.Preference_Helper;
import com.omnikart.www.omnikartseller.Main_Activity;
import com.omnikart.www.omnikartseller.Main_Login_Page;
import com.omnikart.www.omnikartseller.Network.Volley.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Yash Gupta on 17-12-2015.
 */
public class Background_Login {
    JSONObject params;
    String url;
    String success="";
    String authorization="";
    ProgressBar progressBar;
    Boolean isLoggedIn;
    Preference_Helper preference_helper;
    final Context mContext;
    public Background_Login(final Context context) {
        this.mContext = context;
        url = "http://testing.omnikart.com/index.php?route=rest/login/login";
        params = new JSONObject();
        Authorization auth = new Authorization(mContext);
        authorization= auth.getAuthorization();
        Log.d("Voleey 1 Auth",authorization);
        preference_helper = new Preference_Helper(mContext);
        isLoggedIn = preference_helper.load("isLoggedIn",false);
        if (isLoggedIn) {
            try {
                Log.d("islogeedin", "is true");
                params.put("email", preference_helper.load("email", ""));
                params.put("password", preference_helper.load("password", ""));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d("islogeedin", "Before Connection, params");
            Log.d("islogeedin", params.toString());

            ConnectionLogIn();
           // ((Activity)context).finish();
        }
        else{
            Log.d("back login","goin to main login");
            Intent intent = new Intent(mContext, Main_Login_Page.class);
            mContext.startActivity(intent);
            ((Activity)context).finish();
        }
    }
    public void ConnectionLogIn(){


        //show_data.append(params.toString());
//        progressBar.setVisibility(View.VISIBLE);

        VolleySingleton volleySingleton = new VolleySingleton(mContext);
        volleySingleton.postWithHeadersWithParams(url, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    if (response.has("success")) {
                        success = response.getString("success");  }
                    if (response.has("error")) {
                        Log.d("vo","v");
                        Log.d("Volley 1 error",response.getString("error")); }
                    if(response.has("data")) {
                        JSONObject data = response.getJSONObject("data");
                        String[] data_array = new String[]{"customer_id","customer_group_id","store_id",
                                "firstname","lastname","email","telephone","fax","cart","wishlist","newsletter",
                                "address_id","ip","status","approved","safe","date_added","session","custom_fields",
                                "account_custom_field"};
                        for (int i=0;i<data_array.length;i++){
                            if (data.has(data_array[i])){
                                preference_helper.save(data_array[i],data.getString(data_array[i]));
                            } }  }
                    Log.d("Volley 1","Site accessed");
                    Log.d("Volley 1", success);

                    LogIn(success);
//                    progressBar.setVisibility(View.GONE);

                } catch (JSONException e) {
                    e.printStackTrace();
               //     progressBar.setVisibility(View.GONE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Volley 1","Site not accessed");
                // Log.d("Volley 1",error.getMessage());
                Toast.makeText(mContext, "Error in connection", Toast.LENGTH_SHORT).show();
               // progressBar.setVisibility(View.GONE);
                // start main login page
                Intent intent = new Intent(mContext, Main_Login_Page.class);
                Log.d("Location", "new activity");

                mContext.startActivity(intent);
                ((Activity)mContext).finish();

            }
        });

    }
    public void LogIn(String req){
        if(req.equals("true")) {
            Intent intent = new Intent(mContext, Front_Page.class);
            Log.d("Location", "new activity");

            mContext.startActivity(intent);
            ((Activity)mContext).finish();

        }

        else {
            Toast.makeText(mContext,"Invalid Username or Password",Toast.LENGTH_SHORT).show();
            Log.d("Location", "Toast");
            Log.d("isloggedin", "is logged in is " + isLoggedIn);
            //start main login page
            Intent intent = new Intent(mContext, Main_Login_Page.class);
            Log.d("Location", "new activity");

            mContext.startActivity(intent);
            ((Activity)mContext).finish();
        }

}
}

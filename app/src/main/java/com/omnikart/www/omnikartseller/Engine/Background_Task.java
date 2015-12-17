package com.omnikart.www.omnikartseller.Engine;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.omnikart.www.omnikartseller.Activity_Error;
import com.omnikart.www.omnikartseller.Helper.Preference_Helper;
import com.omnikart.www.omnikartseller.Main_Login_Page;
import com.omnikart.www.omnikartseller.Network.Volley.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Yash Gupta on 14-12-2015.
 */
public class Background_Task {
Context mContext;
      public Background_Task(final Context context) {
          this.mContext = context;
        String url = "http://testing.omnikart.com/index.php?route=rest/tokencheck/check";
            // Token check
            VolleySingleton volleySingleton = new VolleySingleton(context);
            volleySingleton.postWithHeaders(url, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    String success = "";
                    try {
                        if (response.has("success")) {
                            success = response.getString("success");
                            Log.d("Volley main success", success);
                            if (success.equals("true")) {
                                Log.d("Volley main success", "starting main login");
                                Background_Login background_login = new Background_Login(mContext);
                                /*Intent i = new Intent(mContext, Background_Login.class);
                                mContext.startActivity(i);*/
                               // ((Activity)context).finish();

                            } else {
                                Log.d("Volley main success", "starting auth");
                                Auth_Token auth_token = new Auth_Token(mContext);
                               // ((Activity)context).finish();
                            }
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(mContext, "Please check your internet connection", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(mContext, Activity_Error.class);
                    mContext.startActivity(intent);
                    ((Activity) context).finish();
                }
            });
        }
    }


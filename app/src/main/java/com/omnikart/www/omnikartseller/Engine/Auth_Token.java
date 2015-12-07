package com.omnikart.www.omnikartseller.Engine;

import android.app.Application;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.omnikart.www.omnikartseller.Helper.Preference_Helper;
import com.omnikart.www.omnikartseller.Network.Volley.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yash Gupta on 07-12-2015.
 */
public class Auth_Token extends AppCompatActivity{
    String url;
    Preference_Helper prefs;
    public Auth_Token(final Context context){
//        context = getApplicationContext();
        url ="http://testing.omnikart.com/index.php?route=feed/rest_api/gettoken&grant_type=client_credentials";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,url,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        String access_token="";
                        String expires_in="";
                        String token_type="";
                        try {
                            if(response.has("access_token")){
                            access_token = response.getString("access_token");}
                            if(response.has("expires_in")){
                                expires_in = response.getString("expires_in");}
                            if(response.has("token_type")){
                                token_type = response.getString("token_type");}
                            Log.d("Volley",access_token+"   "+expires_in+  "  "  +token_type+" is null");
                            prefs = new Preference_Helper(context);
                            prefs.save("access_token",access_token);
                            prefs.save("expires_in",expires_in);
                            prefs.save("token_type",token_type);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<>();
                // cookie = headers.get("Set-Cookie");
                headers.put("Authorization","Basic OTgxMjAxOm1hbmRhcnpvcGU=");
                Log.d("Volley full header", String.valueOf(headers)+"   ***  dsf");

                return headers;
            }

            /*@Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                try {
                    Log.d("Volley", "inside parseNetwork");
                    response.headers.put("Authorization","Basic OTgxMjAxOm1hbmRhcnpvcGU=");
                    String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                    Log.d("Volley json", json);
                    Log.d("Volley Set-Auth", " #### set auth " );
                   // cookie = response.headers.get("Set-Cookie") ;

                    Log.d("Volley full header", String.valueOf(response.headers));
                    return Response.success(new JSONObject(json), HttpHeaderParser.parseCacheHeaders(response));

                } catch (UnsupportedEncodingException e) {
                    Log.d("Volley full header", "1");
                    e.printStackTrace();
                    return Response.error(new ParseError(e));
                } catch (JSONException e) {
                    Log.d("Volley full header", e.getMessage());
                    e.printStackTrace();
                    return  Response.error(new ParseError(e));
                }
            }*/
        };
        VolleySingleton.getInstance(context).addToRequestQueue(jsonObjectRequest);
    }
}

package com.omnikart.www.omnikartseller.Network.Volley;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;

import org.json.JSONObject;

import java.util.Map;

/**
 * Created by Yash Gupta on 10-12-2015.
 */
public class ApiHelper {

 public static Context mContext;

    public ApiHelper(Context context){
        this.mContext = context;
            }
    public static void makeRequest(int paramInt,String paramString,VolleyApiListeners paramVolleyApiListeners){
            makeRequest(paramInt,paramString,null,paramVolleyApiListeners);
    }
    public static void makeRequest(int paramInt,String paramString,JSONObject paramJSONObject,VolleyApiListeners paramVolleyApiListeners){
        Log.d("URL", paramString.toString());
        if (paramVolleyApiListeners == null) {
            throw new IllegalArgumentException("Volley Api listeners can not be null");
        }
        if (TextUtils.isEmpty(paramString)) {
            throw new IllegalArgumentException("Request url can not be empty");
        }
        VolleyRequest paramRequest = new VolleyRequest(paramInt, paramString, paramJSONObject, paramVolleyApiListeners);
        //paramRequest.setHeader(paramMap);
        VolleySingleton.getInstance(mContext).addToRequestQueue(paramRequest);
    }
}

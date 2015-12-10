package com.omnikart.www.omnikartseller.Network.Volley;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.omnikart.www.omnikartseller.Helper.Authorization;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Yash Gupta on 10-12-2015.
 */
public class VolleyRequest extends JsonObjectRequest {
    private Map<String,String> mHeader;
    private Context context;
    String authorization="";

    public VolleyRequest(int paramInt,String paramString, JSONObject paramJSONObject, VolleyApiListeners paramVolleyApiListeners){
        super(paramInt,paramString,paramJSONObject,paramVolleyApiListeners,paramVolleyApiListeners);
        Authorization auth = new Authorization(context);
        authorization= auth.getAuthorization();
    }
    public VolleyRequest(int paramInt,String paramString, VolleyApiListeners paramVolleyApiListeners){
        super(paramInt,paramString,paramVolleyApiListeners,paramVolleyApiListeners);
        Authorization auth = new Authorization(context);
        authorization= auth.getAuthorization();

    }
    @Override
    public Map<String,String> getHeaders() throws AuthFailureError{
        HashMap<String, String> headers = new HashMap<String, String>();
        headers.put("Authorization", authorization);
        Log.d("Volley 1jsj Auth new", authorization);
        Log.d("Volley 1lkl full header", String.valueOf(headers) + "###");
        return headers;
    }

  /*  public Map<String, String> getHeaders() throws AuthFailureError {
        if (this.mHeader != null) {
            return this.mHeader;
        }
        return null;
    }*/
    public void setHeader(Map<String, String> paramMap)
    {
        this.mHeader = paramMap;
    }
        }
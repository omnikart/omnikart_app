package com.omnikart.www.omnikartseller.Network.Volley;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.omnikart.www.omnikartseller.Helper.Authorization;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import javax.xml.transform.ErrorListener;


public class VolleySingleton {

    private static VolleySingleton instance;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;
    String authorization = "";

    public VolleySingleton(Context context) {
        requestQueue = Volley.newRequestQueue(context);
        Authorization auth = new Authorization(context);
        authorization= auth.getAuthorization();
        Log.d("volley singleton const",authorization);

        imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(20);
            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }
            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        });
    }

    public static VolleySingleton getInstance(Context context) {
        if (instance == null) {
            instance = new VolleySingleton(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req) {
       // req.setTag("App");
        getRequestQueue().add(req);
    }

    public void postWithParams(String paramString,JSONObject paramJSONObject,Response.Listener<JSONObject> listener,Response.ErrorListener errorListener){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,paramString,paramJSONObject, listener,errorListener){
            @Override
            public Map<String,String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", authorization);
                Log.d("Volley 1jsj Auth new", authorization);
                Log.d("Volley 1lkl full header", String.valueOf(headers) + "###");
                return headers;
            }

        };
        addToRequestQueue(jsonObjectRequest);
    }
    public void post(String paramString,Response.Listener<JSONObject> listener,Response.ErrorListener errorListener){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,paramString, listener,errorListener){
            @Override
            public Map<String,String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", authorization);
                Log.d("Volley 1jsj Auth new", authorization);
                Log.d("Volley 1lkl full header", String.valueOf(headers) + "###");
                return headers;
            }

        };
        addToRequestQueue(jsonObjectRequest);
    }
    public void get(String paramString,Response.Listener<JSONObject> listener,Response.ErrorListener errorListener){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,paramString, listener,errorListener){
            @Override
            public Map<String,String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Authorization", authorization);
                Log.d("Volley 1jsj Auth new", authorization);
                Log.d("Volley 1lkl full header", String.valueOf(headers) + "###");
                return headers;
            }

        };
        addToRequestQueue(jsonObjectRequest);
    }
}


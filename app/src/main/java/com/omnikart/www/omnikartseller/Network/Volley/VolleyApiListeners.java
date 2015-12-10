package com.omnikart.www.omnikartseller.Network.Volley;

import com.android.volley.Response;

import org.json.JSONObject;

/**
 * Created by Yash Gupta on 10-12-2015.
 */
public abstract class VolleyApiListeners
        implements Response.Listener<JSONObject>, Response.ErrorListener {}

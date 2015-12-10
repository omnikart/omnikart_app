package com.omnikart.www.omnikartseller.Utils;

import android.content.Context;
import android.util.Log;

import com.omnikart.www.omnikartseller.Helper.Authorization;

import java.util.HashMap;

/**
 * Created by Yash Gupta on 10-12-2015.
 */
public class ServerRequestUtils {
    public static HashMap<String, String> getAccessHeader(Context paramContext)
    {
        HashMap<String ,String> paramMap = new HashMap<>();
        Authorization auth = new Authorization(paramContext);
        String authorization = auth.getAuthorization();
        Log.d("Authorization>", authorization);
        paramMap.put("Authorization", authorization);
        return paramMap;
    }

}

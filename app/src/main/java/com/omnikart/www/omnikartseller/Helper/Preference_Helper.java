package com.omnikart.www.omnikartseller.Helper;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.Editable;

/**
 * Created by Yash Gupta on 28-11-2015.
 */
public class Preference_Helper {
    private SharedPreferences prefs;
    private static final String file_name1 = "com.omnikart.www.omnikartseller.firsttime.saveddata";

    public Preference_Helper(Context context)
    {
        prefs = context.getSharedPreferences(file_name1, Context.MODE_PRIVATE);
    }

    public void save(String key , Boolean value)
    {
        prefs.edit().putBoolean(key, value).commit();

    }
    public void save(String key,String value){prefs.edit().putString(key, value).commit();}
    public Boolean load(String key, Boolean defValue)
    {
        return prefs.getBoolean(key, defValue);
    }
    public String load(String key, String defValue)
    {
        return prefs.getString(key,defValue);
    }


}

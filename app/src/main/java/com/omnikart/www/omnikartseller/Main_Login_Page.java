package com.omnikart.www.omnikartseller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.omnikart.www.omnikartseller.Helper.Authorization;
import com.omnikart.www.omnikartseller.Helper.Preference_Helper;
import com.omnikart.www.omnikartseller.Network.Volley.ApiHelper;
import com.omnikart.www.omnikartseller.Network.Volley.VolleyApiListeners;
import com.omnikart.www.omnikartseller.Network.Volley.VolleyRequest;
import com.omnikart.www.omnikartseller.Network.Volley.VolleySingleton;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


public class Main_Login_Page extends AppCompatActivity{
    EditText email;
    EditText password;
    Button btn_login;
    TextView show_data;
    CheckBox checkBox;
    JSONObject params;
    String url;
    String access_token = "";
    String token_type = "";
    String success="";
    String authorization = "";
    ProgressBar progressBar;
    Boolean isLoggedIn;
    Preference_Helper preference_helper;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_login_page);
        email = (EditText) findViewById(R.id.editText_email);
        password = (EditText) findViewById(R.id.editText_password);
        btn_login = (Button) findViewById(R.id.button_loginpage);
        show_data = (TextView) findViewById(R.id.textView_query);
        checkBox = (CheckBox) findViewById(R.id.checkBox_keep_me_logged_in);
        progressBar = (ProgressBar) findViewById(R.id.progressBar_login);
        url = "http://testing.omnikart.com/index.php?route=rest/login/login";
        params = new JSONObject();
        Authorization auth = new Authorization(Main_Login_Page.this);
        authorization= auth.getAuthorization();
        Log.d("Voleey 1 Auth",authorization);
        preference_helper = new Preference_Helper(Main_Login_Page.this);
        isLoggedIn = preference_helper.load("isLoggedIn",false);
        Log.d("islogged in","is logged is "+isLoggedIn);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isLoggedIn = false ;
                preference_helper.save("isLoggedIn",false);
                Log.d("isloggedin","setting is logged in "+isLoggedIn);
                try {
                    params.put("email", email.getText());
                    params.put("password", password.getText());
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                ConnectionLogIn();
            }
        });
        if (isLoggedIn){
            try {
                Log.d("islogeedin", "is true");
                params.put("email", preference_helper.load("email",""));
                params.put("password", preference_helper.load("password", ""));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d("islogeedin", "Before Connection, params");
            Log.d("islogeedin",params.toString());

            ConnectionLogIn();
        }

      }

    public void ConnectionLogIn() {

        show_data.append(params.toString());
        progressBar.setVisibility(View.VISIBLE);

        VolleySingleton volleySingleton = new VolleySingleton(Main_Login_Page.this);
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
                    Log.d("Volley 1", success );

                    LogIn(success);
                    progressBar.setVisibility(View.GONE);

                } catch (JSONException e) {
                    e.printStackTrace();
                    progressBar.setVisibility(View.GONE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Volley 1","Site not accessed");
                // Log.d("Volley 1",error.getMessage());
                Toast.makeText(getApplicationContext(),"Error in connection",Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });

    }

    public void LogIn(String req){
        if(req.equals("true")){
            Intent intent = new Intent(Main_Login_Page.this,Front_Page.class);
            Log.d("Location", "new activity");

          if (checkBox.isChecked()){
           if(!isLoggedIn){
            preference_helper.save("isLoggedIn",true);
            preference_helper.save("email", email.getText().toString());
            preference_helper.save("password",password.getText().toString());
            Log.d("islogeedin", "original data saved");}}
            Main_Login_Page.this.startActivity(intent);
            Main_Login_Page.this.finish();

        }
        else {
            Toast.makeText(getApplicationContext(),"Invalid Username or Password",Toast.LENGTH_SHORT).show();
            Log.d("Location", "Toast");
            Log.d("isloggedin","is logged in is "+isLoggedIn);
        }
    }
}

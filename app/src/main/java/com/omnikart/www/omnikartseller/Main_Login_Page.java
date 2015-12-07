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


public class Main_Login_Page extends AppCompatActivity{
    EditText username;
    EditText password;
    Button btn_login;
    TextView show_data;
    CheckBox checkBox;
    JSONObject params;
    String url;
    String access_token = "";
    String token_type = "";
    String success="";
    String customerID="";
    String mainheader = "";
    String cookie;
    ProgressBar progressBar;
    Boolean isLoggedIn;
    Preference_Helper preference_helper;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_login_page);
        username = (EditText) findViewById(R.id.editText_username);
        password = (EditText) findViewById(R.id.editText_password);
        btn_login = (Button) findViewById(R.id.button_loginpage);
        show_data = (TextView) findViewById(R.id.textView_query);
        checkBox = (CheckBox) findViewById(R.id.checkBox_keep_me_logged_in);
        progressBar = (ProgressBar) findViewById(R.id.progressBar_login);
       url = "http://testing.omnikart.com/index.php?route=rest/login/login";
       // url ="http://testing.omnikart.com/index.php?route=app/login";
      //  url = "https://www.omnikart.com/index.php?route=app/login";
        params = new JSONObject();
         preference_helper = new Preference_Helper(Main_Login_Page.this);
        access_token = preference_helper.load("access_token","");
        token_type = preference_helper.load("token_type","");
        mainheader = token_type+" "+access_token;
        isLoggedIn = preference_helper.load("isLoggedIn",false);
        Log.d("islogged in","is logged is "+isLoggedIn);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isLoggedIn = false ;
                preference_helper.save("isLoggedIn",false);
                Log.d("isloggedin","setting is logged in "+isLoggedIn);
                try {
                    params.put("email", username.getText());
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
                params.put("email", preference_helper.load("username",""));
                params.put("password", preference_helper.load("password", ""));
             //   preference_helper.save("isLoggedIn", false);
             //   Log.d("isloggedin", "set "+String.valueOf(preference_helper.load("isLoggedIn", false)));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            Log.d("islogeedin", "Before Connection, params");
            Log.d("islogeedin",params.toString());

            ConnectionLogIn();
        }

      }

    public void ConnectionLogIn() {

      //  final RequestQueue requestQueue = Volley.newRequestQueue(Main_Login_Page.this);

                show_data.append(params.toString());
                progressBar.setVisibility(View.VISIBLE);
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, params,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    String cookie = "";
                                    if (response.has("success")) {
                                        success = response.getString("success");  }
                                    if (response.has("cookie")) {
                                        cookie = response.getString("cookie");   }
                                    if (response.has("customer_id")) {
                                        customerID = response.getString("customer_id");  }
                                    Log.d("Volley","Site accessed");

                                    Log.d("Volley", success + "   1st   \n" + cookie + "    2nd     \n"+customerID);

                                    LogIn(success);
                                    progressBar.setVisibility(View.GONE);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    progressBar.setVisibility(View.GONE);
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.d("Volley","Site not accessed");
                                // Log.d("Volley",error.getMessage());
                                Toast.makeText(getApplicationContext(),"Error in connection",Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        })
                {
                    @Override
                public Map<String,String> getHeaders() throws AuthFailureError{
                        HashMap<String, String> headers = new HashMap<String, String>();
                        headers.put("Authorization",mainheader);
//                    Log.d("Volley cookie", cookie);
                    Log.d("Volley full header", String.valueOf(headers)+"   ***  dsf");
                        //headers.put("","");
                        return headers;
                    }
                    /*@Override
        protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                        try {
                            Log.d("Volley1", "inside parseNetwork");
                            String json = new String(response.data,HttpHeaderParser.parseCharset(response.headers));
                            Log.d("Volley1 json", json);
                            Log.d("Volley1 Set-Cookie", " ####  ");
                         //   response.headers.put("Authorization",mainheader);
                            Log.d("Volley1 auth headr",mainheader);
                            Log.d("Volley1 full header", String.valueOf(response.headers));
                            return Response.success(new JSONObject(json), HttpHeaderParser.parseCacheHeaders(response));

                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                            return Response.error(new ParseError(e));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            return  Response.error(new ParseError(e));
                        }}*/
                    };



        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }


    public void LogIn(String req){
        if(req.equals("true")){
            Intent intent = new Intent(Main_Login_Page.this,Activity_Listview_Orders.class);
            Bundle bundle =new Bundle();
            bundle.putString("customer_id", customerID);
            bundle.putString("Cookie",cookie);
            intent.putExtras(bundle);

            Log.d("Location", "new activity");
            Log.d("Volley log cookie act 1",cookie+"test");
            Preference_Helper pref = new Preference_Helper(Main_Login_Page.this);
          if (checkBox.isChecked()){
           if(!isLoggedIn){
            pref.save("isLoggedIn",true);
            pref.save("username", username.getText().toString());
            pref.save("password",password.getText().toString());
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

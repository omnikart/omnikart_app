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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class Main_Login_Page extends AppCompatActivity{
    EditText username;
    EditText password;
    Button btn_login;
    TextView show_data;
    CheckBox checkBox;
    JSONObject params;
    String url;
    String success="";
    String customerID="";
    ProgressBar progressBar;
    Boolean isLoggedIn;
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
        url ="http://testing.omnikart.com/index.php?route=app/login";
      //  url = "https://www.omnikart.com/index.php?route=app/login";
        params = new JSONObject();
        final Preference_Helper preference_helper = new Preference_Helper(Main_Login_Page.this);
        isLoggedIn = preference_helper.load("isLoggedIn",false);
        Log.d("islogged in","is logged is "+isLoggedIn);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isLoggedIn = false ;
                preference_helper.save("isLoggedIn",false);
                Log.d("isloggedin","setting is logged in "+isLoggedIn);
                try {
                    params.put("username", username.getText());
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
                params.put("username", preference_helper.load("username",""));
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

    public void ConnectionLogIn(){

        final RequestQueue requestQueue = Volley.newRequestQueue(this);
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
                        });
                requestQueue.add(jsonObjectRequest);


            }


    public void LogIn(String req){
        if(req.equals("1")){
            Intent intent = new Intent(Main_Login_Page.this,Activity_Listview_Orders.class);
            Bundle bundle =new Bundle();
            bundle.putString("customer_id", customerID);
            intent.putExtras(bundle);

            Log.d("Location", "new activity");
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

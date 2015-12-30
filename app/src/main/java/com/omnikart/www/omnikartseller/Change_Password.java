package com.omnikart.www.omnikartseller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.omnikart.www.omnikartseller.Helper.Preference_Helper;
import com.omnikart.www.omnikartseller.Network.Volley.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Yash Gupta on 17-12-2015.
 */
public class Change_Password extends AppCompatActivity {
    EditText currentPass;
    EditText newPass;
    EditText confirmPass;
    Button updatePass;
    String url;
    JSONObject params;
    Preference_Helper prefs;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password);
        currentPass = (EditText)findViewById(R.id.editText_current_password);
        newPass = (EditText)findViewById(R.id.editText_new_password);
        confirmPass = (EditText)findViewById(R.id.editText_confirm_new_password);
        updatePass = (Button) findViewById(R.id.button_update_password);
        prefs = new Preference_Helper(Change_Password.this);
        url = "http://testing.omnikart.com/index.php?route=rest/account/password";
        params = new JSONObject();
        try {
            params.put("old_password",currentPass.getText());
            params.put("password",newPass.getText());
            params.put("confirm",confirmPass.getText());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        updatePass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation()) {
                    updatePassword();

                }
            }
        });
}
    public Boolean validation(){
        if (newPass.getText().toString().length()<4||newPass.getText().length()>20)
        { newPass.setError("New Password should be in between 4 to 20 letters");
            return false;}
        else if (!confirmPass.getText().toString().equals(newPass.getText().toString()))
        {   confirmPass.setError("Does not match new Password");
            Log.d("new pass", String.valueOf(newPass.getText()));
            Log.d("confirm new pass", String.valueOf(confirmPass.getText()));
            return false;}
        else {return true;}
    }

    public void updatePassword(){
        VolleySingleton volleySingleton = new VolleySingleton(Change_Password.this);
        volleySingleton.putWithHeadersWithParams(url, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String success="";
                JSONObject error;
                try {
                    if (response.has("success")){
                    success= response.getString("success");
                    Log.d("pass",success);}
                    if (response.has("error")){
                        error = response.getJSONObject("error");
                        Log.d("pass", String.valueOf(error));
                        Log.d("new pass", String.valueOf(newPass.getText()));
                        Log.d("confirm new pass", String.valueOf(confirmPass.getText()));
                        Log.d("old pass", String.valueOf(currentPass.getText()));

                        if (error.has("old_password")){
                        }     }
                    if(success.equals("true")){
                        //Toast.makeText(Change_Password.this, "Password Changed", Toast.LENGTH_SHORT).show();
                        Log.d("pass", "change pass");
                        newPass.setText("");
                        confirmPass.setText("");
                        currentPass.setText("");
                        confirmPass.setError(null);
                        prefs.save("isLoggedIn", false);
                        Toast.makeText(Change_Password.this, "Password Changed please login again", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Change_Password.this,Main_Activity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                       // Change_Password.this.finish();
                    }
                    else{
                        Toast.makeText(Change_Password.this, "Please Enter Correct Old Password", Toast.LENGTH_SHORT).show();
                        confirmPass.setError(null);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Change_Password.this, "Error in saving", Toast.LENGTH_SHORT).show();
                newPass.setText("");
                confirmPass.setText("");
                currentPass.setText("");
                confirmPass.setError(null);
            }
        });
    }
}

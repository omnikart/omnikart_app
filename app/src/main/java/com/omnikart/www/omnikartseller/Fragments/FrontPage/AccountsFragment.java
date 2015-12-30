package com.omnikart.www.omnikartseller.Fragments.FrontPage;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.omnikart.www.omnikartseller.Change_Password;
import com.omnikart.www.omnikartseller.Activity_Address;
import com.omnikart.www.omnikartseller.Helper.Preference_Helper;
import com.omnikart.www.omnikartseller.Main_Activity;
import com.omnikart.www.omnikartseller.Network.Volley.VolleySingleton;
import com.omnikart.www.omnikartseller.R;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Yash Gupta on 16-12-2015.
 */
public class AccountsFragment extends Fragment {
    EditText firstname;
    EditText lastname;
    EditText email;
    EditText mobile;
    EditText fax;
    Button update;
    TextView address;
    TextView password;
    Button logout;
    Preference_Helper prefs;
    String url;
    JSONObject params;
    public AccountsFragment(){}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_accounts, container, false);
        firstname =(EditText) rootView.findViewById(R.id.editText_firstname);
        lastname =(EditText) rootView.findViewById(R.id.editText_lastname);
        email =(EditText) rootView.findViewById(R.id.editText_email);
        mobile =(EditText) rootView.findViewById(R.id.editText_mobileno);
        fax =(EditText) rootView.findViewById(R.id.editText_fax);
        update =(Button) rootView.findViewById(R.id.button_update);
        address =(TextView) rootView.findViewById(R.id.textView_editAddress);
        password =(TextView) rootView.findViewById(R.id.textView_changePassword);
        logout = (Button) rootView.findViewById(R.id.button_logout);
        prefs = new Preference_Helper(getActivity().getApplicationContext());
        url ="http://testing.omnikart.com/index.php?route=rest/account/account";

        fetchSavedData();
        updateProfile();
        changePassword();
        editAddress();
        logout();
        return rootView;
    }
    public void fetchSavedData(){
        firstname.setText(prefs.load("firstname",""));
        lastname.setText(prefs.load("lastname",""));
        email.setText(prefs.load("email",""));
        mobile.setText(prefs.load("telephone", ""));
        fax.setText(prefs.load("fax", ""));
    }
    public void updateProfile(){
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation()) {
                    params = new JSONObject();
                    try {
                        params.put("firstname", firstname.getText());
                        params.put("lastname", lastname.getText());
                        params.put("email", email.getText());
                        params.put("telephone", mobile.getText());
                        params.put("fax", fax.getText());

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //Toast.makeText(getActivity().getApplicationContext(), "noerror", Toast.LENGTH_SHORT).show();
                    VolleySingleton volleySingleton = new VolleySingleton(getActivity().getApplicationContext());
                    volleySingleton.postWithHeadersWithParams(url, params, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            String success = "";
                            try {
                                if (response.has("success")) {
                                    success = response.getString("success");
                                    Log.d("success", success);
                                }
                                if (response.has("error")) {
                                    Log.d("success error", response.getString("error"));
                                }
                                if (success.equals("true")) {
                                    Toast.makeText(getActivity().getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
                                    prefs.save("firstname", firstname.getText().toString());
                                    prefs.save("lastname", lastname.getText().toString());
                                    prefs.save("email", email.getText().toString());
                                    prefs.save("telephone", mobile.getText().toString());
                                    prefs.save("fax", fax.getText().toString());
                                    fetchSavedData();
                                } else {
                                    Toast.makeText(getActivity().getApplicationContext(), "Error in saving", Toast.LENGTH_SHORT).show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getActivity().getApplicationContext(), "Error in saving1", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
    public Boolean validation(){

        String emailPattern ="^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        String mobilePattern = "[0-9]{10}";
        if( firstname.getText().toString().length() == 0 ){
            firstname.setError( "First name is required!" );
            return false ;}
        else if( lastname.getText().toString().length() == 0 ){
            lastname.setError( "Last name is required!" );
            return false ;}
        else if( !email.getText().toString().matches(emailPattern) ){
            email.setError( "Please enter correct Email" );
            return false ;}
        else if( !mobile.getText().toString().matches(mobilePattern) ){
            mobile.setError( "Please enter correct Mobile no" );
            return false ;}
        else {return true;}

    }
    public void logout(){
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefs.save("isLoggedIn",false);
                Intent intent = new Intent(getActivity().getApplicationContext(),Main_Activity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
    }
    public void changePassword(){
        password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(),Change_Password.class);
                startActivity(intent);
            }
        });
    }
    public void editAddress(){
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(),Activity_Address.class);
                startActivity(intent);
            }
        });
    }
}

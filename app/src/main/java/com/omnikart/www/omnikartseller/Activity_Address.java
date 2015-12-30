package com.omnikart.www.omnikartseller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.omnikart.www.omnikartseller.Adapters.ListView_Address_Adapter;
import com.omnikart.www.omnikartseller.Network.Volley.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Yash Gupta on 28-12-2015.
 */
public class Activity_Address extends AppCompatActivity{

    ListView list;
    String url;
    ListView_Address_Adapter listView_address_adapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_address);
        list = (ListView) findViewById(R.id.listView_editAddress);
        url = "http://testing.omnikart.com/index.php?route=rest/account/address";
        TextView addAddress = (TextView) findViewById(R.id.add_address);
        addAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_Address.this, Activity_Add_Address.class);
                startActivity(intent);
            }
        });
        //Toast.makeText(getApplicationContext(),"onCreate",Toast.LENGTH_SHORT).show();
        AddressList();
    }
    public void onResume(){
        super.onResume();
       // Toast.makeText(getApplicationContext(), "onresume", Toast.LENGTH_SHORT).show();
        AddressList();
    }


    public void AddressList(){
        VolleySingleton volleySingleton = new VolleySingleton(Activity_Address.this);
        volleySingleton.getWithHeaders(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String success="";
                String[] address_array = {"address_id","firstname","lastname","company","address_1","address_2","city","postcode","zone","country"};
                JSONObject data ;
                JSONArray addresses;
                String[] array_address_id = new String[0];
                String[] array_firstname = new String[0];
                String[] array_lastname = new String[0];
                String[] array_company = new String[0];
                String[] array_address_1 = new String[0];
                String[] array_address_2 = new String[0];
                String[] array_city = new String[0];
                String[] array_postcode = new String[0];
                String[] array_state = new String[0];
                String[] array_country = new String[0];
                String[] addresses_array = new String[10];

                try {
                    if (response.has("success")) {
                        success = response.getString("success"); }
                    if (response.has("data")){
                        data = response.getJSONObject("data");
                        if (data.has("addresses")){
                            addresses = data.getJSONArray("addresses");
                            array_address_id = new String[addresses.length()];
                            array_firstname = new String[addresses.length()];
                            array_lastname = new String[addresses.length()];
                            array_company = new String[addresses.length()];
                            array_address_1 = new String[addresses.length()];
                            array_address_2 = new String[addresses.length()];
                            array_city = new String[addresses.length()];
                            array_postcode = new String[addresses.length()];
                            array_state = new String[addresses.length()];
                            array_country = new String[addresses.length()];
                            for (int i=0;i < addresses.length();i++){
                                JSONObject address_details = addresses.getJSONObject(i);
                                array_address_id[i]=address_details.getString(address_array[0]);
                                array_firstname[i]=address_details.getString(address_array[1]);
                                array_lastname[i]=address_details.getString(address_array[2]);
                                array_company[i]=address_details.getString(address_array[3]);
                                array_address_1[i]=address_details.getString(address_array[4]);
                                array_address_2[i]=address_details.getString(address_array[5]);
                                array_city[i]=address_details.getString(address_array[6]);
                                array_postcode[i]=address_details.getString(address_array[7]);
                                array_state[i]=address_details.getString(address_array[8]);
                                array_country[i]=address_details.getString(address_array[9]);

                            }
                        }
                    }
                    Log.d("array", String.valueOf(response));

                    listView_address_adapter = new ListView_Address_Adapter(Activity_Address.this,array_address_id,
                            array_firstname, array_lastname,array_company,array_address_1,array_address_2,array_city,
                            array_postcode,array_state,array_country);
                    list.setAdapter(listView_address_adapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
    }
}


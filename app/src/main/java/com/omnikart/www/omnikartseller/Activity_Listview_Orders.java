package com.omnikart.www.omnikartseller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.omnikart.www.omnikartseller.Adapters.ListView_Orders_Adapter;
import com.omnikart.www.omnikartseller.Helper.Authorization;
import com.omnikart.www.omnikartseller.Network.Volley.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;


public class Activity_Listview_Orders extends AppCompatActivity{

    ListView list;
    String url;
    ProgressBar progressBar;
    String authorization;
    String set_cookie;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_orders);
        list =(ListView) findViewById(R.id.listView_orders);
        url ="http://testing.omnikart.com/index.php?route=rest/order/orders";
        progressBar = (ProgressBar) findViewById(R.id.progressBar_listview_orders);
        Authorization auth = new Authorization(Activity_Listview_Orders.this);
        authorization= auth.getAuthorization();
        Log.d("Volley 2 New Auth",authorization);
        Connection_Fetch_Orders();
    }
   @Override
    public void onResume(){
       super.onResume();
        Connection_Fetch_Orders();
    }
    public void Connection_Fetch_Orders() {

        progressBar.setVisibility(View.VISIBLE);
        VolleySingleton volleySingleton = new VolleySingleton(Activity_Listview_Orders.this);
        volleySingleton.getWithHeaders(url, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                String order_id = "";
                String date_added = "";
                String currency_code = "";
                String currency_value = "";
                String name = "";
                String order_status = "";
                String error = "";
                String success = "";
                String[] array_order_id = new String[0];
                String[] array_date_added = new String[0];
                String[] array_currency_code = new String[0];
                String[] array_currency_value = new String[0];
                String[] array_name = new String[0];
                String[] array_order_status = new String[0];

                try {
                    if (response.has("success")) {
                        success = response.getString("success");
                        Log.d("Volley 2 success", success);
                    }
                    if (response.has("error")) {
                        error = response.getString("error");
                        Log.d("Volley 2 error", error);
                    }
                    if (response.has("data")) {
                        JSONObject data = response.getJSONObject("data");
                        JSONArray orders;
                        if (data.has("orders")) {
                            orders = data.getJSONArray("orders");
                            array_order_id = new String[orders.length()];
                            array_date_added = new String[orders.length()];
                            array_currency_code = new String[orders.length()];
                            array_currency_value = new String[orders.length()];
                            array_name = new String[orders.length()];
                            array_order_status = new String[orders.length()];
                            for (int i = 0; i < orders.length(); i++) {
                                JSONObject order_details = orders.getJSONObject(i);
                                if (order_details.has("order_id")) {
                                    order_id = order_details.getString("order_id");
                                }
                                if (order_details.has("date_added")) {
                                    date_added = order_details.getString("date_added");
                                }
                                if (order_details.has("currency_code")) {
                                    currency_code = order_details.getString("currency_code");
                                }
                                if (order_details.has("currency_value")) {
                                    currency_value = order_details.getString("currency_value");
                                }
                                if (order_details.has("name")) {
                                    name = order_details.getString("name");
                                }
                                if (order_details.has("orderstatus")) {
                                    order_status = order_details.getString("orderstatus");
                                }
                                // Creating Arrays
                                array_order_id[i] = order_id;
                                array_date_added[i] = date_added;
                                array_currency_code[i] = currency_code;
                                array_currency_value[i] = currency_value;
                                array_name[i] = name;
                                array_order_status[i] = order_status;
                            }

                        }
                    }
                    progressBar.setVisibility(View.GONE);
                    ListView_Orders_Adapter adapter = new ListView_Orders_Adapter(Activity_Listview_Orders.this, array_order_id, array_date_added, array_currency_code, array_currency_value, array_name, array_order_status);
                    list.setAdapter(adapter);
                    final String[] finalArray_order_id = array_order_id;
                    list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            Log.d("ITEM", finalArray_order_id[position]);
                            Log.d("ITEM", String.valueOf(id));
                            Intent intent = new Intent(Activity_Listview_Orders.this, Order_Details.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("order_id", finalArray_order_id[position]);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                    progressBar.setVisibility(View.GONE);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Volley2", "No connection");
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Connection Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity__fragments__firsttime, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar actions click
        switch (item.getItemId()) {
            case R.id.action_close:
                finish();
                //  return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

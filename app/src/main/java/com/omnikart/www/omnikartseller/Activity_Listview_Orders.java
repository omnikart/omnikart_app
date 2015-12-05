package com.omnikart.www.omnikartseller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.omnikart.www.omnikartseller.Adapters.ListView_Orders_Adapter;
import com.omnikart.www.omnikartseller.Network.Volley.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;


public class Activity_Listview_Orders extends AppCompatActivity{

    JSONObject params;
    ListView list;
    String url;
    String customerID;
    String cookie="";
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_orders);
        list =(ListView) findViewById(R.id.listView_orders);
        url ="http://testing.omnikart.com/index.php?route=app/customerpartner/order";
       // String url ="https://www.omnikart.com/index.php?route=app/customerpartner/order";
        progressBar = (ProgressBar) findViewById(R.id.progressBar_listview_orders);
        Bundle bundle = getIntent().getExtras();
        customerID = bundle.getString("customer_id");
        cookie = bundle.getString("Cookie");
        Log.d("Volley1_customerid",customerID);
        params = new JSONObject();
        try {
            params.put("customer_id",customerID);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("Volley1_params", params.toString());
        Connection_Fetch_Orders();
    }
    @Override
    public void onResume(){
        super.onResume();
        Connection_Fetch_Orders();
    }
    public void Connection_Fetch_Orders(){

      //  final RequestQueue requestQueue = Volley.newRequestQueue(Activity_Listview_Orders.this);
        progressBar.setVisibility(View.VISIBLE);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url,params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String id= "";
                        String order_id ="";
                        String date_added = "";
                        String currency_code = "";
                        String currency_value = "";
                        String name = "";
                        String order_status ="";
                        String error = "";
                        String[] array_order_id = new String[0];
                        String[] array_date_added = new String[0];
                        String[] array_currency_code = new String[0];
                        String[] array_currency_value = new String[0];
                        String[] array_name = new String[0];
                        String[] array_order_status = new String[0];

                        try {
                            if (response.has("id")) {
                                id = response.getString("id");
                            }
                            if (response.has("error")) {
                                error = response.getString("error");
                            }

                            if(response.has("cookie")){
                                String cookie1 = response.getString("cookie");
                                Log.d("Volley1",cookie1);
                                Log.d("Volley1",cookie);
                            }

                            JSONArray orders ;
                            if (response.has("orders")) {
                                orders = response.getJSONArray("orders");
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

                                  //  Log.d("Volley1", error + "" + id + "" + order_id + "" + date_added + "" + currency_code + "" + currency_value + "" + name + "" + order_status + "\n");
                                    // tv_orderDetails.append("" + id + "" + order_id + "" + date_added + "" + currency_code + "" + currency_value + "" + name + "" + order_status + "\n");
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
                                    Intent intent = new Intent(Activity_Listview_Orders.this,Order_Details.class);
                                    Bundle bundle =new Bundle();
                                    bundle.putString("order_id", finalArray_order_id[position]);
                                    bundle.putString("customer_id", customerID);
                                    intent.putExtras(bundle);
                                    startActivity(intent);

                                }
                            });

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                },       new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Volley1","No connection");
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Connection Error", Toast.LENGTH_SHORT).show();
//                            Log.d("Volley1",error.getMessage());
            }
        }){
            @Override
            protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                try {
                    Log.d("Volley1 cookie",cookie);
                    Log.d("Volley1 full header", String.valueOf(response.headers));
                    response.headers.put("Set-Cookie",cookie);
                    Log.d("Volley1", "inside parseNetwork");
                    String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                    Log.d("Volley1 response header",response.headers +"");
                    Log.d("Volley1 json", json );
                    return Response.success(new JSONObject(json), HttpHeaderParser.parseCacheHeaders(response));

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                    return Response.error(new ParseError(e));
                } catch (JSONException e) {
                    e.printStackTrace();
                    return  Response.error(new ParseError(e));
                }
            }
        };
        //  requestQueue.add(jsonObjectRequest);
        VolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }


}

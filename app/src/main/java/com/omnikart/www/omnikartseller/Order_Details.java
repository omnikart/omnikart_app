package com.omnikart.www.omnikartseller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.omnikart.www.omnikartseller.Network.Volley.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Order_Details extends AppCompatActivity {
    TextView order_details;
    String order_id;
    String url;
    String finalUrl;
    ProgressBar progressBar;
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.order_details);
            Bundle bundle =getIntent().getExtras();
            order_id =  bundle.getString("order_id");
            order_details =(TextView) findViewById(R.id.textView_orderdetails);
            url ="http://testing.omnikart.com/index.php?route=rest/order/orders&id=";
            finalUrl = url + order_id;
            progressBar = (ProgressBar) findViewById(R.id.progressBar_orders_details);
            Connection_Fetch_Order_Details();
        }

        public void Connection_Fetch_Order_Details(){

            progressBar.setVisibility(View.VISIBLE);
            VolleySingleton volleySingleton = new VolleySingleton(Order_Details.this);
            volleySingleton.getWithHeaders(finalUrl,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Log.d("Volley2", "Connection Done");
                            String success = "";
                            String invoice_no = "";
                            String invoice_prefix = "";
                            String store_id  = "";
                            String store_name = "";
                            String store_url = "";
                            String firstname = "";
                            String lastname = "";
                            String telephone = "";
                            String fax = "";
                            String email = "";
                            String payment_firstname = "";
                            String payment_lastname = "";
                            String payment_company = "";
                            String payment_address_1 = "";
                            String payment_address_2 = "";
                            String payment_postcode = "";
                            String payment_city = "";
                            String payment_zone_id = "";
                            String payment_zone = "";
                            String payment_zone_code = "";
                            String payment_country_id = "";
                            String payment_country = "";
                            String payment_iso_code_2 = "";
                            String payment_iso_code_3 = "";
                            String payment_address_format = "";
                            String payment_method = "";
                            String shipping_firstname = "";
                            String shipping_lastname = "";
                            String shipping_company = "";
                            String shipping_address_1 = "";
                            String shipping_address_2 = "";
                            String shipping_postcode = "";
                            String shipping_city = "";
                            String shipping_zone_id = "";
                            String shipping_zone = "";
                            String shipping_zone_code = "";
                            String shipping_country_id = "";
                            String shipping_country = "";
                            String shipping_iso_code_2 = "";
                            String shipping_iso_code_3 = "";
                            String shipping_address_format = "";
                            String shipping_method = "";
                            String comment = "";
                            String total = "";
                            String order_status_id = "";
                            String language_id = "";
                            String currency_id = "";
                            String currency_code = "";
                            String currency_value = "";
                            String date_modified = "";
                            String date_added = "";
                            String ip = "";
                            String product_id = "";
                            String product_name = "";
                            String product_model = "";
                            String product_tracking = "";
                            String product_quantity = "";
                            String product_paid_status = "";
                            String product_price = "";
                            String product_total = "";

                            try {
                                if (response.has("success")) {
                                    success = response.getString("success");   }

                                JSONObject orderinfo ;
                                if (response.has("data")){
                                    orderinfo = response.getJSONObject("data");
                                    if(orderinfo.has("invoice_no")){invoice_no = orderinfo.getString("invoice_no"); }
                                    if(orderinfo.has("invoice_prefix")){invoice_prefix = orderinfo.getString("invoice_prefix"); }
                                    if(orderinfo.has("store_id")){store_id = orderinfo.getString("store_id"); }
                                    if(orderinfo.has("store_name")){store_name = orderinfo.getString("store_name"); }
                                    if(orderinfo.has("store_url")){store_url = orderinfo.getString("store_url"); }
                                    if(orderinfo.has("firstname")){firstname = orderinfo.getString("firstname"); }
                                    if(orderinfo.has("lastname")){lastname = orderinfo.getString("lastname"); }
                                    if(orderinfo.has("telephone")){telephone = orderinfo.getString("telephone"); }
                                    if(orderinfo.has("fax")){fax = orderinfo.getString("fax"); }
                                    if(orderinfo.has("email")){email = orderinfo.getString("email"); }
                                    if(orderinfo.has("payment_firstname")){payment_firstname = orderinfo.getString("payment_firstname"); }
                                    if(orderinfo.has("payment_lastname")){payment_lastname = orderinfo.getString("payment_lastname"); }
                                    if(orderinfo.has("payment_company")){payment_company = orderinfo.getString("payment_company"); }
                                    if(orderinfo.has("payment_address_1")){payment_address_1 = orderinfo.getString("payment_address_1"); }
                                    if(orderinfo.has("payment_address_2")){payment_address_2 = orderinfo.getString("payment_address_2"); }
                                    if(orderinfo.has("payment_postcode")){payment_postcode = orderinfo.getString("payment_postcode"); }
                                    if(orderinfo.has("payment_city")){payment_city = orderinfo.getString("payment_city"); }
                                    if(orderinfo.has("payment_zone_id")){payment_zone_id = orderinfo.getString("payment_zone_id"); }
                                    if(orderinfo.has("payment_zone")){payment_zone = orderinfo.getString("payment_zone"); }
                                    if(orderinfo.has("payment_zone_code")){payment_zone_code = orderinfo.getString("payment_zone_code"); }
                                    if(orderinfo.has("payment_country_id")){payment_country_id = orderinfo.getString("payment_country_id"); }
                                    if(orderinfo.has("payment_country")){payment_country = orderinfo.getString("payment_country"); }
                                    if(orderinfo.has("payment_iso_code_2")){payment_iso_code_2 = orderinfo.getString("payment_iso_code_2"); }
                                    if(orderinfo.has("payment_iso_code_3")){payment_iso_code_3 = orderinfo.getString("payment_iso_code_3"); }
                                    if(orderinfo.has("payment_address_format")){payment_address_format = orderinfo.getString("payment_address_format"); }
                                    if(orderinfo.has("payment_method")){payment_method = orderinfo.getString("payment_method"); }
                                    if(orderinfo.has("shipping_firstname")){shipping_firstname = orderinfo.getString("shipping_firstname"); }
                                    if(orderinfo.has("shipping_lastname")){shipping_lastname = orderinfo.getString("shipping_lastname"); }
                                    if(orderinfo.has("shipping_company")){shipping_company = orderinfo.getString("shipping_company"); }
                                    if(orderinfo.has("shipping_address_1")){shipping_address_1 = orderinfo.getString("shipping_address_1"); }
                                    if(orderinfo.has("shipping_address_2")){shipping_address_2 = orderinfo.getString("shipping_address_2"); }
                                    if(orderinfo.has("shipping_postcode")){shipping_postcode = orderinfo.getString("shipping_postcode"); }
                                    if(orderinfo.has("shipping_city")){shipping_city = orderinfo.getString("shipping_city"); }
                                    if(orderinfo.has("shipping_zone_id")){shipping_zone_id = orderinfo.getString("shipping_zone_id"); }
                                    if(orderinfo.has("shipping_zone")){shipping_zone = orderinfo.getString("shipping_zone"); }
                                    if(orderinfo.has("shipping_zone_code")){shipping_zone_code = orderinfo.getString("shipping_zone_code"); }
                                    if(orderinfo.has("shipping_country_id")){shipping_country_id = orderinfo.getString("shipping_country_id"); }
                                    if(orderinfo.has("shipping_country")){shipping_country = orderinfo.getString("shipping_country"); }
                                    if(orderinfo.has("shipping_iso_code_2")){shipping_iso_code_2 = orderinfo.getString("shipping_iso_code_2"); }
                                    if(orderinfo.has("shipping_iso_code_3")){shipping_iso_code_3 = orderinfo.getString("shipping_iso_code_3"); }
                                    if(orderinfo.has("shipping_address_format")){shipping_address_format = orderinfo.getString("shipping_address_format"); }
                                    if(orderinfo.has("shipping_method")){shipping_method = orderinfo.getString("shipping_method"); }
                                    if(orderinfo.has("comment")){comment = orderinfo.getString("comment"); }
                                    if(orderinfo.has("total")){total = orderinfo.getString("total"); }
                                    if(orderinfo.has("order_status_id")){order_status_id = orderinfo.getString("order_status_id"); }
                                    if(orderinfo.has("language_id")){language_id = orderinfo.getString("language_id"); }
                                    if(orderinfo.has("currency_id")){currency_id = orderinfo.getString("currency_id"); }
                                    if(orderinfo.has("currency_code")){currency_code = orderinfo.getString("currency_code"); }
                                    if(orderinfo.has("currency_value")){currency_value = orderinfo.getString("currency_value"); }
                                    if(orderinfo.has("date_modified")){date_modified = orderinfo.getString("date_modified"); }
                                    if(orderinfo.has("date_added")){date_added = orderinfo.getString("date_added"); }
                                    if(orderinfo.has("ip")){ip = orderinfo.getString("ip"); }
                                }

                                order_details.append("\n"+success+"\n"+order_id+"\n"+invoice_no+"\n"+invoice_prefix+"\n"+store_id+"\n"
                                        +store_name+"\n"+store_url+"\n"+firstname+"\n"+lastname+"\n"+telephone+"\n"
                                        +fax+"\n"+email+"\n"+payment_firstname+"\n"+payment_lastname+"\n"+payment_company+"\n"
                                        +payment_address_1+"\n"+payment_address_2+"\n"+payment_postcode+"\n"+payment_city+"\n"
                                        +payment_zone_id+"\n"+payment_zone+"\n"+payment_zone_code+"\n"+payment_country_id+"\n"
                                        +payment_country+"\n"+payment_iso_code_2+"\n"+payment_iso_code_3+"\n"+payment_address_format+"\n"
                                        +payment_method+"\n"+shipping_firstname+"\n"+shipping_lastname+"\n"+shipping_company+"\n"+shipping_address_1+"\n"
                                        +shipping_address_2+"\n"+shipping_postcode+"\n"+shipping_city+"\n"+shipping_zone_id+"\n"+shipping_zone+"\n"
                                        +shipping_zone_code+"\n"+shipping_country_id+"\n"+shipping_country+"\n"+shipping_iso_code_2+"\n"+shipping_iso_code_3+"\n"
                                        +shipping_address_format+"\n"+shipping_method+"\n"+comment+"\n"+total+"\n"+order_status_id+"\n"+language_id+"\n"
                                        +currency_id+"\n"+currency_code+"\n"+currency_value+"\n"+date_modified+"\n"+date_added+"\n"+ip+"\n");

                                JSONArray products ;
                                if(response.has("products")){
                                    products = response.getJSONArray("products");
                                    for (int i = 0 ; i<products.length() ; i++){
                                        JSONObject product_details = products.getJSONObject(i);
                                        if(product_details.has("product_id")){product_id = product_details.getString("product_id");}
                                        if(product_details.has("name")){product_name = product_details.getString("name");}
                                        if(product_details.has("model")){product_model = product_details.getString("model");}
                                        if(product_details.has("tracking")){product_tracking = product_details.getString("tracking");}
                                        if(product_details.has("quantity")){product_quantity = product_details.getString("quantity");}
                                        if(product_details.has("paid_status")){product_paid_status = product_details.getString("paid_status");}
                                        if(product_details.has("price")){product_price = product_details.getString("price");}
                                        if(product_details.has("total")){product_total = product_details.getString("total");}

                                        order_details.append("\n PRODUCTS"+product_id+"\n"+product_name+"\n"+product_model+"\n"+product_tracking+"\n"
                                                +product_quantity+"\n"+product_paid_status+"\n"+product_price+"\n"+product_total+"\n");
                                    }
                                }
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
                            Log.d("Volley2","Connection Error");
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), "Connection Error", Toast.LENGTH_SHORT).show();
                        }
                    }
            );
                  }

}


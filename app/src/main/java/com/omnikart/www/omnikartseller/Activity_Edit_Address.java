package com.omnikart.www.omnikartseller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;

/**
 * Created by Yash Gupta on 30-12-2015.
 */
public class Activity_Edit_Address extends AppCompatActivity{
    String id;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_address);
        id = getIntent().getExtras().getString("id");
        String Firstname = getIntent().getExtras().getString("firstname");
        String Lastname = getIntent().getExtras().getString("lastname");
        String Company = getIntent().getExtras().getString("company");
        String Address1 = getIntent().getExtras().getString("address1");
        String Address2 = getIntent().getExtras().getString("address2");
        String City = getIntent().getExtras().getString("city");
        String Postcode = getIntent().getExtras().getString("postcode");
        String State = getIntent().getExtras().getString("state");
        String Country = getIntent().getExtras().getString("country");

        Log.d("id",id);
        EditText firstname = (EditText) findViewById(R.id.editText_firstname);
        EditText lastname = (EditText) findViewById(R.id.editText_lastname);
        EditText company = (EditText) findViewById(R.id.editText_company);
        EditText address1 = (EditText) findViewById(R.id.editText_address1);
        EditText address2 = (EditText) findViewById(R.id.editText_address2);
        EditText city = (EditText) findViewById(R.id.editText_city);
        EditText postcode = (EditText) findViewById(R.id.editText_postcode);
        EditText state = (EditText) findViewById(R.id.editText_state);
        EditText country = (EditText) findViewById(R.id.editText_country);

        firstname.setText(Firstname);
        lastname.setText(Lastname);
        company.setText(Company);
        address1.setText(Address1);
        address2.setText(Address2);
        city.setText(City);
        postcode.setText(Postcode);
        state.setText(State);
        country.setText(Country);
    }
}

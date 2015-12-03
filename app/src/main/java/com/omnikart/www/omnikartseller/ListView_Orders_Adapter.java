package com.omnikart.www.omnikartseller;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.Response;

import org.json.JSONObject;

import java.util.zip.Inflater;

/**
 * Created by Yash Gupta on 02-12-2015.
 */
public class ListView_Orders_Adapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] order_id ;
    private final String[] date_added ;
    private final String[] currency_code ;
    private final String[] currency_value ;
    private final String[] name ;
    private final String[] order_status ;


    public ListView_Orders_Adapter(Activity context, String[] order_id, String[] date_added, String[] currency_code, String[] currency_value, String[] name, String[] order_status) {
        super(context, R.layout.listitem_orders,order_id);
        this.context = context;
        this.order_id = order_id;
        this.date_added = date_added;
        this.currency_code = currency_code;
        this.currency_value = currency_value;
        this.name = name;
        this.order_status = order_status;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater =context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.listitem_orders, null, true);

        TextView orderID = (TextView) rowView.findViewById(R.id.textView_orderID);
        orderID.setText(order_id[position]);

        TextView dateAdded = (TextView) rowView.findViewById(R.id.textView_dateAdded);
        dateAdded.setText(date_added[position]);

        TextView currencyCode = (TextView) rowView.findViewById(R.id.textView_currencyCode);
        currencyCode.setText(currency_code[position]);

        TextView currencyValue = (TextView) rowView.findViewById(R.id.textView_currencyValue);
        currencyValue.setText(currency_value[position]);

        TextView personName = (TextView) rowView.findViewById(R.id.textView_name);
        personName.setText(name[position]);

        TextView orderStatus = (TextView) rowView.findViewById(R.id.textView_orderStatus);
        orderStatus.setText(order_status[position]);

        return rowView;
    }
}

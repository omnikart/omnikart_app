package com.omnikart.www.omnikartseller.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.omnikart.www.omnikartseller.Activity_Add_Address;
import com.omnikart.www.omnikartseller.Activity_Address;
import com.omnikart.www.omnikartseller.Activity_Edit_Address;
import com.omnikart.www.omnikartseller.R;

/**
 * Created by Yash Gupta on 28-12-2015.
 */
public class ListView_Address_Adapter extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] id;
    private final String[] first_name;
    private final String[] last_name;
    private final String[] company;
    private final String[] address1;
    private final String[] address2 ;
    private final String[] city;
    private final String[] postcode;
    private final String[] state;
    private final String[] country;

    public ListView_Address_Adapter(Activity context,String[] id, String[] first_name , String[] last_name,String[] company,String[] address1,
                                    String[] address2, String[] city,String[] postcode,String[] state,String[] country) {
        super(context, R.layout.listitem_address,last_name);
        this.context = context;
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.company=company;
        this.address1=address1;
        this.address2=address2;
        this.city=city;
        this.postcode=postcode;
        this.state=state;
        this.country=country;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater =context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.listitem_address, null, true);

        TextView fullname = (TextView) rowView.findViewById(R.id.textView_fullname);
        fullname.setText(first_name[position]+" "+last_name[position]);

        TextView Company = (TextView) rowView.findViewById(R.id.textView_company);
        Company.setText(company[position]);
        if (company[position].equals("")){Company.setVisibility(View.GONE);}

        TextView Address1 = (TextView) rowView.findViewById(R.id.textView_address1);
        Address1.setText(address1[position]);

        TextView Address2 = (TextView) rowView.findViewById(R.id.textView_address2);
        Address2.setText(address2[position]);
        if (address2[position].equals("")){Address2.setVisibility(View.GONE);}

        TextView CityAndCode = (TextView) rowView.findViewById(R.id.textView_cityAndCode);
        CityAndCode.setText(city[position]+" - "+postcode[position]);

        TextView State = (TextView) rowView.findViewById(R.id.textView_state);
        State.setText(state[position]);

        final TextView Country = (TextView) rowView.findViewById(R.id.textView_country);
        Country.setText(country[position]);

        final Button moreOptions = (Button) rowView.findViewById(R.id.button_menu);
        moreOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupMenu popup = new PopupMenu(context, moreOptions);
                popup.getMenuInflater().inflate(R.menu.menu_address, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        int i = item.getItemId();
                        if (i == R.id.item_edit_address) {
                            //do something
                            Toast.makeText(context,"edit"+position,Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(context,Activity_Edit_Address.class);
                            Bundle bundle = new Bundle();
                            bundle.putString("id",id[position]);
                            bundle.putString("firstname",first_name[position]);
                            bundle.putString("lastname",last_name[position]);
                            bundle.putString("company",company[position]);
                            bundle.putString("address1",address1[position]);
                            bundle.putString("address2",address2[position]);
                            bundle.putString("city",city[position]);
                            bundle.putString("postcode",postcode[position]);
                            bundle.putString("state",state[position]);
                            bundle.putString("country",country[position]);

                            intent.putExtras(bundle);
                            context.startActivity(intent);
                            return true;
                        }
                        else if (i == R.id.item_delete_address){
                            //do something
                            Toast.makeText(context,"delete"+position,Toast.LENGTH_SHORT).show();
                            ((Activity_Address)context).AddressList();
                            notifyDataSetChanged();
                            return true;
                        }
                        else {
                            return onMenuItemClick(item);
                        }
                    }
                });

                popup.show();
            }
        });

        return rowView;
    }
    public void refreshEvents(){
        notifyDataSetChanged();
    }
}

package com.omnikart.www.omnikartseller;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by Yash Gupta on 28-11-2015.
 */
public class Firsttime_Fragment3 extends Fragment {
    Button login_button ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view= inflater.inflate(R.layout.firsttime_fragment3,container,false);

        login_button = (Button) view.findViewById(R.id.button_fragment_login);
        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity().getApplicationContext(), "Button Clicked", Toast.LENGTH_SHORT).show();
                Preference_Helper prefs = new Preference_Helper(getActivity());
                prefs.save("isClicked", true);
                Intent i = new Intent(getActivity(),Main_Login_Page.class);
                startActivity(i);
               // Firsttime_Fragment3.this.getExitTransition();
            }
        });


        return view;
    }

}

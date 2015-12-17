package com.omnikart.www.omnikartseller.Fragments.FrontPage;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.omnikart.www.omnikartseller.R;

/**
 * Created by Yash Gupta on 15-12-2015.
 */
public class AboutUs extends Fragment {
    public AboutUs(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_about_us, container, false);

        return rootView;
    }
}

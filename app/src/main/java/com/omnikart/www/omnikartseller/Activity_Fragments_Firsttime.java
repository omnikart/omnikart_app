package com.omnikart.www.omnikartseller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.omnikart.www.omnikartseller.Adapters.Fragments_Pager_Adapter;
import com.omnikart.www.omnikartseller.Helper.Preference_Helper;

public class Activity_Fragments_Firsttime extends AppCompatActivity {

    ViewPager mPagerView;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

            setContentView(R.layout.activity_fragments_firsttime);
            mPagerView = (ViewPager) findViewById(R.id.pager_fragments_firsttime);
            Fragments_Pager_Adapter adapter = new Fragments_Pager_Adapter(getSupportFragmentManager());
            mPagerView.setAdapter(adapter);

    }

}
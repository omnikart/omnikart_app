package com.omnikart.www.omnikartseller;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Yash Gupta on 28-11-2015.
 */
public class Fragments_Pager_Adapter extends FragmentPagerAdapter {
    public Fragments_Pager_Adapter(FragmentManager fm)
    {
        super(fm);
    }
    @Override
    public Fragment getItem(int i) {
        switch (i){
            case 0:
                return new Firsttime_Fragment1();
            case 1:
                return new Firsttime_Fragment2();
            case 2:
                return new Firsttime_Fragment3();
            default:
                break;}
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}

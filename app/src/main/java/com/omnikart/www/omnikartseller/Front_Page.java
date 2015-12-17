package com.omnikart.www.omnikartseller;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.omnikart.www.omnikartseller.Adapters.NavDrawerListAdapter;
import com.omnikart.www.omnikartseller.Fragments.FrontPage.AboutUs;
import com.omnikart.www.omnikartseller.Fragments.FrontPage.AccountsFragment;
import com.omnikart.www.omnikartseller.Fragments.FrontPage.HomeFragment;
import com.omnikart.www.omnikartseller.Helper.NavDrawerItem;
import java.util.ArrayList;

public class Front_Page extends AppCompatActivity{
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired time passed between two back presses.
    private long mBackPressed;
    private int mPosition=0;
    private Boolean inFragment = false;
    String[] navMenuTitles;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);

        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);
        // nav drawer icons from resources
        TypedArray navMenuIcons = getResources().obtainTypedArray(R.array.nav_drawer_icons);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.slider_menu);

        ArrayList<NavDrawerItem> navDrawerItems = new ArrayList<NavDrawerItem>();
        // adding nav drawer items to array
        // Home
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[0], navMenuIcons.getResourceId(0, -1)));
        //My Account
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(1, -1)));
        //Order History
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(2, -1)));
        //Transactions
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(3, -1), true, "22"));
        //My Products
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(4, -1)));
        //Ask to admin
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(5, -1)));
        //About Us
        navDrawerItems.add(new NavDrawerItem(navMenuTitles[6], navMenuIcons.getResourceId(6, -1)));
        // Recycle the typed array
        navMenuIcons.recycle();
        // setting the nav drawer list adapter
        NavDrawerListAdapter adapter = new NavDrawerListAdapter(getApplicationContext(), navDrawerItems);
        mDrawerList.setAdapter(adapter);

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                R.string.app_name, // nav drawer open - description for accessibility
                R.string.app_name // nav drawer close - description for accessibility
        ){
            public void onDrawerClosed(View view) {
                getSupportActionBar().setTitle("Omnikart Seller");
                // calling onPrepareOptionsMenu() to show action bar icons
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                getSupportActionBar().setTitle("Omnikart Seller");
                // calling onPrepareOptionsMenu() to hide action bar icons
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        // enabling action bar app icon and behaving it as toggle button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        // on first time display view for first nav item
        if (savedInstanceState == null) {
            displayView(0);        }
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mPosition = position;
                Log.d("mposition insideonclick", String.valueOf(mPosition)+inFragment);
                displayView(position);

            }
        });
    }
     public void displayView(int position) {
        // update the main content by replacing fragments
        Fragment fragment = null;

        switch (position) {
            case 0:
                mDrawerLayout.closeDrawers();
                   fragment = new HomeFragment();
                break;
            case 1:
                   fragment = new AccountsFragment();
                mDrawerLayout.closeDrawers();
                break;
            case 2:
                fragment = null;
                inFragment = false;
                mDrawerLayout.closeDrawers();
               // mPosition=0;
                Log.d("mposition inswitch", String.valueOf(mPosition)+inFragment);
                Intent intent = new Intent(Front_Page.this,Activity_Listview_Orders.class);
                startActivity(intent);

               //  fragment = new PhotosFragment();
                break;
            case 3:
                //  fragment = new CommunityFragment();
                mDrawerLayout.closeDrawers();
                break;
            case 4:
                //  fragment = new PagesFragment();
                mDrawerLayout.closeDrawers();
                break;
            case 5:
                fragment = new AboutUs();
                break;
            case 6:
                  fragment = new AboutUs();
                break;

            default:

                break;
    } if (fragment != null) {
             FragmentManager fragmentManager = getFragmentManager();
             fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
            inFragment = true;
             // update selected item and title, then close the drawer
             mDrawerList.setItemChecked(position, true);
             mDrawerList.setSelection(position);
             Log.d("mposition infragment", String.valueOf(position) + inFragment);
             setTitle(navMenuTitles[position]);
             mDrawerLayout.closeDrawer(mDrawerList);
         } else {
             // error in creating fragment
             Log.e("MainActivity", "Error in creating fragment");
         }
     }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity__fragments__firsttime, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle action bar actions click
        switch (item.getItemId()) {
            case R.id.action_close:
                finish();
              //  return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
     //Called when invalidateOptionsMenu() is triggered

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // if nav drawer is opened, hide the action items
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
        menu.findItem(R.id.action_close).setVisible(!drawerOpen);
        return super.onPrepareOptionsMenu(menu);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    @Override
    public void onBackPressed() {

       Log.d("mposition", String.valueOf(mPosition)+inFragment);
       // int i = mPosition ;
        if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
            mDrawerLayout.closeDrawers();
        }
        if ((mPosition!= 0)|| !inFragment) {
            mPosition = 0;
            inFragment = true;
            Log.d("mposition set","as 0 true");
            displayView(0);
        }
        if (mPosition==0) {
            if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
                Log.d("time", String.valueOf(mBackPressed));
                super.onBackPressed();
                return;
            } else {
                Toast.makeText(getBaseContext(), "Tap back button in order to exit", Toast.LENGTH_SHORT).show();
            }

            mBackPressed = System.currentTimeMillis();
            Log.d("time", String.valueOf(mBackPressed));
        }
     }
}

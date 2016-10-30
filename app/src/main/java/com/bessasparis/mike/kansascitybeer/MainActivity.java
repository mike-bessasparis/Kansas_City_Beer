/*
 * Copyright (c) 2016  Michael J. Bessasparis
 */

package com.bessasparis.mike.kansascitybeer;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * Created by mike on 9/25/2016.
 */

public class MainActivity extends FragmentActivity
        implements
        BreweryListFragment.OnBrewerySelectedListener {

    // Use this brewery list object globally
    static public BreweryList mBreweryList = new BreweryList();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolBar = (Toolbar) findViewById(R.id.my_toolbar);
//        setSupportActionBar(myToolBar);

        //if we're being re-created (rotation) do nothing.  otherwise create a fragment
        if (savedInstanceState != null) {
            return;
        }

        // Create a new Fragment to be placed in the activity layout
        BreweryListFragment firstFragment = new BreweryListFragment();

        // Add the fragment to the 'fragment_container' FrameLayout
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, firstFragment).commit();

    }

    public void onBrewerySelected(int position) {

        // Create fragment and give it an argument for the selected article
        BreweryDetailFragment newFragment = new BreweryDetailFragment();
        Bundle args = new Bundle();
        args.putInt(BreweryDetailFragment.ARG_POSITION, position);
        newFragment.setArguments(args);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        // Replace whatever is in the fragment_container view with this fragment,
        // and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragment_container, newFragment);
        transaction.addToBackStack(null);

        // Commit the transaction
        transaction.commit();
        ;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.brewery_list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                displayAboutBox();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void displayAboutBox() {

        Toast toast = Toast.makeText(MainActivity.this,
                "v0.2.0, by mikeb", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

}
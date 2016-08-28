/*
 * Copyright (c) 2016  Michael J. Bessasparis
 */

package com.bessasparis.mike.kansascitybeer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * An activity representing a single Brewery detail screen.
 */
public class BreweryDetailActivity extends AppCompatActivity implements RatingBar.OnRatingBarChangeListener {

    public static final String ARG_OBJ = "json-obj-string";
    public JSONObject bObj;
    public RatingBar ratingBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brewery_detail);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setOnRatingBarChangeListener(this);

        Intent intent = getIntent();

        //create a brewery object out of the arg string
        //and set the activity text
        try {
            bObj = new JSONObject(intent.getStringExtra(BreweryListActivity.ARG_OBJ));
            ((TextView) findViewById(R.id.brewery_detail_address)).
                    setText(getBreweryAddress(bObj));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // receives a string representing the JSON object of the brewery
    // returns the address attribute as a string
    public String getBreweryAddress(JSONObject mObj) throws JSONException {
        String breweryAddress;
        breweryAddress = mObj.getString("address");
        return breweryAddress;
    }

    // receives a string representing the JSON object of the brewery
    // returns the address attribute as a string
    public String getBreweryWebSite(JSONObject mObj) throws JSONException {
        String breweryWebSite;
        breweryWebSite = mObj.getString("website");
        return breweryWebSite;
    }

    //on a click send the map activity the brewery JSON object as a string
    public void mapButtonClicked(View v) {

        try {
            Intent searchAddress = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("geo:0,0?q=" + getBreweryAddress(bObj)));
            startActivity(searchAddress);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //go to www site button
    public void websiteButtonClicked(View v) {

        try {
            Intent openWebsite = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://" + getBreweryWebSite(bObj)));
            startActivity(openWebsite);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void onRatingChanged(RatingBar rBar, float rating, boolean fromUser) {

        Toast.makeText(BreweryDetailActivity.this, "thanks for rating", Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, BreweryListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}

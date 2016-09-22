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

/**
 * An activity representing a single Brewery detail screen.
 */
public class BreweryDetailActivity extends AppCompatActivity implements RatingBar.OnRatingBarChangeListener {

    public Brewery bObj;
    public RatingBar ratingBar;

    private int i;
    private BreweryList mBreweryList = new BreweryList();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brewery_detail);

        Intent intent = getIntent();
        i = intent.getIntExtra("brewery-index", 0);
        bObj = getBreweryObject(i);

        ((TextView) findViewById(R.id.brewery_detail_name)).
                setText(bObj.name);
        ((TextView) findViewById(R.id.brewery_detail_address)).
                setText(bObj.address);

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        ratingBar.setRating(bObj.rating);
        ratingBar.setOnRatingBarChangeListener(this);
    }


    private Brewery getBreweryObject(int i) {
        return (mBreweryList.getBrewery(i));
    }


    //on a click send the map activity the brewery name
    public void mapButtonClicked(View v) {
        Intent searchAddress = new Intent(Intent.ACTION_VIEW,
                Uri.parse("geo:0,0?q=" + bObj.name + " " + bObj.address));
        startActivity(searchAddress);
    }

    //go to www site button send browser the URL
    public void websiteButtonClicked(View v) {
        Intent openWebsite = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://" + bObj.website));
        startActivity(openWebsite);
    }


    public void onRatingChanged(RatingBar rBar, float rating, boolean fromUser) {
        Toast.makeText(BreweryDetailActivity.this, "thanks for rating", Toast.LENGTH_SHORT).show();
        bObj.rating = rating;
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

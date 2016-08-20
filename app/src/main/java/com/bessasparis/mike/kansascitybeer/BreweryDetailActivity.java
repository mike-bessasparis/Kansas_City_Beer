package com.bessasparis.mike.kansascitybeer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * An activity representing a single Brewery detail screen.
 */
public class BreweryDetailActivity extends AppCompatActivity {

    public static final String ARG_OBJ = "json-obj-string";
    public JSONObject bObj;

//    @Override
//    public void onMapReady(GoogleMap mMap) {
//        mMap.addMarker(new MarkerOptions().position(new LatLng(39.351410, -94.915102))
//                .title("CAC HQ and G6"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
//                new LatLng(39.351410, -94.915102), 16));
//        mMap.setMyLocationEnabled(true);
//
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brewery_detail);

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

//        MapFragment mapFragment = (MapFragment) getFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);

    }

    // receives a string representing the JSON object of the brewery
    // returns the address attribute as a string
    public String getBreweryAddress(JSONObject mObj) throws JSONException {
        String breweryAddress;
        breweryAddress = mObj.getString("address");
        return breweryAddress;
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

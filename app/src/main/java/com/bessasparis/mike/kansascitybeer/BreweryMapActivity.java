package com.bessasparis.mike.kansascitybeer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mike on 8/20/2016.
 */
public class BreweryMapActivity extends AppCompatActivity implements OnMapReadyCallback {

    public JSONObject bObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brewery_map);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();

        //create a brewery object out of the arg string
        try {
            bObj = new JSONObject(intent.getStringExtra(BreweryListActivity.ARG_OBJ));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    // receives a string representing the JSON object of the brewery
    // returns the address attribute as a string
    public String getBreweryName(JSONObject mObj) throws JSONException {
        String breweryName;
        breweryName = mObj.getString("name");
        return breweryName;
    }

    @Override
    public void onMapReady(GoogleMap mMap) {

        try {
            mMap.addMarker(new MarkerOptions().position(new LatLng(39.351410, -94.915102))
                    .title(getBreweryName(bObj)));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                    new LatLng(39.351410, -94.915102), 16));
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMap.setMyLocationEnabled(true);

    }

}
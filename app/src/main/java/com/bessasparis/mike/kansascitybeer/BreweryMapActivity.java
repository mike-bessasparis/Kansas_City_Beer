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

    }

    @Override
    public void onMapReady(GoogleMap mMap) {

        Intent intent = getIntent();

        //create JSON brewery object from the bundle
//        try {
//            bObj = new JSONObject(intent.getStringExtra(BreweryListActivity.ARG_OBJ));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        mMap.addMarker(new MarkerOptions().position(new LatLng(39.351410, -94.915102))
                .title("CAC HQ and G6"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(39.351410, -94.915102), 16));
        mMap.setMyLocationEnabled(true);

    }

}
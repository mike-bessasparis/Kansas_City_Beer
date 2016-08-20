package com.bessasparis.mike.kansascitybeer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONObject;

/**
 * An activity representing a single Brewery detail screen. This
 * activity is only used narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link BreweryListActivity}.
 */
public class BreweryDetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    public static final String ARG_OBJ = "json-obj-string";
    public JSONObject bObj;

    @Override
    public void onMapReady(GoogleMap mMap) {
        mMap.addMarker(new MarkerOptions().position(new LatLng(39.351410, -94.915102))
                .title("CAC HQ and G6"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(
                new LatLng(39.351410, -94.915102), 16));
        mMap.setMyLocationEnabled(true);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brewery_detail);

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //create a brewery object out of the arg string
//        try {
//            bObj = new JSONObject(getArguments().getString(ARG_OBJ));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

//        Toolbar toolbar = (Toolbar) findViewById(R.id.detail_toolbar);
//        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                Uri uri = Uri.parse("geo:0,0?q=1829+Oak+Street");
//                intent.setData(uri);
//                if (intent.resolveActivity(getPackageManager()) != null) {
//                    startActivity(intent);
//                }
//            }
//        });


        // Show the Up button in the action bar.
//        ActionBar actionBar = getSupportActionBar();
//        if (actionBar != null) {
//            actionBar.setDisplayHomeAsUpEnabled(true);
//        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(BreweryDetailFragment.ARG_OBJ,
                    getIntent().getStringExtra(BreweryDetailFragment.ARG_OBJ));
            BreweryDetailFragment fragment = new BreweryDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.brewery_detail_container, fragment)
                    .commit();
        }
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

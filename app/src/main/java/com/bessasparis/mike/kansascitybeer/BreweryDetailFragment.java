/*
 * Copyright (c) 2016  Michael J. Bessasparis
 */

package com.bessasparis.mike.kansascitybeer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import static com.bessasparis.mike.kansascitybeer.MainActivity.mBreweryList;

//let me update brewery list attributes


public class BreweryDetailFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    static final String ARG_POSITION = "brewery_index";
    static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private int brewery_index;
    private String mBreweryName;
    private String mBreweryAddress;
    private String mBreweryWebsite;

    public BreweryDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onClick(View v) {
        Log.i("mjb", "onClick: ");
        switch (v.getId()) {
            case R.id.btn_map_it:
                mapButtonClicked();
                break;
            case R.id.btn_www_site:
                websiteButtonClicked();
                break;
            case R.id.btn_visited:
                mBreweryList.setBreweryVisited(brewery_index);
                break;
            case R.id.btn_not_visited:
                mBreweryList.setBreweryNotVisited(brewery_index);
                break;
        }


    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_brewery_detail, container, false);

        Button btn1 = (Button) v.findViewById(R.id.btn_visited);
        btn1.setOnClickListener(this);

        Button btn2 = (Button) v.findViewById(R.id.btn_not_visited);
        btn2.setOnClickListener(this);

        Button btn3 = (Button) v.findViewById(R.id.btn_map_it);
        btn3.setOnClickListener(this);

        Button btn4 = (Button) v.findViewById(R.id.btn_www_site);
        btn4.setOnClickListener(this);

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        // During startup, check if there are arguments passed to the fragment.
        // onStart is a good place to do this because the layout has already been
        // applied to the fragment at this point so we can safely call the method
        // below that sets the article text.
        Bundle args = getArguments();

        if (args != null) {
            brewery_index = args.getInt(ARG_POSITION);
            mBreweryName = mBreweryList.getBreweryName(brewery_index);
            mBreweryAddress = mBreweryList.getBreweryAddress(brewery_index);
            mBreweryWebsite = mBreweryList.getBreweryWebSite(brewery_index);

            ((TextView) getView().findViewById(R.id.brewery_detail_name)).
                    setText(mBreweryName);
            ((TextView) getView().findViewById(R.id.brewery_detail_address)).
                    setText(mBreweryAddress);
        }

    }

    //on a click send the map activity the brewery name
    public void mapButtonClicked() {
        Intent searchAddress = new Intent(Intent.ACTION_VIEW,
                Uri.parse("geo:0,0?q=" + mBreweryName + " " + mBreweryAddress));
        startActivity(searchAddress);
    }

    //go to www site button send browser the URL
    public void websiteButtonClicked() {
        Intent openWebsite = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://" + mBreweryWebsite));
        startActivity(openWebsite);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

}

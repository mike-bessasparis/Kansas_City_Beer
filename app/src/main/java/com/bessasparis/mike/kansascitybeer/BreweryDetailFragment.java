package com.bessasparis.mike.kansascitybeer;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A fragment representing a single Brewery detail screen.
 * This fragment is either contained in a {@link BreweryListActivity}
 * in two-pane mode (on tablets) or a {@link BreweryDetailActivity}
 * on handsets.
 */
public class BreweryDetailFragment extends Fragment {

    /**
     * The fragment argument representing the JSON object that this fragment
     * represents.
     */
    public static final String ARG_OBJ = "json-obj-string";

    public JSONObject bObj;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public BreweryDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //create a brewery object out of the arg string
        try {
            bObj = new JSONObject(getArguments().getString(ARG_OBJ));
        } catch (Exception e) {
            e.printStackTrace();
        }

        View rootView = inflater.inflate(R.layout.brewery_detail, container, false);

        try {
            ((TextView) rootView.findViewById(R.id.brewery_detail_address)).
                    setText(getBreweryAddress(bObj));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return rootView;

    }


    // receives a string representing the JSON object of the brewery
    // returns the address attribute as a string
    public String getBreweryAddress(JSONObject mObj) throws JSONException {
        String breweryAddress;
        breweryAddress = mObj.getString("address");
        return breweryAddress;
    }
}

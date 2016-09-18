/*
 * Copyright (c) 2016  Michael J. Bessasparis
 */

package com.bessasparis.mike.kansascitybeer;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by mike on 8/28/2016.
 */
public class Brewery {
    public String name;
    public String address;
    public String website;
    public Float rating;


    //constructor
    public Brewery(String s) {
        JSONObject bObj = null;

        try {
            bObj = new JSONObject(s);
            name = getBreweryName(bObj);
            address = getBreweryAddress(bObj);
            website = getBreweryWebSite(bObj);
            rating = getBreweryRating(bObj);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    // receives a string representing the JSON object of the brewery
    // returns the name attribute as a string
    private String getBreweryName(JSONObject mObj) throws JSONException {
        String breweryAddress;
        breweryAddress = mObj.getString("name");
        return breweryAddress;
    }


    // receives a string representing the JSON object of the brewery
    // returns the address attribute as a string
    private String getBreweryAddress(JSONObject mObj) throws JSONException {
        String breweryAddress;
        breweryAddress = mObj.getString("address");
        return breweryAddress;
    }

    // receives a string representing the JSON object of the brewery
    // returns the address attribute as a string
    private String getBreweryWebSite(JSONObject mObj) throws JSONException {
        String breweryWebSite;
        breweryWebSite = mObj.getString("website");
        return breweryWebSite;
    }

    // receives a string representing the JSON object of the brewery
    // returns the rating attribute as a float
    private Float getBreweryRating(JSONObject mObj) throws JSONException {
        float breweryRating;
        breweryRating = Float.parseFloat(mObj.getString("rating"));
        return breweryRating;
    }


}

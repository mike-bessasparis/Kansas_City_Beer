/*
 * Copyright (c) 2016  Michael J. Bessasparis
 */

package com.bessasparis.mike.kansascitybeer;

/**
 * Created by mike on 8/28/2016.
 */
public class Brewery {
    public String name;
    public String address;
    public String website;
    public Float rating;

    //constructor
    public Brewery() {
        name = "name";
        address = "address";
        website = "website";
        rating = 0f;
    }

//    //set the rating attribute of a specified Brewery
//    //return 1 if success, 0 otherwise
//    public void setBreweryRating(float r) {
//        rating = r;
//    }
//
//
//    // receives a string representing the JSON object of the brewery
//    // returns the name attribute as a string
//    private String getBreweryName(JSONObject mObj) throws JSONException {
//        String breweryAddress;
//        breweryAddress = mObj.getString("name");
//        return breweryAddress;
//    }
//
//
//    // receives a string representing the JSON object of the brewery
//    // returns the address attribute as a string
//    private String getBreweryAddress(JSONObject mObj) throws JSONException {
//        String breweryAddress;
//        breweryAddress = mObj.getString("address");
//        return breweryAddress;
//    }
//
//    // receives a string representing the JSON object of the brewery
//    // returns the address attribute as a string
//    private String getBreweryWebSite(JSONObject mObj) throws JSONException {
//        String breweryWebSite;
//        breweryWebSite = mObj.getString("website");
//        return breweryWebSite;
//    }
//
//    // receives a string representing the JSON object of the brewery
//    // returns the rating attribute as a float
//    private Float getBreweryRating(JSONObject mObj) throws JSONException {
//        float breweryRating;
//        breweryRating = Float.parseFloat(mObj.getString("rating"));
//        return breweryRating;
//    }


}

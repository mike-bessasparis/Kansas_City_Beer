/*
 * Copyright (c) 2016  Michael J. Bessasparis
 */

package com.bessasparis.mike.kansascitybeer;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by mike on 8/28/2016.
 * <p/>
 * Handles the data.  The data is stored as a JSON object.
 */
public class BreweryData {
    public int size;
    private String jsonString;
    private JSONObject jsonObj;
    private JSONArray breweryArray;
    private String breweries_file = "breweries.json";


    //constructor
    public BreweryData() {

        setupJsonObject();

    }

    //read JSON string into a JSON array of breweries
    private void setupJsonObject() {
        try {
            jsonString = loadJSONFromAsset();
            jsonObj = new JSONObject(jsonString);
            breweryArray = jsonObj.getJSONArray("breweries");
            size = breweryArray.length();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //read JSON string from file
    private String loadJSONFromAsset() {
        String json = "{breweries: [" +
                "{'name':'Amerisports', " +
                "'address':'3200 North Ameristar Drive'," +
                "'website':'www.ameristar.com/kansas-city/amerisports'}," +
                "{'name': 'Big Rip Brewing'," +
                "'address': '216 East Ninth Avenue'," +
                "'website': 'bigripbrewing.com'}" +
                "]}";

        return json;
    }


    //return a specified BreweryObject
    public void getBreweryObject() {

    }

    //return a specified attribute of a specified breweryObject
    public String getBreweryAttribute(String reqAttribute, int i) {
        String a = "dummy";
        try {
            a = breweryArray.getJSONObject(i).getString(reqAttribute);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (a);
    }
}
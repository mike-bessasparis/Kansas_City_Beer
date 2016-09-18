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
            jsonString = loadJSON();
            jsonObj = new JSONObject(jsonString);
            breweryArray = jsonObj.getJSONArray("breweries");
            size = breweryArray.length();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //initialize JSON string
    private String loadJSON() {
        String json = "{breweries: [" +
                "{'name':'Amerisports', 'address':'3200 North Ameristar Drive', 'website':'www.ameristar.com/kansas-city/amerisports', 'rating':'0'}," +
                "{'name':'Big Rip Brewing', 'address':'216 East Ninth Avenue', 'website':'bigripbrewing.com', 'rating':'0'}," +
                "{'name':'Border Brewing','address':'406 East 18th Street','website':'borderbrewco.com', 'rating':'0'}," +
                "{'name':'Boulevard Brewing','address':'2534 Madison Street','website':'boulevard.com', 'rating':'0'}," +
                "{'name':'Brewery Emperial', 'address':'1829 Oak Street', 'website':'breweryemperial.com', 'rating':'0'}," +
                "{'name':'Calibration Brewery', 'address':'119 Armour Road, North Kansas City', 'website':'calibrationbrewery.com', 'rating':'0'}," +
                "{'name':'Cinder Block Brewery', 'address':'110 East 18th Avenue, North Kansas City', 'website':'cinderblockbrewery.com', 'rating':'0'}," +
                "{'name':'Crane Brewing', 'address':'6515 Railroad, Raytown', 'website':'cranebrewing.com', 'rating':'0'}," +
                "{'name':'Double Shift Brewing', 'address':'412 East 18th Street', 'website':'doubleshiftbrewing.com', 'rating':'0'}," +
                "{'name':'Free State Brewing', 'address':'636 Massachusetts, Lawrence', 'website':'freestatebrewing.com', 'rating':'0'}," +
                "{'name':'Green Room Burgers and Beer','address': '4010 Pennsylvania', 'website':'greenroomkc.com', 'rating':'0'}," +
                "{'name':'KC Bier', 'address':'310 West 79th Street', 'website':'kcbier.com', 'rating':'0'}," +
                "{'name':'Martin City Brewing', 'address':'410 East 135th Street', 'website':'martincitybrewingcompany.com', 'rating':'0'}," +
                "{'name':'McCoys Public House', 'address':'4057 Pennsylvania', 'website':'mccoyskc.com', 'rating':'0'}," +
                "{'name':'Red Crow Brewing', 'address':'20561 South Lone Elm Road, Spring Hill', 'website':'redcrowbrew.com', 'rating':'0'}," +
                "{'name':'Rock and Run Brewery', 'address':'114 East Kansas Street, Liberty', 'website':'rockandrunbrewery.com', 'rating':'0'}," +
                "{'name':'Stockyards Brewing', 'address':'1600 Genesee', 'website':'stockyardsbrewing.com', 'rating':'0'}," +
                "{'name':'Tallgrass Brewing', 'address':'5960 Dry Hop Circle, Manhatten', 'website':'tallgrassbeer.com', 'rating':'0'}," +
                "{'name':'Torn Label Brewing','address':'1708 Campbell', 'website':'tornlabel.com', 'rating':'0'}," +
                "{'name':'23rd Street Brewery', 'address':'3512 Clinton Parkway, Lawrence', 'website':'brew23.com', 'rating':'0'}," +
                "{'name':'Weston Brewing', 'address':'500 Welt Street, Weston', 'website':'westonirish.com', 'rating':'0'}" +
                "]}";

        return json;
    }


    //return a specified BreweryObject
    public JSONObject getBreweryObject(int i) {
        JSONObject breweryObject = null;
        try {
            breweryObject = breweryArray.getJSONObject(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (breweryObject);
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
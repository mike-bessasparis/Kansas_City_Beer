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
                "{'name':'Amerisports', 'address':'3200 North Ameristar Drive', 'website':'www.ameristar.com/kansas-city/amerisports'}," +
                "{'name':'Big Rip Brewing', 'address':'216 East Ninth Avenue', 'website':'bigripbrewing.com'}," +
                "{'name':'Border Brewing','address':'406 East 18th Street','website':'borderbrewco.com'}," +
                "{'name':'Boulevard Brewing','address':'2534 Madison Street','website':'boulevard.com'}," +
                "{'name':'Brewery Emperial', 'address':'1829 Oak Street', 'website':'breweryemperial.com'}," +
                "{'name':'Calibration Brewery', 'address':'119 Armour Road, North Kansas City', 'website':'calibrationbrewery.com'}," +
                "{'name':'Cinder Block Brewery', 'address':'110 East 18th Avenue, North Kansas City', 'website':'cinderblockbrewery.com'}," +
                "{'name':'Crane Brewing', 'address':'6515 Railroad, Raytown', 'website':'cranebrewing.com'}," +
                "{'name':'Double Shift Brewing', 'address':'412 East 18th Street', 'website':'doubleshiftbrewing.com'}," +
                "{'name':'Free State Brewing', 'address':'636 Massachusetts, Lawrence', 'website':'freestatebrewing.com'}," +
                "{'name':'Green Room Burgers and Beer','address': '4010 Pennsylvania', 'website':'greenroomkc.com'}," +
                "{'name':'KC Bier', 'address':'310 West 79th Street', 'website':'kcbier.com'}," +
                "{'name':'Martin City Brewing', 'address':'410 East 135th Street', 'website':'martincitybrewingcompany.com'}," +
                "{'name':'McCoys Public House', 'address':'4057 Pennsylvania', 'website':'mccoyskc.com'}," +
                "{'name':'Red Crow Brewing', 'address':'20561 South Lone Elm Road, Spring Hill', 'website':'redcrowbrew.com'}," +
                "{'name':'Rock and Run Brewery', 'address':'114 East Kansas Street, Liberty', 'website':'rockandrunbrewery.com'}," +
                "{'name':'Stockyards Brewing', 'address':'1600 Genesee', 'website':'stockyardsbrewing.com'}," +
                "{'name':'Tallgrass Brewing', 'address':'5960 Dry Hop Circle, Manhatten', 'website':'tallgrassbeer.com'}," +
                "{'name':'Torn Label Brewing','address':'1708 Campbell', 'website':'tornlabel.com'}," +
                "{'name':'23rd Street Brewery', 'address':'3512 Clinton Parkway, Lawrence', 'website':'brew23.com'}," +
                "{'name':'Weston Brewing', 'address':'500 Welt Street, Weston', 'website':'westonirish.com'}" +
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
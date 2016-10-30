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
public class BreweryList {

    private int NUMBER_OF_BREWERIES = 21;

    //TODO maybe this is more elegant as an ArrayList
    private Brewery breweryArray[] = new Brewery[NUMBER_OF_BREWERIES];

    private JSONArray JSONbreweryArray;

    //constructor
    public BreweryList() {
        buildBreweryList();
    }

    //Build brewery list array from JSON array
    private void buildBreweryList() {

        setupJsonObject();

        for (int i = 0; i < NUMBER_OF_BREWERIES; i++) {
            breweryArray[i] = new Brewery();
            breweryArray[i].name = getBreweryAttribute("name", i);
            breweryArray[i].address = getBreweryAttribute("address", i);
            breweryArray[i].website = getBreweryAttribute("website", i);
            breweryArray[i].rating = Float.parseFloat(getBreweryAttribute("rating", i));
            breweryArray[i].visited = getBreweryAttribute("visited", i).equals("true");
        }

    }

    //TODO receive object and update brewery array with rating
    public void updateBreweryList(Brewery bObj) {
        breweryArray[getIndexOfObject(bObj)].rating = bObj.rating;
    }

    //TODO find index of object with specified name
    private int getIndexOfObject(Brewery bObj) {
        int i = 0;
        while (breweryArray[i].name != bObj.name) {
            ++i;
        }
        return (i);
    }


    //get brewery name of object at index i
    public String getBreweryName(int i) {
        return (breweryArray[i].name);
    }

    //get brewery address of object at index i
    public String getBreweryAddress(int i) {
        return (breweryArray[i].address);
    }

    //get brewery website of object at index i
    public String getBreweryWebSite(int i) {
        return (breweryArray[i].website);
    }

    //set brewery visited
    public void setBreweryVisited(int i) {
        breweryArray[i].visited = true;
    }

    //set brewery not visited
    public void setBreweryNotVisited(int i) {
        breweryArray[i].visited = false;
    }


    public String getVisited(int i) {
        if (breweryArray[i].visited == true) {
            return ("Been here.");
        } else {
            return ("Not yet");
        }
    }

    //get brewery array size
    public int getSize() {
        return (NUMBER_OF_BREWERIES);
    }

    //get brewery object at index i
    public Brewery getBrewery(int i) {
        return (breweryArray[i]);
    }


    //TODO get index of brewery with specified name

    //TODO update brewery rating


    //read JSON string into a JSON array of breweries
    private void setupJsonObject() {
        String jsonString;
        JSONObject jsonObj;
        try {
            jsonString = loadJSON();
            jsonObj = new JSONObject(jsonString);
            JSONbreweryArray = jsonObj.getJSONArray("breweries");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //get JSON string from storage
    private String loadJSON() {
        String json = "{breweries: [" +
                "{'name':'Amerisports', 'address':'3200 North Ameristar Drive', 'website':'www.ameristar.com/kansas-city/amerisports', 'rating':'0', 'visited': 'false'}," +
                "{'name':'Big Rip Brewing', 'address':'216 East Ninth Avenue', 'website':'bigripbrewing.com', 'rating':'0', 'visited': 'false'}," +
                "{'name':'Border Brewing','address':'406 East 18th Street','website':'borderbrewco.com', 'rating':'0', 'visited': 'false'}," +
                "{'name':'Boulevard Brewing','address':'2534 Madison Street','website':'boulevard.com', 'rating':'0', 'visited': 'false'}," +
                "{'name':'Brewery Emperial', 'address':'1829 Oak Street', 'website':'breweryemperial.com', 'rating':'0', 'visited': 'false'}," +
                "{'name':'Calibration Brewery', 'address':'119 Armour Road, North Kansas City', 'website':'calibrationbrewery.com', 'rating':'0', 'visited': 'false'}," +
                "{'name':'Cinder Block Brewery', 'address':'110 East 18th Avenue, North Kansas City', 'website':'cinderblockbrewery.com', 'rating':'0', 'visited': 'false'}," +
                "{'name':'Crane Brewing', 'address':'6515 Railroad, Raytown', 'website':'cranebrewing.com', 'rating':'0', 'visited': 'false'}," +
                "{'name':'Double Shift Brewing', 'address':'412 East 18th Street', 'website':'doubleshiftbrewing.com', 'rating':'0', 'visited': 'false'}," +
                "{'name':'Free State Brewing', 'address':'636 Massachusetts, Lawrence', 'website':'freestatebrewing.com', 'rating':'0', 'visited': 'false'}," +
                "{'name':'Green Room Burgers and Beer','address': '4010 Pennsylvania', 'website':'greenroomkc.com', 'rating':'0', 'visited': 'false'}," +
                "{'name':'KC Bier', 'address':'310 West 79th Street', 'website':'kcbier.com', 'rating':'3', 'visited': 'false'}," +
                "{'name':'Martin City Brewing', 'address':'410 East 135th Street', 'website':'martincitybrewingcompany.com', 'rating':'0', 'visited': 'false'}," +
                "{'name':'McCoys Public House', 'address':'4057 Pennsylvania', 'website':'mccoyskc.com', 'rating':'0', 'visited': 'false'}," +
                "{'name':'Red Crow Brewing', 'address':'20561 South Lone Elm Road, Spring Hill', 'website':'redcrowbrew.com', 'rating':'0', 'visited': 'false'}," +
                "{'name':'Rock and Run Brewery', 'address':'114 East Kansas Street, Liberty', 'website':'rockandrunbrewery.com', 'rating':'0', 'visited': 'false'}," +
                "{'name':'Stockyards Brewing', 'address':'1600 Genesee', 'website':'stockyardsbrewing.com', 'rating':'0', 'visited': 'false'}," +
                "{'name':'Tallgrass Brewing', 'address':'5960 Dry Hop Circle, Manhatten', 'website':'tallgrassbeer.com', 'rating':'0', 'visited': 'false'}," +
                "{'name':'Torn Label Brewing','address':'1708 Campbell', 'website':'tornlabel.com', 'rating':'0', 'visited': 'false'}," +
                "{'name':'23rd Street Brewery', 'address':'3512 Clinton Parkway, Lawrence', 'website':'brew23.com', 'rating':'0', 'visited': 'false'}," +
                "{'name':'Weston Brewing', 'address':'500 Welt Street, Weston', 'website':'westonirish.com', 'rating':'0', 'visited': 'false'}" +
                "]}";

        return json;
    }

    //return a specified attribute of a specified breweryObject as a string
    public String getBreweryAttribute(String reqAttribute, int i) {
        String a = "dummy";
        try {
            a = JSONbreweryArray.getJSONObject(i).getString(reqAttribute);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (a);
    }


    //TODO save the JSON string
    public int saveBreweryData(String b) {
        return (1);
    }

    //TODO read the JSON string
    public String readBreweryData() {
        return ("1");
    }

}
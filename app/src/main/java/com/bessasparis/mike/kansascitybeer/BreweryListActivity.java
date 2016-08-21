package com.bessasparis.mike.kansascitybeer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

/**
 * An activity representing a list of Breweries. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link BreweryDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class BreweryListActivity extends AppCompatActivity {

    public static final String ARG_OBJ = "json-obj-string";
    public String jsonString;
    public JSONObject jsonObj;
    public JSONArray breweryArray;
    public int numberOfBreweries = 0;
    private String breweries_file = "breweries.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brewery_list);
        Toolbar myToolBar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolBar);

        setupJsonObject();

        View breweriesListView = findViewById(R.id.brewery_list);
        assert breweriesListView != null;

        setupRecyclerView((RecyclerView) breweriesListView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.brewery_list_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setupJsonObject() {
        try {
            jsonString = loadJSONFromAsset();
            jsonObj = new JSONObject(jsonString);
            breweryArray = jsonObj.getJSONArray("breweries");
            numberOfBreweries = breweryArray.length();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open(breweries_file);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public String getBreweryName(int i) throws JSONException {
        String breweryName;

        JSONObject breweryObj = breweryArray.getJSONObject(i);
        breweryName = breweryObj.getString("name");
        return breweryName;
    }


    private void setupRecyclerView(@NonNull RecyclerView myRecyclerView) {
        myRecyclerView.setAdapter(new myAdapter());
    }

    public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.brewery_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {

            try {
                holder.mContentView.setText(getBreweryName(position));
            } catch (Exception e) {
                e.printStackTrace();
            }

            //on a click send the detail activity the brewery JSON object as a string
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        Context context = v.getContext();
                        Intent intent = new Intent(context, BreweryDetailActivity.class);

                        try {
                            intent.putExtra(ARG_OBJ,
                                    breweryArray.getJSONObject(holder.getAdapterPosition()).toString());
                            context.startActivity(intent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                }
            });
        }

        @Override
        public int getItemCount() {
            return (numberOfBreweries);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mContentView;

            public ViewHolder(View v) {
                super(v);
                mView = v;
                mContentView = (TextView) v.findViewById(R.id.content);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }
}

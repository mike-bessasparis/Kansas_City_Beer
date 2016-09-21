/*
 * Copyright (c) 2016  Michael J. Bessasparis
 */

package com.bessasparis.mike.kansascitybeer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * An activity representing a list of Breweries.
 */
public class BreweryListActivity extends AppCompatActivity {

    public BreweryList bdata = new BreweryList(); //JSON formatted all brewery data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brewery_list);
        Toolbar myToolBar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolBar);

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
                displayAboutBox();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void displayAboutBox() {

        Toast toast = Toast.makeText(BreweryListActivity.this,
                "v0.2.0, by mikeb", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    private void setupRecyclerView(@NonNull RecyclerView myRecyclerView) {
        myRecyclerView.setAdapter(new myAdapter());
    }


    //TODO - show rating in brewery list
    public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.brewery_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {

            holder.mNameView.setText(bdata.getBreweryAttribute("name", position));
            holder.mVisited.setText("Not Yet");

            //on a click send the detail activity the brewery JSON object as a string
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, BreweryDetailActivity.class);
                    intent.putExtra("brewery-object",
                            bdata.getBreweryObject(holder.getAdapterPosition()).toString());
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return (bdata.size);
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mNameView;
            public final TextView mVisited;

            public ViewHolder(View v) {
                super(v);
                mView = v;
                mNameView = (TextView) v.findViewById(R.id.name);
                mVisited = (TextView) v.findViewById(R.id.visited);
            }

            @Override
            public String toString() {
//                return super.toString() + " '" + mNameView.getText() + "'";
//                return super.toString() + "BreweryListItem";
                return getClass().getName() + '@' + Integer.toHexString(hashCode());
            }
        }
    }
}

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

    public BreweryData bdata = new BreweryData(); //JSON formatted all brewery data

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
        toast.setGravity(Gravity.CENTER | Gravity.CENTER, 0, 0);
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

//            float mRtg;

            holder.mContentView.setText(bdata.getBreweryAttribute("name", position));
//            holder.mRating.setRating(Float.parseFloat(bdata.getBreweryAttribute("rating", position)));

//            mRtg = Float.parseFloat(bdata.getBreweryAttribute("rating", position));


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
            public final TextView mContentView;
//            public final RatingBar mRating;

            public ViewHolder(View v) {
                super(v);
                mView = v;
                mContentView = (TextView) v.findViewById(R.id.content);
//                mRating = (RatingBar) v.findViewById(R.id.rating);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }
}

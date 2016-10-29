/*
 * Copyright (c) 2016  Michael J. Bessasparis
 */

package com.bessasparis.mike.kansascitybeer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * An activity representing a list of Breweries.
 */
public class BreweryListFragment extends Fragment {

    public BreweryList mBreweryList = new BreweryList();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_brewery_list, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        View breweriesListView = getView().findViewById(R.id.brewery_list);
        assert breweriesListView != null;
        setupRecyclerView((RecyclerView) breweriesListView);
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

            holder.mNameView.setText(mBreweryList.getBreweryName(position));
            holder.mVisited.setText(mBreweryList.getVisited(position));

            //on a click send the detail activity the position clicked
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, BreweryDetailActivity.class);
                    intent.putExtra("brewery-index", holder.getAdapterPosition());
                    context.startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return (mBreweryList.getSize());
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
                return getClass().getName() + '@' + Integer.toHexString(hashCode());
            }
        }
    }
}

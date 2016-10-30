/*
 * Copyright (c) 2016  Michael J. Bessasparis
 */

package com.bessasparis.mike.kansascitybeer;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static com.bessasparis.mike.kansascitybeer.MainActivity.mBreweryList;

/**
 * An activity representing a list of Breweries.
 */
public class BreweryListFragment extends Fragment {

    final static String ARG_POSITION = "brewery_index";

    OnBrewerySelectedListener mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnBrewerySelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " HEY, must implement OnBrewerySelectedListener");
        }
    }

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

    // The container Activity must implement this interface so the frag can deliver messages
    public interface OnBrewerySelectedListener {
        /**
         * Called by BreweryListFragment when a list item is selected
         */
        public void onBrewerySelected(int position);
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
                    // Notify the parent activity of selected item
                    mCallback.onBrewerySelected(holder.getAdapterPosition());
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

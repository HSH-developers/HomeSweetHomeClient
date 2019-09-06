package com.example.hsh.homesweethome.furniture.presenter;

import android.content.Context;

import com.example.hsh.homesweethome.furniture.presenter.interfaces.IRecyclerViewAdapterLocationsPresenter;
import com.example.hsh.homesweethome.furniture.view.interfaces.RecyclerViewHolderLocation;

import java.util.ArrayList;

public class RecyclerViewAdapterLocationsPresenter implements IRecyclerViewAdapterLocationsPresenter {

    private ArrayList<String> locations;
    private Context activityContext;

    public RecyclerViewAdapterLocationsPresenter(ArrayList<String> locations, Context activityContext) {
        this.locations = locations;
        this.activityContext = activityContext;
    }

    @Override
    public void onBindLocationAtPosition(int position, RecyclerViewHolderLocation viewHolder) {
        viewHolder.setLocation(locations.get(position));
    }

    @Override
    public int getLocationsCount() {
        return locations.size();
    }
}

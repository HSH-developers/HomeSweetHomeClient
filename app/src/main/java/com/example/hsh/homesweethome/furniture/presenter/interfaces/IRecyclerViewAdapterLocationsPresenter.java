package com.example.hsh.homesweethome.furniture.presenter.interfaces;

import com.example.hsh.homesweethome.furniture.view.interfaces.RecyclerViewHolderLocation;

public interface IRecyclerViewAdapterLocationsPresenter {

    void onBindLocationAtPosition(int position, RecyclerViewHolderLocation viewHolder);
    int getLocationsCount();
}

package com.example.hsh.homesweethome.furniture.presenter.interfaces;

import android.content.Context;

import com.example.hsh.homesweethome.furniture.view.interfaces.RecyclerViewHolderFurniture;

public interface IRecyclerViewAdapterFurniturePresenter {
    void onBindFurnitureViewAtPosition(int position, RecyclerViewHolderFurniture viewHolder);
    int getFurnitureCount();
    Context getActivityContext();
}

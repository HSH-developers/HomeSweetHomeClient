package com.example.hsh.homesweethome.furniture.presenter;

import android.content.Context;

import com.example.hsh.homesweethome.Models.Furniture;
import com.example.hsh.homesweethome.furniture.view.interfaces.RecyclerViewHolderFurniture;
import com.example.hsh.homesweethome.furniture.presenter.interfaces.IRecyclerViewAdapterFurniturePresenter;

import java.util.ArrayList;

public class RecyclerViewAdapterFurniturePresenter implements IRecyclerViewAdapterFurniturePresenter {

    private ArrayList<com.example.hsh.homesweethome.Models.Furniture> furniture;
    private ArrayList<Furniture> furnitureFiltered;
    private Context activityContext;

    public RecyclerViewAdapterFurniturePresenter(ArrayList<Furniture> furniture, Context activityContext) {
        this.furniture = furniture;
        this.furnitureFiltered = furniture;
        this.activityContext = activityContext;
    }

    @Override
    public void onBindFurnitureViewAtPosition(int position, RecyclerViewHolderFurniture viewHolder) {
        viewHolder.setFurnitureBrand(furniture.get(position).getBrand());
        viewHolder.setFurnitureTitle(furniture.get(position).getName());
        viewHolder.setFurniturePrice(furniture.get(position).getPrice().toString());
        viewHolder.setOnClickListenerFurnitureCard(activityContext, furniture.get(position));
        viewHolder.setFurnitureImage(furniture.get(position).getImageUrl());
        viewHolder.setFurnitureBrandImage(furniture.get(position).getBrandImageUrl());
    }

    @Override
    public int getFurnitureCount() {
        return furniture.size();
    }

    @Override
    public Context getActivityContext() {
        return this.activityContext;
    }
}

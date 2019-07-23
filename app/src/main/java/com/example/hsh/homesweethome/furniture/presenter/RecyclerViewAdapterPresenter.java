package com.example.hsh.homesweethome.furniture.presenter;

import android.content.Context;

import com.example.hsh.homesweethome.Models.Furniture;
import com.example.hsh.homesweethome.furniture.RecyclerViewHolderFurniture;

import java.util.ArrayList;

public class RecyclerViewAdapterPresenter implements IRecyclerViewAdapterPresenter {

    private ArrayList<com.example.hsh.homesweethome.Models.Furniture> furniture;
    private ArrayList<Furniture> furnitureFiltered;
    private Context activityContext;

    public RecyclerViewAdapterPresenter(ArrayList<Furniture> furniture, Context activityContext) {
        this.furniture = furniture;
        this.furnitureFiltered = furniture;
        activityContext = activityContext;
    }

    @Override
    public void onBindFurnitureViewAtPosition(int position, RecyclerViewHolderFurniture viewHolder) {
        viewHolder.setFurnitureBrand(furniture.get(position).getFurnitureBrand());
        viewHolder.setFurnitureTitle(furniture.get(position).getFurnitureName());
        viewHolder.setFurniturePrice(furniture.get(position).getFurniturePrice().toString());
        viewHolder.setOnClickListenerFurnitureCard(activityContext, furniture.get(position));
        viewHolder.setFurnitureImage(furniture.get(position).getFurnitureImageUrl());
        viewHolder.setFurnitureBrandImage(furniture.get(position).getFurnitureBrandImageUrl());
    }

    @Override
    public int getFurnitureCount() {
        return furniture.size();
    }
}

package com.example.hsh.homesweethome.furniture.presenter;

import com.example.hsh.homesweethome.furniture.RecyclerViewHolderFurniture;

public interface IRecyclerViewAdapterPresenter {
    void onBindFurnitureViewAtPosition(int position, RecyclerViewHolderFurniture viewHolder);
    int getFurnitureCount();
}

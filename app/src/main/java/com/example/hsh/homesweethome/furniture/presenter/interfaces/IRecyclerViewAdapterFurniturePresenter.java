package com.example.hsh.homesweethome.furniture.presenter.interfaces;

import com.example.hsh.homesweethome.furniture.view.interfaces.RecyclerViewHolderFurniture;

public interface IRecyclerViewAdapterFurniturePresenter {
    void onBindFurnitureViewAtPosition(int position, RecyclerViewHolderFurniture viewHolder);
    int getFurnitureCount();
}

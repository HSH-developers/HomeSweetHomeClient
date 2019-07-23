package com.example.hsh.homesweethome.furniture;

public interface IRecyclerViewAdapterPresenter {
    void onBindFurnitureViewAtPosition(int position, RecyclerViewHolderFurniture viewHolder);
    int getFurnitureCount();
}

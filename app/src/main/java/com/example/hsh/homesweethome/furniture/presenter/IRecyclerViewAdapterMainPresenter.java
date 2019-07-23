package com.example.hsh.homesweethome.furniture.presenter;

import com.example.hsh.homesweethome.furniture.RecyclerViewHolderCategoryFurniture;

public interface IRecyclerViewAdapterMainPresenter {
    void onBindFurnitureCategoryAtPosition(int position, RecyclerViewHolderCategoryFurniture viewHolder);
    int getFurnitureCategoriesSize();
    void clearCategories();
}

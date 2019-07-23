package com.example.hsh.homesweethome.furniture.presenter;

import android.content.Context;

import com.example.hsh.homesweethome.Models.CategoryFurniture;
import com.example.hsh.homesweethome.furniture.RecyclerViewHolderCategoryFurniture;

import java.util.ArrayList;

public class RecyclerViewAdapterMainPresenter implements IRecyclerViewAdapterMainPresenter {
    private ArrayList<CategoryFurniture> categoriesFurniture;
    private ArrayList<CategoryFurniture> categoriesFurnitureFiltered;
    private Context activityContext;

    public RecyclerViewAdapterMainPresenter(ArrayList<CategoryFurniture> categoriesFurniture, ArrayList<CategoryFurniture> categoriesFurnitureFiltered, Context activityContext) {
        this.categoriesFurniture = categoriesFurniture;
        this.categoriesFurnitureFiltered = categoriesFurnitureFiltered;
        this.activityContext = activityContext;
    }

    @Override
    public void onBindFurnitureCategoryAtPosition(int position, RecyclerViewHolderCategoryFurniture viewHolder) {
        viewHolder.setFurnitureCategory(categoriesFurniture.get(position).getFurnitureCategory());
        viewHolder.setFurnitureCategoryCard(categoriesFurniture.get(position), activityContext);
        viewHolder.setHorizontalRecyclerView(categoriesFurniture.get(position).getFurnitures(), activityContext);
    }

    @Override
    public int getFurnitureCategoriesSize() {
        return categoriesFurnitureFiltered.size();
    }

    @Override
    public void clearCategories() {
        categoriesFurnitureFiltered.clear();
    }


}

package com.example.hsh.homesweethome.furniture.presenter;

import android.os.Bundle;

import com.example.hsh.homesweethome.Models.CategoryFurniture;
import com.example.hsh.homesweethome.furniture.view.IFurnitureCategoryFragmentView;

import java.util.ArrayList;

public class FurnitureCategoryFragmentPresenter implements IFurnitureCategoryFragmentPresenter {

    private IFurnitureCategoryFragmentView view;
    private CategoryFurniture categoryFurniture;
    private ArrayList<String> locations;



    public FurnitureCategoryFragmentPresenter(IFurnitureCategoryFragmentView view) {
        this.view = view;
    }

    @Override
    public void getCategoryFurniture(Bundle fragmentArgs) {
        categoryFurniture = (CategoryFurniture) fragmentArgs.getSerializable("category_furnitures");
        locations = fragmentArgs.getStringArrayList("locations");


    }

    @Override
    public void getFurnitureCategory(String category) {

    }

    @Override
    public void setFurnitureFilter() {
        view.setFilterView();
    }
}

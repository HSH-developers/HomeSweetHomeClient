package com.example.hsh.homesweethome.furniture.model.Interfaces;

import com.example.hsh.homesweethome.Models.CategoryFurniture;

import java.util.ArrayList;

public interface IFurnitureCategoryListModel {

    interface OnFinishedListener {
        void onFinished(ArrayList<CategoryFurniture> categories);
        void onFailure(Throwable t);
    }

    void getFurnitureList(OnFinishedListener onFinishedListener);

}

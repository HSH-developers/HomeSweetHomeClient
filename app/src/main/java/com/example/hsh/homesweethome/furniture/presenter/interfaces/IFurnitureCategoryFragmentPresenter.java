package com.example.hsh.homesweethome.furniture.presenter.interfaces;

import android.os.Bundle;

public interface IFurnitureCategoryFragmentPresenter {
    void getCategoryFurniture(Bundle fragmentArgs);

    void getFurnitureCategory(String category);

    void setFurnitureFilter();
}

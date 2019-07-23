package com.example.hsh.homesweethome.furniture;

import android.os.Bundle;

public interface IFurnitureCategoryFragmentPresenter {
    void getCategoryFurniture(Bundle fragmentArgs);

    void getFurnitureCategory(String category);

    void setFurnitureFilter();
}

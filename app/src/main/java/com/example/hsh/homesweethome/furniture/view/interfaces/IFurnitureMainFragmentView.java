package com.example.hsh.homesweethome.furniture.view.interfaces;

import com.example.hsh.homesweethome.Models.CategoryFurniture;

import java.util.ArrayList;

public interface IFurnitureMainFragmentView {
    void displayFurnitureRecyclerView(ArrayList<CategoryFurniture> categories);
    void displayFilteredFurniture(String query);
}

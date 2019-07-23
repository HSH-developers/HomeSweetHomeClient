package com.example.hsh.homesweethome.furniture;

import android.content.Context;

import com.example.hsh.homesweethome.Models.CategoryFurniture;
import com.example.hsh.homesweethome.Models.Furniture;

import java.util.ArrayList;

public interface RecyclerViewHolderCategoryFurniture {
    void setHorizontalRecyclerView(ArrayList<Furniture> furniture, Context activityContext);
    void setFurnitureCategory(String category);
    void setFurnitureCategoryCard(CategoryFurniture category, Context activityContext);

}

package com.example.hsh.homesweethome.furniture;

import android.content.Context;

import com.example.hsh.homesweethome.Models.Furniture;

public interface RecyclerViewHolderFurniture {
    void setFurnitureTitle(String title);
    void setFurnitureBrand(String brand);
    void setFurniturePrice(String price);
    void setOnClickListenerFurnitureCard(Context activityContext, Furniture furniture);
    void setFurnitureImage(String furnitureImageUrl);
    void setFurnitureBrandImage(String furnitureBrandImageUrl);
}

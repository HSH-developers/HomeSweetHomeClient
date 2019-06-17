package com.example.hsh.homesweethome.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class CategoryFurniture implements Serializable {

    @Expose
    @SerializedName("furnitureCategory")
    private String furnitureCategory;

    @Expose
    @SerializedName("furnitures")
    private ArrayList<Furniture> furnitures = new ArrayList<>();

    public CategoryFurniture(String furnitureCategory, ArrayList<Furniture> furnitures) {
        this.furnitureCategory = furnitureCategory;
        this.furnitures = furnitures;
    }

    public String getFurnitureCategory() {
        return furnitureCategory;
    }

    public void setFurnitureCategory(String furnitureCategory) {
        this.furnitureCategory = furnitureCategory;
    }

    public ArrayList<Furniture> getFurnitures() {
        return furnitures;
    }

    public void setFurnitures(ArrayList<Furniture> furnitures) {
        this.furnitures = furnitures;
    }
}

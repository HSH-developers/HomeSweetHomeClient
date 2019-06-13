package com.example.hsh.homesweethome.Models;

import java.util.ArrayList;

public class CategoryFurniture {

    private String furnitureCategory;
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

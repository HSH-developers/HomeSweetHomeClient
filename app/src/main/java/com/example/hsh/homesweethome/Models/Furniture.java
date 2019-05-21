package com.example.hsh.homesweethome.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Furniture {

    @SerializedName("furnitureBrand")
    @Expose
    private String furnitureBrand;
    @SerializedName("furnitureName")
    @Expose
    private String furnitureName;
    @SerializedName("furnitureType")
    @Expose
    private String furnitureType;
    @SerializedName("furnitureImageUrl")
    @Expose
    private String furnitureImageUrl;


    public String getFurnitureBrand() {
        return furnitureBrand;
    }

    public void setFurnitureBrand(String furnitureBrand) {
        this.furnitureBrand = furnitureBrand;
    }

    public String getFurnitureName() {
        return furnitureName;
    }

    public void setFurnitureName(String furnitureName) {
        this.furnitureName = furnitureName;
    }

    public String getFurnitureType() {
        return furnitureType;
    }

    public void setFurnitureType(String furnitureType) {
        this.furnitureType = furnitureType;
    }

    public String getFurnitureImageUrl() {
        return furnitureImageUrl;
    }

    public void setFurnitureImageUrl(String furnitureImageUrl) {
        this.furnitureImageUrl = furnitureImageUrl;
    }


}




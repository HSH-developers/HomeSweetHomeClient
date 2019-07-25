package com.example.hsh.homesweethome.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Furniture implements Serializable{

    @SerializedName("furnitureName")
    @Expose
    private String furnitureName;
    @SerializedName("furnitureBrand")
    @Expose
    private String furnitureBrand;
    @SerializedName("furnitureCategory")
    @Expose
    private String furnitureCategory;
    @SerializedName("furnitureDimension")
    @Expose
    private String furnitureDimension;
    @SerializedName("furniturePrice")
    @Expose
    private Integer furniturePrice;
    @SerializedName("furnitureType")
    @Expose
    private String furnitureType;
    @SerializedName("furnitureImageUrl")
    @Expose
    private String furnitureImageUrl;
    @SerializedName("furnitureModelUrl")
    @Expose
    private String furnitureModelUrl;
    @SerializedName("furnitureBrandImageUrl")
    @Expose
    private String furnitureBrandImageUrl;

    public String getFurnitureName() {
        return furnitureName;
    }

    public void setFurnitureName(String furnitureName) {
        this.furnitureName = furnitureName;
    }

    public String getFurnitureBrand() {
        return furnitureBrand;
    }

    public void setFurnitureBrand(String furnitureBrand) {
        this.furnitureBrand = furnitureBrand;
    }

    public String getFurnitureCategory() {
        return furnitureCategory;
    }

    public void setFurnitureCategory(String furnitureCategory) {
        this.furnitureCategory = furnitureCategory;
    }

    public String getFurnitureDimension() {
        return furnitureDimension;
    }

    public void setFurnitureDimension(String furnitureDimension) {
        this.furnitureDimension = furnitureDimension;
    }

    public Integer getFurniturePrice() {
        return furniturePrice;
    }

    public void setFurniturePrice(Integer furniturePrice) {
        this.furniturePrice = furniturePrice;
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

    public String getFurnitureModelUrl() {
        return furnitureModelUrl;
    }

    public void setFurnitureModelUrl(String furnitureModelUrl) {
        this.furnitureModelUrl = furnitureModelUrl;
    }

    public String getFurnitureBrandImageUrl() {
        return furnitureBrandImageUrl;
    }

    public void setFurnitureBrandImageUrl(String furnitureBrandImageUrl) {
        this.furnitureBrandImageUrl = furnitureBrandImageUrl;
    }
}



package com.example.hsh.homesweethome.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Furniture implements Serializable{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("brand")
    @Expose
    private String brand;
    @SerializedName("category")
    @Expose
    private String category;
    @SerializedName("dimension")
    @Expose
    private String dimension;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("image_url")
    @Expose
    private String imageUrl;
    @SerializedName("model_url")
    @Expose
    private String modelUrl;
    @SerializedName("brand_image_url")
    @Expose
    private String brandImageUrl;
    @SerializedName("inStore")
    @Expose
    private Boolean in_store;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getModelUrl() {
        return modelUrl;
    }

    public void setModelUrl(String modelUrl) {
        this.modelUrl = modelUrl;
    }

    public String getBrandImageUrl() {
        return brandImageUrl;
    }

    public void setBrandImageUrl(String brandImageUrl) {
        this.brandImageUrl = brandImageUrl;
    }

    public Boolean getInStore() {
        return in_store;
    }

    public void setInStore(Boolean in_store) {
        this.in_store = in_store;
    }
}



package com.example.hsh.homesweethome.furniture.model;

import android.util.Log;

import com.example.hsh.homesweethome.Models.CategoryFurniture;
import com.example.hsh.homesweethome.Models.Furniture;
import com.example.hsh.homesweethome.furniture.model.Interfaces.IFurnitureCategoryListModel;
import com.example.hsh.homesweethome.network.APIService;
import com.example.hsh.homesweethome.network.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FurnitureCategoryListModel implements IFurnitureCategoryListModel {

    private String tag = "FurnitureCategoryListModel";
    private static final FurnitureCategoryListModel instance = new FurnitureCategoryListModel();

    public static FurnitureCategoryListModel getInstance() {
        if ( instance == null ) {
            return new FurnitureCategoryListModel();
        }
        return instance;
    }

    private APIService mAPIService;
    private ArrayList<CategoryFurniture> categories;


    private FurnitureCategoryListModel() {
        this.mAPIService = Utils.getAPIService();
        this.categories = new ArrayList<>();
    }

    @Override
    public void getFurnitureList(OnFinishedListener onFinishedListener) {

        Call<List<Furniture>> call = mAPIService.getFurnitures();
        call.enqueue(new Callback<List<Furniture>>() {
            @Override
            public void onResponse(Call<List<Furniture>> call, Response<List<Furniture>> response) {
                if (response.isSuccessful()) {
                    Log.e(tag, "Received data from server");
                    Log.e(tag, response.body().toString());
                    HashMap<String, ArrayList<Furniture>> splitFurniture = new HashMap<>();
                    for(Furniture furniture : response.body()) {
                        if (splitFurniture.get(furniture.getCategory()) != null) {
                            splitFurniture.get(furniture.getCategory()).add(furniture);
                        } else {
                            ArrayList<Furniture> mFurniture = new ArrayList<>();
                            mFurniture.add(furniture);
                            splitFurniture.put(furniture.getCategory(), mFurniture);
                        }
                    }
                    for (String furnitureCategory : splitFurniture.keySet()) {
                        categories.add(new CategoryFurniture(furnitureCategory, splitFurniture.get(furnitureCategory)));
                    }
                    onFinishedListener.onFinished(categories);


                }
            }

            @Override
            public void onFailure(Call<List<Furniture>> call, Throwable t) {


            }
        });

    }
}

package com.example.hsh.homesweethome.furniture.model;

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

public class FurnitureCategoryCategoryListModel implements IFurnitureCategoryListModel {

    private APIService mAPIService;
    private ArrayList<CategoryFurniture> categories = new ArrayList<>();


    @Override
    public void getFurnitureList(OnFinishedListener onFinishedListener) {

        mAPIService = Utils.getAPIService();
        Call<List<Furniture>> call = mAPIService.getFurnitures();
        call.enqueue(new Callback<List<Furniture>>() {
            @Override
            public void onResponse(Call<List<Furniture>> call, Response<List<Furniture>> response) {
                if (response.isSuccessful()) {
                    HashMap<String, ArrayList<Furniture>> splitFurnitures = new HashMap<>();
                    for(Furniture furniture : response.body()) {
                        if (splitFurnitures.get(furniture.getFurnitureCategory()) != null) {
                            splitFurnitures.get(furniture.getFurnitureCategory()).add(furniture);
                        } else {
                            ArrayList<Furniture> mFurniture = new ArrayList<>();
                            mFurniture.add(furniture);
                            splitFurnitures.put(furniture.getFurnitureCategory(), mFurniture);
                        }
                    }
                    for (String furnitureCategory : splitFurnitures.keySet()) {
                        categories.add(new CategoryFurniture(furnitureCategory, splitFurnitures.get(furnitureCategory)));
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

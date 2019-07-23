package com.example.hsh.homesweethome.furniture;

import android.util.Log;

import com.example.hsh.homesweethome.Models.CategoryFurniture;
import com.example.hsh.homesweethome.Models.Furniture;
import com.example.hsh.homesweethome.network.APIService;
import com.example.hsh.homesweethome.network.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FurnitureMainFragmentPresenter implements IFurnitureMainFragmentPresenter {

    private IFurnitureMainFragmentView view;
    private APIService mAPIService;

    private ArrayList<CategoryFurniture> categories = new ArrayList<>();

    public FurnitureMainFragmentPresenter(IFurnitureMainFragmentView view) {
        this.view = view;
    }

    @Override
    public void getFurniture() {

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
                            ArrayList<Furniture> furnitures = new ArrayList<>();
                            furnitures.add(furniture);
                            splitFurnitures.put(furniture.getFurnitureCategory(), furnitures);
                        }
                    }

                    for (String furnitureCategory : splitFurnitures.keySet()) {
                        categories.add(new CategoryFurniture(furnitureCategory, splitFurnitures.get(furnitureCategory)));
                    }

                }
            }

            @Override
            public void onFailure(Call<List<Furniture>> call, Throwable t) {

            }
        });

        view.displayFurnitureRecyclerView(categories);
    }

    @Override
    public void getFilteredFurniture(String query) {

    }
}

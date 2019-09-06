package com.example.hsh.homesweethome.furniture.view;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.hsh.homesweethome.Models.CategoryFurniture;
import com.example.hsh.homesweethome.Models.Furniture;
import com.example.hsh.homesweethome.R;
import com.example.hsh.homesweethome.furniture.adapter.RecyclerViewAdapterFurniture;
import com.example.hsh.homesweethome.furniture.presenter.RecyclerViewAdapterFurniturePresenter;
import com.example.hsh.homesweethome.furniture.presenter.interfaces.IRecyclerViewAdapterFurniturePresenter;
import com.example.hsh.homesweethome.furniture.view.interfaces.RecyclerViewHolderCategoryFurniture;

import java.util.ArrayList;
import java.util.Arrays;

public class MyViewHolder extends RecyclerView.ViewHolder implements RecyclerViewHolderCategoryFurniture {
    RecyclerView horizontalRecyclerView;
    TextView furniture_category;
    CardView furniture_category_card;


    public MyViewHolder(View itemView) {
        super(itemView);
        horizontalRecyclerView = itemView.findViewById(R.id.furnitureCards);
        furniture_category = itemView.findViewById(R.id.furnitureTypeCardText);
        furniture_category_card = itemView.findViewById(R.id.furnitureTypeCard);

    }

    @Override
    public void setHorizontalRecyclerView(ArrayList<Furniture> furniture, Context activityContext) {
        IRecyclerViewAdapterFurniturePresenter presenter = new RecyclerViewAdapterFurniturePresenter(furniture, activityContext);
        RecyclerViewAdapterFurniture horizontalCards = new RecyclerViewAdapterFurniture(presenter);
        horizontalRecyclerView.setLayoutManager(new LinearLayoutManager(activityContext, LinearLayoutManager.HORIZONTAL, false));
        horizontalRecyclerView.setAdapter(horizontalCards);

    }


    @Override
    public void setFurnitureCategory(String category) {
        furniture_category.setText(category);

    }

    @Override
    public void setFurnitureCategoryCard(CategoryFurniture category, Context activityContext) {
        furniture_category.setOnClickListener(v -> {
            AppCompatActivity activity = (AppCompatActivity) activityContext;
            FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
            Fragment furnitureCategoryFragment = new FurnitureCategoryFragment();
            Bundle fragArgs = new Bundle();
            fragArgs.putSerializable("category_furnitures", category);
            fragArgs.putStringArrayList("locations", new ArrayList<>(Arrays.asList("Living Room", "Bedroom", "Kitchen", "Outdoor")));
            furnitureCategoryFragment.setArguments(fragArgs);

            ft.replace(R.id.fragment_container, furnitureCategoryFragment).addToBackStack(null).commit();
        });

    }
}

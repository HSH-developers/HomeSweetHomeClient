package com.example.hsh.homesweethome.furniture;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.hsh.homesweethome.Models.CategoryFurniture;
import com.example.hsh.homesweethome.Models.Furniture;
import com.example.hsh.homesweethome.R;

import java.util.ArrayList;
import java.util.Arrays;

public class RecyclerViewAdapterMain
        extends RecyclerView.Adapter<RecyclerViewAdapterMain.MyViewHolder>{

    private IRecyclerViewAdapterMainPresenter presenter;

    public RecyclerViewAdapterMain(IRecyclerViewAdapterMainPresenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.furniture_type_and_cards, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        presenter.onBindFurnitureCategoryAtPosition(position, holder);
    }

    @Override
    public int getItemCount() {
        return presenter.getFurnitureCategoriesSize();
    }

    public void clearData() {
        presenter.clearCategories();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder implements RecyclerViewHolderCategoryFurniture{
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
            IRecyclerViewAdapterPresenter presenter = new RecyclerViewAdapterPresenter(furniture, activityContext);
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


}

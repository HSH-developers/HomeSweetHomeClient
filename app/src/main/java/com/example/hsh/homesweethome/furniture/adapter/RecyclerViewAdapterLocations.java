package com.example.hsh.homesweethome.furniture.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.hsh.homesweethome.R;
import com.example.hsh.homesweethome.furniture.presenter.RecyclerViewAdapterLocationsPresenter;
import com.example.hsh.homesweethome.furniture.view.CategoryViewHolder;

public class RecyclerViewAdapterLocations extends RecyclerView.Adapter<CategoryViewHolder> {


    private RecyclerViewAdapterLocationsPresenter presenter;

    public RecyclerViewAdapterLocations(RecyclerViewAdapterLocationsPresenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.furniture_location_card, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        presenter.onBindLocationAtPosition(position, holder);
    }


    @Override
    public int getItemCount() {
        return presenter.getLocationsCount();
    }

}



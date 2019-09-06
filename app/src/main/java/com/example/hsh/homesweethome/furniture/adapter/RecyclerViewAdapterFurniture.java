package com.example.hsh.homesweethome.furniture.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.hsh.homesweethome.R;
import com.example.hsh.homesweethome.furniture.presenter.interfaces.IRecyclerViewAdapterFurniturePresenter;
import com.example.hsh.homesweethome.furniture.view.FurnitureViewHolder;

public class RecyclerViewAdapterFurniture extends RecyclerView.Adapter<FurnitureViewHolder>{


    private IRecyclerViewAdapterFurniturePresenter presenter;

    public RecyclerViewAdapterFurniture(IRecyclerViewAdapterFurniturePresenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public FurnitureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FurnitureViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.furniture_card, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FurnitureViewHolder holder, int position) {
        presenter.onBindFurnitureViewAtPosition(position, holder);
    }

    @Override
    public int getItemCount() {
        return presenter.getFurnitureCount();
    }

}

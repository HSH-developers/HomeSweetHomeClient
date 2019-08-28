package com.example.hsh.homesweethome.furniture.adapter;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.hsh.homesweethome.R;
import com.example.hsh.homesweethome.furniture.presenter.interfaces.IRecyclerViewAdapterMainPresenter;
import com.example.hsh.homesweethome.furniture.view.MyViewHolder;

public class RecyclerViewAdapterMain
        extends RecyclerView.Adapter<MyViewHolder>{

    private IRecyclerViewAdapterMainPresenter presenter;

    public RecyclerViewAdapterMain(IRecyclerViewAdapterMainPresenter presenter) {
        this.presenter = presenter;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(presenter.getActivityContext()).inflate(R.layout.furniture_type_and_cards, parent,false));
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


}

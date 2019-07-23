package com.example.hsh.homesweethome.furniture.view;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.hsh.homesweethome.R;
import com.example.hsh.homesweethome.furniture.view.interfaces.RecyclerViewHolderLocation;

public class CategoryViewHolder extends RecyclerView.ViewHolder implements RecyclerViewHolderLocation {

    private TextView locationText;

    public CategoryViewHolder(View itemView) {
        super(itemView);
        locationText = itemView.findViewById(R.id.location);
    }

    @Override
    public void setLocation(String location) {
        locationText.setText(location);
    }
}

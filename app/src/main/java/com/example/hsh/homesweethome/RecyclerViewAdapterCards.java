package com.example.hsh.homesweethome;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hsh.homesweethome.Models.Furniture;

import java.util.List;

public class RecyclerViewAdapterCards
        extends RecyclerView.Adapter<RecyclerViewAdapterCards.MyViewHolder>
        implements Filterable {

    private Context mContext;
    private List<Furniture> mData;

    public RecyclerViewAdapterCards(Context mContext, List<Furniture> mData) {

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView furniture_title, furniture_brand, furniture_price;
        ImageView furniture_image, furniture_brand_logo;
        CardView furniture_card;

        public MyViewHolder(View itemView) {
            super(itemView);
            furniture_title = itemView.findViewById(R.id.furnitureTitleCard);
            furniture_brand = itemView.findViewById(R.id.furnitureBrandCard);
            furniture_price = itemView.findViewById(R.id.furniturePriceCard);
            furniture_brand_logo = itemView.findViewById(R.id.furnitureBrandLogoCard);
            furniture_image = itemView.findViewById(R.id.furnitureImgCard);
            furniture_card = itemView.findViewById(R.id.furnitureCard);


        }
    }
}

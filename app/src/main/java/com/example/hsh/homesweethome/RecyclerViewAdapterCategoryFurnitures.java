package com.example.hsh.homesweethome;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hsh.homesweethome.Models.Furniture;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewAdapterCategoryFurnitures extends RecyclerView.Adapter<RecyclerViewAdapterCategoryFurnitures.FurnitureViewHolder> {


    private Context mContext;
    private ArrayList<Furniture> mData;

    public RecyclerViewAdapterCategoryFurnitures(Context mContext, ArrayList<Furniture> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public FurnitureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.furniture_card, parent, false);
        return new FurnitureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FurnitureViewHolder holder, int position) {

        Glide.with(mContext)
                .asBitmap()
                .load(mData.get(position).getFurnitureImageUrl())
                .into(holder.furniture_image);


        Picasso.get()
                .load(mData.get(position).getFurnitureBrandImageUrl())
                .fit()
                .centerInside()
                .into(holder.furniture_brand_logo);


        holder.furniture_title.setText(mData.get(position).getFurnitureName());
        holder.furniture_brand.setText(mData.get(position).getFurnitureBrand());
        holder.furniture_price.setText(mData.get(position).getFurniturePrice().toString());
        holder.furniture_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, FurnitureDetails.class);
                intent.putExtra("Furniture", mData.get(position));
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class FurnitureViewHolder extends RecyclerView.ViewHolder {

        TextView furniture_title, furniture_brand, furniture_price;
        ImageView furniture_image, furniture_brand_logo;
        CardView furniture_card;

        public FurnitureViewHolder(View itemView) {
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

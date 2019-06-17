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

import static android.support.constraint.Constraints.TAG;

public class RecyclerViewAdapterHorizontalCards extends RecyclerView.Adapter<RecyclerViewAdapterHorizontalCards.ViewHolder>{

    private ArrayList<Furniture> mData = new ArrayList<>();
    private Context mContext;
    private String TAG = "HorizontalCards";

    public RecyclerViewAdapterHorizontalCards(ArrayList<Furniture> mData, Context mContext) {
        this.mData = mData;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.furniture_card, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(mContext)
                .asBitmap()
                .load(mData.get(position).getFurnitureImageUrl())
                .into(holder.furniture_image);


        Picasso.get()
                .load(mData.get(position).getFurnitureBrandImageUrl())
                .fit()
                .centerInside()
                .into(holder.furniture_brand_logo);

//        Glide.with(mContext)
//                .asBitmap()
//                .load(mData.get(position).getFurnitureBrandImageUrl())
//                .into(holder.furniture_brand_logo);


        Log.e(TAG, mData.get(position).getFurnitureBrandImageUrl());

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

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView furniture_title, furniture_brand, furniture_price;
        ImageView furniture_image, furniture_brand_logo;
        CardView furniture_card;

        public ViewHolder(View itemView) {
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

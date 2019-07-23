package com.example.hsh.homesweethome;

import android.content.Context;
import android.content.Intent;
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

import com.example.hsh.homesweethome.Model.Furniture;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerViewAdapterFurniture extends RecyclerView.Adapter<RecyclerViewAdapterFurniture.FurnitureViewHolder> implements Filterable{


    private Context mContext;
    private ArrayList<Furniture> mData;
    private ArrayList<Furniture> mDataFiltered;

    public RecyclerViewAdapterFurniture(Context mContext, ArrayList<Furniture> mData) {
        this.mContext = mContext;
        this.mData = mData;
        this.mDataFiltered = mData;
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

        Picasso.get()
                .load(mDataFiltered.get(position).getFurnitureImageUrl())
                .fit()
                .into(holder.furniture_image);


        Picasso.get()
                .load(mDataFiltered.get(position).getFurnitureBrandImageUrl())
                .fit()
                .into(holder.furniture_brand_logo);


        holder.furniture_title.setText(mDataFiltered.get(position).getFurnitureName());
        holder.furniture_brand.setText(mDataFiltered.get(position).getFurnitureBrand());
        holder.furniture_price.setText(mDataFiltered.get(position).getFurniturePrice().toString());
        holder.furniture_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext, FurnitureDetails.class);
                intent.putExtra("Furniture", mDataFiltered.get(position));
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mDataFiltered = mData;
                } else {
                    /**
                     * TODO : Change the way the list is filtered, i want a lambda sorting function that filters it according to % of the slider bar
                     */
                    ArrayList<Furniture> filteredList = new ArrayList<>();
                    Integer filteredPrice = Integer.parseInt(charString);
                    for (Furniture furniture : mData) {
                        if (furniture.getFurniturePrice() > filteredPrice) {
                            filteredList.add(furniture);
                        }
                    }
                    mDataFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mDataFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mDataFiltered = (ArrayList<Furniture>) results.values;
                notifyDataSetChanged();
            }
        };
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

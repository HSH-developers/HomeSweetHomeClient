package com.example.hsh.homesweethome;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapterMain extends RecyclerView.Adapter<RecyclerViewAdapterMain.MyViewHolder> implements Filterable{

    private Context mContext ;
    private List<Furniture> mData ;
    private List<Furniture> mDataFiltered;

    public RecyclerViewAdapterMain(Context mContext, List<Furniture> mData) {
        this.mContext = mContext;
        this.mData = mData;
        this.mDataFiltered = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.furniture_card,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.furniture_title.setText(mDataFiltered.get(position).getFurnitureName());

        String UriString = mDataFiltered.get(position).getFurnitureImageUrl();
        if(UriString!=null){
            Uri uri = Uri.parse(UriString);
            Picasso.get().load(uri).into(holder.furniture_image);
        }

        holder.furniture_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, FurnitureDetails.class);
                intent.putExtra("FurnitureName" , mDataFiltered.get(position).getFurnitureName());
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
                    List<Furniture> filteredList = new ArrayList<>();
                    for (Furniture row : mData) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getFurnitureBrand().toLowerCase().contains(charString.toLowerCase()) || row.getFurnitureName().contains(charSequence) || row.getFurnitureType().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    mDataFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mDataFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mDataFiltered = (ArrayList<Furniture>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView furniture_title;
        ImageView furniture_image;
        CardView furniture_card;

        public MyViewHolder(View itemView) {
            super(itemView);
            furniture_title = itemView.findViewById(R.id.furnitureTitleCard);
            furniture_image = itemView.findViewById(R.id.furnitureImgCard);
            furniture_card = itemView.findViewById(R.id.furnitureCard);


        }
    }

}

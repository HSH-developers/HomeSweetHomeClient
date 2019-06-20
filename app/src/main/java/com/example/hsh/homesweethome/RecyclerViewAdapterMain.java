package com.example.hsh.homesweethome;


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

import java.util.ArrayList;
import java.util.Arrays;

public class RecyclerViewAdapterMain
        extends RecyclerView.Adapter<RecyclerViewAdapterMain.MyViewHolder> implements Filterable{

    private Context mContext ;
    private ArrayList<CategoryFurniture> mData ;
    private ArrayList<CategoryFurniture> mDataFiltered;
    private String TAG = "RecyclerViewAdapterMain";



    public RecyclerViewAdapterMain(Context mContext, ArrayList<CategoryFurniture> mData) {
        this.mContext = mContext;
        this.mData = mData;
        this.mDataFiltered = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.furniture_type_and_cards, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        RecyclerViewAdapterFurniture horizontalCards = new RecyclerViewAdapterFurniture(mContext, mDataFiltered.get(position).getFurnitures());
        holder.horizontalRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.HORIZONTAL, false));
        holder.horizontalRecyclerView.setAdapter(horizontalCards);

        holder.furniture_category.setText(mDataFiltered.get(position).getFurnitureCategory());
        holder.furniture_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatActivity activity = (AppCompatActivity) mContext;
                FragmentTransaction ft = activity.getSupportFragmentManager().beginTransaction();
                Fragment furnitureCategoryFragment = new FurnitureCategoryFragment();
                Bundle fragArgs = new Bundle();
                CategoryFurniture categoryFurniture = mDataFiltered.get(position);
                fragArgs.putSerializable("category_furnitures", categoryFurniture);
                fragArgs.putStringArrayList("locations", new ArrayList<>(Arrays.asList("Living Room", "Bedroom", "Kitchen", "Outdoor")));
                furnitureCategoryFragment.setArguments(fragArgs);

                ft.replace(R.id.fragment_container, furnitureCategoryFragment).addToBackStack(null).commit();
            }
        });

    }

    @Override
    public int getItemCount() {
        Log.e(TAG, "Item count: " + Integer.toString(mDataFiltered.size()));

        for (int i = 0 ;i < mDataFiltered.size(); i++) {
            Log.e(TAG, "Item count furnitures: " + Integer.toString(mDataFiltered.get(i).getFurnitures().size()));
            for (Furniture furniture : mDataFiltered.get(i).getFurnitures()) {
                Log.e(TAG, furniture.getFurnitureName());
            }
        }
        return mDataFiltered.size();
    }

    public void clearData() {
        mDataFiltered.clear();
    }

    /**
     * TODO : Implement fuzzy search logic
     */
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mDataFiltered = mData;
                } else {
                    ArrayList<CategoryFurniture> filteredList = new ArrayList<>();
                    for (CategoryFurniture category : mData) {
                        CategoryFurniture filteredCategoryFurniture = new CategoryFurniture(category.getFurnitureCategory(), category.getFurnitures());
                        ArrayList<Furniture> filteredFurnituresCategory = new ArrayList<>();
                        for (Furniture row : category.getFurnitures()) {
                            // name match condition. this might differ depending on your requirement
                            // here we are looking for name or phone number match
                            if (row.getFurnitureBrand().toLowerCase().contains(charString.toLowerCase()) || row.getFurnitureName().contains(charSequence) || row.getFurnitureType().contains(charSequence)) {
                                filteredFurnituresCategory.add(row);
                            }
                        }
                        filteredCategoryFurniture.setFurnitures(filteredFurnituresCategory);
                        filteredList.add(filteredCategoryFurniture);
                    }

                    mDataFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mDataFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mDataFiltered = (ArrayList<CategoryFurniture>) filterResults.values;
                Log.e(TAG, "Notifying data set changed");
                notifyDataSetChanged();
            }
        };
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        RecyclerView horizontalRecyclerView;
        TextView furniture_category;
        CardView furniture_category_card;


        public MyViewHolder(View itemView) {
            super(itemView);
            horizontalRecyclerView = itemView.findViewById(R.id.furnitureCards);
            furniture_category = itemView.findViewById(R.id.furnitureTypeCardText);
            furniture_category_card = itemView.findViewById(R.id.furnitureTypeCard);

        }
    }


}

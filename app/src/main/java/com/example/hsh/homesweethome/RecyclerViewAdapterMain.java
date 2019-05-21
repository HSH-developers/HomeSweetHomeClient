package com.example.hsh.homesweethome;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hsh.homesweethome.Models.Furniture;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapterMain extends RecyclerView.Adapter<RecyclerViewAdapterMain.MyViewHolder> {

    private Context mContext ;
    private List<Furniture> mData ;

    public RecyclerViewAdapterMain(Context mContext, List<Furniture> mData) {
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view ;
        LayoutInflater mInflater = LayoutInflater.from(mContext);
        view = mInflater.inflate(R.layout.activity_main,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.furniture_title.setText(mData.get(position).getFurnitureName());

        String UriString = mData.get(position).getFurnitureImageUrl();
        if(UriString!=null){
            Uri uri = Uri.parse(UriString);
            Picasso.get().load(uri).into(holder.furniture_image);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView furniture_title;
        ImageView furniture_image;

        public MyViewHolder(View itemView) {
            super(itemView);
            furniture_title = itemView.findViewById(R.id.furnitureTitleCard);
            furniture_image = itemView.findViewById(R.id.furnitureImgCard);


        }
    }

}

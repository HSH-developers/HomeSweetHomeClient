package com.example.hsh.homesweethome;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hsh.homesweethome.Models.Furniture;
import com.squareup.picasso.Picasso;

@SuppressLint("ValidFragment")
public class FurnitureDetailsMainFragment extends Fragment {
    private static final String TAG = "FurnitureMainFragment";
    private Furniture furniture;
    ImageView furnitureImage;
    TextView furnitureNameTextView;

    @SuppressLint("ValidFragment")
    public FurnitureDetailsMainFragment(Furniture furniture) {
        this.furniture = furniture;

        }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.furniture_details_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Uri uri = Uri.parse(furniture.getFurnitureImageUrl());

        furnitureImage = view.findViewById(R.id.furnitureMainImg);
        furnitureNameTextView = view.findViewById(R.id.furnitureMainName);
        furnitureNameTextView.setText(furniture.getFurnitureName());
        Picasso.get().load(uri).resize(70,70).centerCrop().into(furnitureImage);
    }
}

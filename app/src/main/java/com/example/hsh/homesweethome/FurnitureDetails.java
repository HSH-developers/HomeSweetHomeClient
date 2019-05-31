package com.example.hsh.homesweethome;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.hsh.homesweethome.Models.Furniture;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;
import com.squareup.picasso.Picasso;


public class FurnitureDetails extends AppCompatActivity{

    private ViewPager viewPager;
    private SpaceNavigationView bottomNavigationView;
    private String furnitureName;
    private String furnitureImage;
    private Furniture furniture;

    private String TAG = "FurnitureDetails";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Intent intent = getIntent();
        if(intent.getExtras() != null) {
            furniture = (Furniture) intent.getSerializableExtra("Furniture");
            furnitureName = intent.getExtras().getString("FurnitureName");
            furnitureImage = intent.getExtras().getString("FurnitureImage");
        }

        Log.e(TAG, furniture.getFurnitureImageUrl() );
        Log.e(TAG, furniture.getFurnitureModelUrl());


        setContentView(R.layout.furniture_details);
        viewPager = findViewById(R.id.furnitureDetailsViewPager);
        viewPager.setAdapter(new FurnitureFragmentAdapter(getSupportFragmentManager(), furnitureName, furnitureImage));
        bottomNavigationView = findViewById(R.id.bottomNavigationDetailsMain);


        bottomNavigationView.addSpaceItem(new SpaceItem("", R.drawable.sceneform_hand_phone));
        bottomNavigationView.addSpaceItem(new SpaceItem("", R.drawable.sceneform_plane));

        bottomNavigationView.setCentreButtonSelectable(true);
        bottomNavigationView.setCentreButtonSelected();
        bottomNavigationView.setActivated(true);

        viewPager.setCurrentItem(1);


        bottomNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                Intent intent = new Intent(getApplicationContext(), FurnitureARActivity.class);
                startActivity(intent);

            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                viewPager.setCurrentItem(itemIndex);
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {

            }
        });






    }



}

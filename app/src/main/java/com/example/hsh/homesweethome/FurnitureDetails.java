package com.example.hsh.homesweethome;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;


public class FurnitureDetails extends AppCompatActivity{

    private ViewPager viewPager;
    private SpaceNavigationView bottomNavigationView;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.furniture_details);
        viewPager = findViewById(R.id.furnitureDetailsViewPager);
        viewPager.setAdapter(new FurnitureFragmentAdapter(getSupportFragmentManager()));
        bottomNavigationView = findViewById(R.id.bottomNavigationDetailsMain);


        bottomNavigationView.addSpaceItem(new SpaceItem("", R.drawable.sceneform_hand_phone));
        bottomNavigationView.addSpaceItem(new SpaceItem("", R.drawable.sceneform_plane));

        bottomNavigationView.setCentreButtonSelectable(true);
        bottomNavigationView.setCentreButtonSelected();
        bottomNavigationView.setActivated(true);

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

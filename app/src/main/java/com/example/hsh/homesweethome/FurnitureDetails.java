package com.example.hsh.homesweethome;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.hsh.homesweethome.Model.Furniture;
import com.luseen.spacenavigation.SpaceItem;
import com.luseen.spacenavigation.SpaceNavigationView;
import com.luseen.spacenavigation.SpaceOnClickListener;


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
        }

        Log.e(TAG, furniture.getFurnitureImageUrl() );
        Log.e(TAG, furniture.getFurnitureModelUrl());


        setContentView(R.layout.furniture_details);
        viewPager = findViewById(R.id.furnitureDetailsViewPager);
        viewPager.setAdapter(new FurnitureFragmentAdapter(getSupportFragmentManager(), furniture));
        bottomNavigationView = findViewById(R.id.bottomNavigationDetailsMain);


        bottomNavigationView.addSpaceItem(new SpaceItem("", R.drawable.heart));
        bottomNavigationView.addSpaceItem(new SpaceItem("", R.drawable.shopping_cart));

        bottomNavigationView.setCentreButtonSelectable(true);
        bottomNavigationView.setCentreButtonSelected();
        bottomNavigationView.setActivated(true);

        viewPager.setCurrentItem(1);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                switch(position) {
                    case 0 :
                        bottomNavigationView.changeCurrentItem(0);
                        break;
                    case 1 :
                        bottomNavigationView.changeCurrentItem(1);
                        break;


                }

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });



        bottomNavigationView.setSpaceOnClickListener(new SpaceOnClickListener() {
            @Override
            public void onCentreButtonClick() {
                Intent intent = new Intent(getApplicationContext(), FurnitureARActivity.class);
                intent.putExtra("Furniture", furniture);
                startActivity(intent);

            }

            @Override
            public void onItemClick(int itemIndex, String itemName) {
                switch(itemIndex) {
                    case 0 :
                        viewPager.setCurrentItem(0);
                        break;
                    case 1 :
                        viewPager.setCurrentItem(2);
                        break;


                }
            }

            @Override
            public void onItemReselected(int itemIndex, String itemName) {

            }
        });






    }



}

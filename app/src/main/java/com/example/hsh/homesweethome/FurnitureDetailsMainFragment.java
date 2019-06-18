package com.example.hsh.homesweethome;

import android.annotation.SuppressLint;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.hsh.homesweethome.Models.Furniture;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class FurnitureDetailsMainFragment extends Fragment {
    private static final String TAG = "FurnitureMainFragment";
    private Furniture furniture;
    ImageView furnitureImage;
    TextView furnitureNameTextView;
    TextView furniturePriceTextView;
    TextView furnitureMaterialUsed;
    TextView furnitureCategory;
    TextView furnitureDimension;
    RatingBar furnitureRatingBar;
    TextView furnitureRatingScale;
    LinearLayout basicSpecification, careInstruction, review, moreLikeThis;
    HorizontalScrollView furnitureDetailsHorizontalScrollView;
    GestureDetector gestureDetector = null;
    ArrayList<LinearLayout> layouts = null;
    TabLayout tabLayout;

    LinearLayout.LayoutParams params;
    int parentLeft, parentRight;
    int mWidth;
    int currPosition, prevPosition;





    @SuppressLint("ValidFragment")
    public FurnitureDetailsMainFragment(Furniture furniture) {
        this.furniture = furniture;

        }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.furniture_details_main, container, false);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Uri uri = Uri.parse(furniture.getFurnitureImageUrl());

        furnitureImage = view.findViewById(R.id.furnitureMainImg);
        furnitureNameTextView = view.findViewById(R.id.furnitureMainName);
        furniturePriceTextView = view.findViewById(R.id.furniturePrice);
        furnitureRatingBar = view.findViewById(R.id.furnitureRating);
        furnitureRatingScale = view.findViewById(R.id.furnitureRatingScale);
        furnitureMaterialUsed = view.findViewById(R.id.basic_specification_material_used_result);
        furnitureDimension = view.findViewById(R.id.basic_specification_dimension_result);
        furnitureCategory = view.findViewById(R.id.basic_specification_category_result);
        tabLayout = view.findViewById(R.id.tab_layout);

        tabLayout.addTab(tabLayout.newTab().setText("Specifications"));
        tabLayout.addTab(tabLayout.newTab().setText("Reviews"));
        tabLayout.addTab(tabLayout.newTab().setText("More Like this"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabLayout.setScrollPosition(tab.getPosition(), 0f, true);
                furnitureDetailsHorizontalScrollView.smoothScrollTo(layouts.get(tab.getPosition())
                        .getLeft(), 0);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        String priceTag = "$"+ String.valueOf(furniture.getFurniturePrice());
        furniturePriceTextView.setText(priceTag);
        furnitureMaterialUsed.setText(furniture.getFurnitureType());
        furnitureDimension.setText(furniture.getFurnitureDimension());
        furnitureCategory.setText(furniture.getFurnitureCategory());

        furnitureDetailsHorizontalScrollView = view.findViewById(R.id.furnitureDetailsHorizontalScrollView);
        gestureDetector = new GestureDetector(new MyGestureDetector());

        basicSpecification =  view.findViewById(R.id.basic_specification);
        review = view.findViewById(R.id.reviews);
        moreLikeThis = view.findViewById(R.id.more_like_this);

        Display display = getActivity().getWindowManager().getDefaultDisplay();
        mWidth = display.getWidth(); // deprecated
        layouts = new ArrayList<>();
        params = new LinearLayout.LayoutParams(mWidth, LinearLayout.LayoutParams.WRAP_CONTENT);

        basicSpecification.setLayoutParams(params);
        review.setLayoutParams(params);
        moreLikeThis.setLayoutParams(params);

        layouts.add(basicSpecification);
        layouts.add(review);
        layouts.add(moreLikeThis);





        furnitureDetailsHorizontalScrollView.setOnTouchListener((v, event) -> {
            if (gestureDetector.onTouchEvent(event)) {
                return true;
            }
            return false;
        });


        furnitureRatingBar.setOnRatingBarChangeListener((ratingBar, v, b) -> {
            furnitureRatingScale.setText(String.valueOf(v));
            switch ((int) ratingBar.getRating()) {
                case 1:
                    furnitureRatingScale.setText("It looks disgusting");
                    break;
                case 2:
                    furnitureRatingScale.setText("Meh...");
                    break;
                case 3:
                    furnitureRatingScale.setText("Noice");
                    break;
                case 4:
                    furnitureRatingScale.setText("Holy shit, that looks good");
                    break;
                case 5:
                    furnitureRatingScale.setText("Awesome. I love it");
                    break;
                default:
                    furnitureRatingScale.setText("");
            }
        });

        furnitureNameTextView.setText(furniture.getFurnitureName());
        ViewTreeObserver vto = furnitureImage.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                furnitureImage.getViewTreeObserver().removeOnPreDrawListener(this);
                Picasso.get().load(uri).resize(furnitureImage.getMeasuredWidth(), furnitureImage.getMeasuredHeight()).centerCrop().into(furnitureImage);
                return true;
            }
        });
    }

    class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            if (e1.getX() < e2.getX()) {
                currPosition = getVisibleViews("left");
            } else {
                currPosition = getVisibleViews("right");
            }

            furnitureDetailsHorizontalScrollView.smoothScrollTo(layouts.get(currPosition)
                    .getLeft(), 0);
            tabLayout.setScrollPosition(currPosition,0f, true);
            return true;
        }
    }

    public int getVisibleViews(String direction) {
        Rect hitRect = new Rect();
        int position = 0;
        int rightCounter = 0;
        for (int i = 0; i < layouts.size(); i++) {
            if (layouts.get(i).getLocalVisibleRect(hitRect)) {
                if (direction.equals("left")) {
                    position = i;

                    break;
                } else if (direction.equals("right")) {
                    rightCounter++;
                    position = i;
                    if (rightCounter == 2)
                        break;
                }
            }
        }
        return position;
    }
}

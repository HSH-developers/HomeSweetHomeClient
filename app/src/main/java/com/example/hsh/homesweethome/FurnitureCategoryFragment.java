package com.example.hsh.homesweethome;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hsh.homesweethome.Models.CategoryFurniture;
import com.example.hsh.homesweethome.Models.Furniture;

import java.util.ArrayList;

import static com.google.sceneform_animation.bi.as;
import static com.google.sceneform_animation.bi.f;

public class FurnitureCategoryFragment extends Fragment {

    private TextView furnitureCategory;
    private RecyclerView locationsHorizontalRecyclerView;
    private RecyclerView categoryFurnitureRecyclerView;
    private RecyclerViewAdapterCategoryFurnitures recyclerViewAdapterCategoryFurnitures;
    private RecyclerViewAdapterHorizontalLocations recyclerViewAdapterHorizontalLocations;
    private ArrayList<String> locations;
    private ImageView furnitureFilter;
    private CardView apply_button;

    public FurnitureCategoryFragment() {
    }

    private CategoryFurniture categoryFurniture;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.furniture_category_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle fragmentArgs = getArguments();
        categoryFurniture = (CategoryFurniture) fragmentArgs.getSerializable("category_furnitures");
        locations = fragmentArgs.getStringArrayList("locations");

        furnitureFilter = view.findViewById(R.id.filter_furniture);
        furnitureCategory = view.findViewById(R.id.furnitureCategory);
        locationsHorizontalRecyclerView = view.findViewById(R.id.locations_horizontal_recycler);
        categoryFurnitureRecyclerView = view.findViewById(R.id.category_furnitures_recycler_view);

        locationsHorizontalRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        categoryFurnitureRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        recyclerViewAdapterHorizontalLocations = new RecyclerViewAdapterHorizontalLocations(getContext(), locations);
        recyclerViewAdapterCategoryFurnitures = new RecyclerViewAdapterCategoryFurnitures(getContext(), categoryFurniture.getFurnitures());

        locationsHorizontalRecyclerView.setAdapter(recyclerViewAdapterHorizontalLocations);
        categoryFurnitureRecyclerView.setAdapter(recyclerViewAdapterCategoryFurnitures);
        categoryFurnitureRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));

        furnitureCategory.setText(categoryFurniture.getFurnitureCategory());

        furnitureFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog filterDialog = new Dialog(getContext());
                filterDialog.setContentView(R.layout.filter_dialog);
                apply_button = filterDialog.findViewById(R.id.apply_button);
                apply_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        filterDialog.dismiss();
                    }
                });
                filterDialog.show();

            }
        });

    }
}

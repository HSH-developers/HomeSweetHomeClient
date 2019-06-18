package com.example.hsh.homesweethome;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hsh.homesweethome.Models.CategoryFurniture;
import com.example.hsh.homesweethome.Models.Furniture;

import java.util.ArrayList;

import static com.google.sceneform_animation.bi.as;

public class FurnitureCategoryFragment extends Fragment {

    private TextView furnitureCategory;
    private RecyclerView locationsHorizontalRecyclerView;
    private RecyclerView categoryFurnitureRecyclerView;
    private RecyclerViewAdapterCategoryFurnitures recyclerViewAdapterCategoryFurnitures;
    private RecyclerViewAdapterHorizontalLocations recyclerViewAdapterHorizontalLocations;
    private ArrayList<String> locations;

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


    }
}

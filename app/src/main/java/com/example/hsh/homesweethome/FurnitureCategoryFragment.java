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
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.hsh.homesweethome.Models.CategoryFurniture;

import java.util.ArrayList;

public class FurnitureCategoryFragment extends Fragment {

    private TextView furnitureCategory;
    private RecyclerView locationsHorizontalRecyclerView;
    private RecyclerView categoryFurnitureRecyclerView;
    private RecyclerViewAdapterFurniture recyclerViewAdapterFurniture;
    private RecyclerViewAdapterLocations recyclerViewAdapterLocations;
    private ArrayList<String> locations;
    private ImageView furnitureFilter;
    private CardView apply_button;
    private SeekBar price_slider_bar;

    public FurnitureCategoryFragment() {
    }

    private CategoryFurniture categoryFurniture;
    private Integer filteredPrice;


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

        recyclerViewAdapterLocations = new RecyclerViewAdapterLocations(getContext(), locations);
        recyclerViewAdapterFurniture = new RecyclerViewAdapterFurniture(getContext(), categoryFurniture.getFurnitures());

        locationsHorizontalRecyclerView.setAdapter(recyclerViewAdapterLocations);
        categoryFurnitureRecyclerView.setAdapter(recyclerViewAdapterFurniture);
        categoryFurnitureRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));

        furnitureCategory.setText(categoryFurniture.getFurnitureCategory());


        furnitureFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog filterDialog = new Dialog(getContext());
                filterDialog.setContentView(R.layout.filter_dialog);

                apply_button = filterDialog.findViewById(R.id.apply_button);
                price_slider_bar = filterDialog.findViewById(R.id.price_slider_bar);
                price_slider_bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        filteredPrice = seekBar.getProgress()/100 * 1000;
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                        filteredPrice = seekBar.getProgress()/100 * 1000;
                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        filteredPrice = seekBar.getProgress()/100 * 1000;
                    }
                });
                apply_button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        recyclerViewAdapterFurniture.getFilter().filter(filteredPrice.toString());
                        filterDialog.dismiss();
                    }
                });
                filterDialog.show();

            }
        });

    }
}

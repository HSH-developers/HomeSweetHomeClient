package com.example.hsh.homesweethome.furniture.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.hsh.homesweethome.Models.CategoryFurniture;
import com.example.hsh.homesweethome.R;
import com.example.hsh.homesweethome.common.view.BaseFragment;
import com.example.hsh.homesweethome.furniture.adapter.RecyclerViewAdapterMain;
import com.example.hsh.homesweethome.furniture.presenter.FurnitureMainFragmentPresenter;
import com.example.hsh.homesweethome.furniture.presenter.RecyclerViewAdapterMainPresenter;
import com.example.hsh.homesweethome.furniture.view.interfaces.IFurnitureMainFragmentView;

import java.util.ArrayList;

public class MainFragment extends BaseFragment implements IFurnitureMainFragmentView {

    private FurnitureMainFragmentPresenter presenter;
    private RecyclerViewAdapterMain verticalAdapter;
    private RecyclerView verticalRecyclerView;

    private SearchView searchView;

    private String tag = "MainFragment";

    public MainFragment() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.furniture_main_fragment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        presenter = new FurnitureMainFragmentPresenter(this);
        verticalRecyclerView = view.findViewById(R.id.recyclerview_id);
        searchView = getActivity().findViewById(R.id.search_view);

        presenter.getFurniture();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                presenter.getFilteredFurniture(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                presenter.getFilteredFurniture(newText);
                return false;
            }
        });

    }



    @Override
    public void displayFurnitureRecyclerView(ArrayList<CategoryFurniture> categories) {
        Log.e(tag, "displaying view");
        verticalAdapter = new RecyclerViewAdapterMain(new RecyclerViewAdapterMainPresenter(categories, getActivity()));
        verticalAdapter.setHasStableIds(true);

        verticalRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        verticalRecyclerView.setAdapter(verticalAdapter);

        verticalAdapter.notifyDataSetChanged();
    }

    @Override
    public void displayFilteredFurniture(String query) {

    }
}

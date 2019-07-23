package com.example.hsh.homesweethome.furniture;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.hsh.homesweethome.Models.CategoryFurniture;
import com.example.hsh.homesweethome.Models.Furniture;
import com.example.hsh.homesweethome.R;
import com.example.hsh.homesweethome.network.APIService;
import com.example.hsh.homesweethome.network.Utils;
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.subjects.PublishSubject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FurnitureMainFragment extends FurnitureBaseFragment implements IFurnitureMainFragmentView {

    private FurnitureMainFragmentPresenter presenter;
    private RecyclerViewAdapterMain verticalAdapter;
    private RecyclerView verticalRecyclerView;

    private SearchView searchView;

    public FurnitureMainFragment() {
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
        verticalAdapter = new RecyclerViewAdapterMain(getActivity(), categories);
        verticalAdapter.setHasStableIds(true);

        verticalRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        verticalRecyclerView.setAdapter(verticalAdapter);
        verticalAdapter.clearData();

        verticalAdapter.notifyDataSetChanged();
    }

    @Override
    public void displayFilteredFurniture(String query) {

    }
}

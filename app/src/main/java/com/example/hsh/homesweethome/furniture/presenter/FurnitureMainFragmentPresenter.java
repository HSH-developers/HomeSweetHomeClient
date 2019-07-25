package com.example.hsh.homesweethome.furniture.presenter;

import com.example.hsh.homesweethome.Models.CategoryFurniture;
import com.example.hsh.homesweethome.furniture.model.FurnitureCategoryCategoryListModel;
import com.example.hsh.homesweethome.furniture.model.Interfaces.IFurnitureCategoryListModel;
import com.example.hsh.homesweethome.furniture.presenter.interfaces.IFurnitureMainFragmentPresenter;
import com.example.hsh.homesweethome.furniture.view.interfaces.IFurnitureMainFragmentView;

import java.util.ArrayList;

public class FurnitureMainFragmentPresenter implements IFurnitureMainFragmentPresenter, IFurnitureCategoryListModel.OnFinishedListener {

    private IFurnitureMainFragmentView view;
    private IFurnitureCategoryListModel furnitureCategoryListModel;


    public FurnitureMainFragmentPresenter(IFurnitureMainFragmentView view) {
        this.view = view;
        this.furnitureCategoryListModel = new FurnitureCategoryCategoryListModel();
    }

    @Override
    public void getFurniture() {
        furnitureCategoryListModel.getFurnitureList(this);
    }

    @Override
    public void getFilteredFurniture(String query) {

    }

    @Override
    public void onFinished(ArrayList<CategoryFurniture> categories) {
        view.displayFurnitureRecyclerView(categories);
    }

    @Override
    public void onFailure(Throwable t) {

    }
}

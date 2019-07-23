package com.example.hsh.homesweethome.furniture.presenter;

import com.example.hsh.homesweethome.furniture.FurnitureBaseFragment;
import com.example.hsh.homesweethome.furniture.FurnitureMainFragment;
import com.example.hsh.homesweethome.furniture.view.IFurnitureView;

public class FurniturePresenter implements IFurniturePresenter, IFragmentNavigationPresenter {

    private IFurnitureView view;

    public FurniturePresenter(IFurnitureView view) {
        this.view = view;
    }


    @Override
    public void loadInitialFragment() {
        view.setFragment(new FurnitureMainFragment());
    }

    @Override
    public void changeFragment(FurnitureBaseFragment fragment) {
        view.setFragment(fragment);
    }
}

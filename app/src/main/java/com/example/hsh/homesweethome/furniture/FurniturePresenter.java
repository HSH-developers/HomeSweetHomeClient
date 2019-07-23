package com.example.hsh.homesweethome.furniture;

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

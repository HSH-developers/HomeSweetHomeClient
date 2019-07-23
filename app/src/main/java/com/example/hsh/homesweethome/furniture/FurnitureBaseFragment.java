package com.example.hsh.homesweethome.furniture;

import android.support.v4.app.Fragment;

public abstract class FurnitureBaseFragment extends Fragment implements IFragmentNavigationView {

    protected IFragmentNavigationPresenter navigationPresenter;

    @Override
    public void attachPresenter(IFragmentNavigationPresenter presenter) {
        navigationPresenter = presenter;
    }
}

package com.example.hsh.homesweethome.common.view;

import android.support.v4.app.Fragment;

import com.example.hsh.homesweethome.furniture.presenter.interfaces.IFragmentNavigationPresenter;
import com.example.hsh.homesweethome.common.view.interfaces.IFragmentNavigationView;

public abstract class BaseFragment extends Fragment implements IFragmentNavigationView {

    protected IFragmentNavigationPresenter navigationPresenter;

    @Override
    public void attachPresenter(IFragmentNavigationPresenter presenter) {
        navigationPresenter = presenter;
    }
}

package com.example.hsh.homesweethome.auth.login.presenter;

import com.example.hsh.homesweethome.auth.login.MainFragment;
import com.example.hsh.homesweethome.auth.login.presenter.ILoginPresenter;
import com.example.hsh.homesweethome.auth.login.view.ILoginView;
import com.example.hsh.homesweethome.common.view.BaseFragment;
import com.example.hsh.homesweethome.furniture.presenter.interfaces.IFragmentNavigationPresenter;

public class LoginPresenter implements ILoginPresenter, IFragmentNavigationPresenter {

    private ILoginView view;

    public LoginPresenter(ILoginView view) {
        this.view = view;
    }

    @Override
    public void loadInitialFragment() {
        view.setFragment(new MainFragment());
    }

    @Override
    public void changeFragment(BaseFragment fragment) {
        view.setFragment(fragment);
    }
}

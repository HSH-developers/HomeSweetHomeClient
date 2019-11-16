package com.example.hsh.homesweethome.auth.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.hsh.homesweethome.R;
import com.example.hsh.homesweethome.auth.login.presenter.LoginPresenter;
import com.example.hsh.homesweethome.auth.login.view.ILoginView;
import com.example.hsh.homesweethome.common.view.BaseFragment;

public class Login extends AppCompatActivity implements
        ILoginView, NavigationView.OnNavigationItemSelectedListener {

    private LoginPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        presenter = new LoginPresenter(this);
        presenter.loadInitialFragment();



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void setFragment(BaseFragment fragment) {
        fragment.attachPresenter(presenter);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.login_fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }
}

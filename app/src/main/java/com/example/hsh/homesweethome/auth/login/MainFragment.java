package com.example.hsh.homesweethome.auth.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hsh.homesweethome.R;
import com.example.hsh.homesweethome.auth.login.presenter.MainFragmentPresenter;
import com.example.hsh.homesweethome.auth.login.view.IMainFragmentView;
import com.example.hsh.homesweethome.common.view.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFragment extends BaseFragment implements IMainFragmentView {
    @BindView(R.id.input_email)
    private EditText emailText;
    @BindView(R.id.input_password)
    private EditText passwordText;
    @BindView(R.id.btn_login)
    private Button loginButton;
    @BindView(R.id.link_signup)
    private TextView signupLink;

    private MainFragmentPresenter presenter;

    public MainFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_main_fragment, container, false );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(view);
        presenter = new MainFragmentPresenter(this);

        loginButton.setOnClickListener(view1 -> {
            login();
        });

    }

    private void login() {

        if (!validate()) {
            onLoginFailed();
            return;
        }

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

    }


    private void onLoginFailed() {
        Toast.makeText(getContext(), "Login failed", Toast.LENGTH_LONG).show();
        loginButton.setEnabled(true);
    }

    private boolean validate() {
        boolean valid = true;

        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailText.setError("enter a valid email address");
            valid = false;
        } else {
            emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            passwordText.setError(null);
        }

        return valid;
    }
}

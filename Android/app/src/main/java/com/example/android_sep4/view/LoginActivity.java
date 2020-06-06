package com.example.android_sep4.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.android_sep4.R;
import com.example.android_sep4.viewmodel.LoginActivityViewModel;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private LoginActivityViewModel loginActivityViewModel;
    private ProgressDialog progressDialog;
    private Button loginButton;
    private EditText emailField;
    private EditText passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");

        setViewModel();

        loginButton = findViewById(R.id.btn_login);
        emailField = findViewById(R.id.input_email);
        passwordField = findViewById(R.id.input_password);

        loginButton.setOnClickListener(v -> login());
    }

    private void setViewModel() {
        loginActivityViewModel = new ViewModelProvider(this).get(LoginActivityViewModel.class);

        loginActivityViewModel.getIsValidating().observe(this, aBoolean -> {
            if (aBoolean) {
                progressDialog.show();
            } else {
                progressDialog.dismiss();
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        String email = emailField.getText().toString();
        String password = passwordField.getText().toString();
        int validation = loginActivityViewModel.validateFields(email, password);

        switch (validation) {
            case 1:
                verifyLogin(email, password);
                break;
            case 2:
                emailField.setError("Email format is not valid");
                break;
            case 3:
                passwordField.setError("Password needs to be between 6 and 16 characters");
                break;
        }
    }

    public void verifyLogin(String email, String password) {
        LiveData<Boolean> validLogin = loginActivityViewModel.validateLogin(email, password);
        validLogin.observe(this, valid -> {
            if (valid) {
                Intent mainActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainActivity);
                finish();
            } else {
                onLoginFailed("Email or password is not correct.");
            }
        });
    }

    public void onLoginFailed(String feedback) {
        Toast.makeText(getBaseContext(), feedback, Toast.LENGTH_LONG).show();
    }

}

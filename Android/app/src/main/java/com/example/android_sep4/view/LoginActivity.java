package com.example.android_sep4.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android_sep4.R;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private Button loginButton;
    private EditText usernameField;
    private EditText passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginButton = findViewById(R.id.btn_login);
        usernameField = findViewById(R.id.input_username);
        passwordField = findViewById(R.id.input_password);

        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();

        // TODO: Implement our authentication logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onLoginSuccess or onLoginFailed
                        onLoginSuccess();
                        // onLoginFailed();
                        progressDialog.dismiss();
                    }
                }, 1000);

        Intent mainActivity = new Intent(this, MainActivity.class);
        startActivity(mainActivity);
    }

    public void onLoginSuccess() {
        loginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();

        if (username.isEmpty()) {
            usernameField.setError("Enter a valid username");
            valid = false;
        } else {
            usernameField.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            passwordField.setError("The password should contain at least 4 characters");
            valid = false;
        } else {
            passwordField.setError(null);
        }

        return valid;
    }

}

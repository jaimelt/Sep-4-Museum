package com.example.android_sep4.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android_sep4.R;
import com.example.android_sep4.viewmodel.ManageAccountsViewModel;
import com.google.android.material.textfield.TextInputLayout;

public class ManageAccountsActivity extends AppCompatActivity {
    private ManageAccountsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_accounts);
        viewModel = new ViewModelProvider(this).get(ManageAccountsViewModel.class);

        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


    }

    public void onRegisterAccountClicked(View view) {
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_register_account, null);
        EditText emailField = dialogView.findViewById(R.id.emailField);
        EditText passwordField = dialogView.findViewById(R.id.passwordField);
        EditText repeatPasswordField = dialogView.findViewById(R.id.repeatPasswordField);
        Button createAccountBtn = dialogView.findViewById(R.id.createAccountButton);
        createDialog(dialogView, emailField, passwordField, repeatPasswordField, createAccountBtn);
    }

    private void createDialog(View dialogView, EditText emailField, EditText passwordField, EditText repeatPasswordField, Button createAccountBtn)
    {
        //Creating AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this, AlertDialog.THEME_DEVICE_DEFAULT_DARK);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();
        createAccountBtn.setOnClickListener(v -> {
            String email = emailField.getText().toString().trim();
            String password = passwordField.getText().toString().trim();
            String repeatPassword = repeatPasswordField.toString().trim();
            int validation = viewModel.registerAccount(email, password, repeatPassword);
            switch (validation) {
                case 1:
                    dialog.dismiss();
                    break;
                case 2:
                    emailField.setError("Email format is not valid");
                    break;
                case 3:
                    TextInputLayout passwordLayout = dialogView.findViewById(R.id.passwordLayout);
                    passwordLayout.setError("Password must be between 6 and 16 characters");
                    break;
                case 4:
                    TextInputLayout repeatPasswordLayout = dialogView.findViewById(R.id.repeatPasswordLayout);
                    repeatPasswordLayout.setError("Passwords do not match");
            }
        });
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}

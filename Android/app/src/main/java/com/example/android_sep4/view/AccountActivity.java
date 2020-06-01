package com.example.android_sep4.view;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.android_sep4.R;
import com.example.android_sep4.adapters.AccountAdapter;
import com.example.android_sep4.viewmodel.AccountViewModel;
import com.google.android.material.textfield.TextInputLayout;

public class AccountActivity extends AppCompatActivity implements AccountAdapter.OnListItemClickListener {
    private AccountViewModel viewModel;
    private AccountAdapter accountAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        viewModel = new ViewModelProvider(this).get(AccountViewModel.class);
        setToolbar();
        setRecyclerView();
        setProgressBar();
    }

    private void setProgressBar() {
        ProgressBar progressBar = findViewById(R.id.progress_bar_artworks);
        viewModel.getIsLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean) {
                    progressBar.setVisibility(View.VISIBLE);
                } else progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void setRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        accountAdapter = new AccountAdapter(this);
        recyclerView.setAdapter(accountAdapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        viewModel.getUsers().observe(this, users -> {
            accountAdapter.setUsers(users);
        });
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Manage user accounts");
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    public void onRegisterAccountClicked(View view) {
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_register_account, null);
        EditText emailField = dialogView.findViewById(R.id.emailField);
        EditText passwordField = dialogView.findViewById(R.id.passwordField);
        EditText repeatPasswordField = dialogView.findViewById(R.id.repeatPasswordField);
        Button createAccountBtn = dialogView.findViewById(R.id.createAccountButton);
        openCreateDialog(dialogView, emailField, passwordField, repeatPasswordField, createAccountBtn);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    private void openCreateDialog(View dialogView, EditText emailField, EditText passwordField, EditText repeatPasswordField, Button createAccountBtn) {
        //Creating AlertDialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this, android.R.style.Theme_DeviceDefault_Dialog_Alert);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();
        createAccountBtn.setOnClickListener(v -> {
            String email = emailField.getText().toString().trim();
            String password = passwordField.getText().toString().trim();
            String repeatPassword = repeatPasswordField.getText().toString().trim();
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
        if(dialog.getWindow() != null)
        {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        dialog.show();
    }

    @Override
    //finish on activity when up navigation is clicked - animation slide to right
    public boolean onSupportNavigateUp() {
        onBackPressed();
        finish();
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    @Override
    public void onListItemClick(int clickedItemIndex) {
        openDeleteDialog(clickedItemIndex);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    private void openDeleteDialog(int index)
    {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this, android.R.style.Theme_DeviceDefault_Dialog_Alert);
        alertDialog.setTitle("Exit");
        alertDialog.setMessage("Are you sure about deleting this user account?");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                viewModel.deleteUser(index);
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }
}

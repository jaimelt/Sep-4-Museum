package com.example.android_sep4.view.settings;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.android_sep4.R;
import com.example.android_sep4.viewmodel.SettingsViewModel;
import com.google.android.material.textfield.TextInputLayout;

public class UserFragment extends Fragment {
    private SettingsViewModel settingsViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        if(getActivity() != null)
        {
            getActivity().setTitle("User");
        }
        return inflater.inflate(R.layout.fragment_user_settings, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView email = view.findViewById(R.id.email_text);
        settingsViewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        email.setText(settingsViewModel.getEmail());
        LinearLayout linearLayout = view.findViewById(R.id.change_password);
        linearLayout.setOnClickListener(v -> showAlertDialog());
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_DeviceDefault_Dialog_Alert);
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_change_password, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        EditText passwordField = view.findViewById(R.id.password_field);
        EditText repeatPasswordField = view.findViewById(R.id.password_repeat_field);
        Button changePasswordBtn = view.findViewById(R.id.change_password_btn);
        changePasswordBtn.setOnClickListener(v -> {
            String password = passwordField.getText().toString().trim();
            String repeatPassword = repeatPasswordField.getText().toString().trim();
            int validation = settingsViewModel.changePassword(password, repeatPassword);
            switch (validation) {
                case 1:
                    dialog.dismiss();
                    break;
                case 2:
                    TextInputLayout passwordLayout = view.findViewById(R.id.password_layout);
                    passwordLayout.setError("Password must be between 6 and 16 characters");
                    break;
                case 3:
                    TextInputLayout repeatPasswordLayout = view.findViewById(R.id.repeat_password_layout);
                    repeatPasswordLayout.setError("Passwords do not match");
                    break;
            }
        });
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        dialog.show();
    }
}

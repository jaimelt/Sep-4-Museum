package com.example.android_sep4.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.android_sep4.repositories.AuthRepository;

public class ManageAccountsViewModel extends AndroidViewModel {
    private AuthRepository authRepository;

    public ManageAccountsViewModel(@NonNull Application application) {
        super(application);
        authRepository = AuthRepository.getInstance();
    }

    public String registerAccount(String email, String password, String repeatPassword) {
        String validation = validate(email, password, repeatPassword);
        if(validation.equals("valid"))
        {
            authRepository.registerUser(email, password);
            return validation;
        }
        else {
          return validation;
        }
    }

    private String validate(String email, String password, String repeatPassword) {
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return "invalid email";
        }
        else if (password.isEmpty() || password.length() < 6 || password.length() > 16) {
            return "Invalid password. Password must be between 6 and 16 characters.";
        }
        else if(!password.equals(repeatPassword))
        {
            return "Passwords do not match";
        }
        else {
            return "valid";
        }
    }
}

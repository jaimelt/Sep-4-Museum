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

    public int registerAccount(String email, String password, String repeatPassword) {
        int validation = validate(email, password, repeatPassword);
        if (validation == 1) {
            authRepository.registerUser(email, password);
        }
        return validation;
    }

    private int validate(String email, String password, String repeatPassword) {
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return 2;
        } else if (password.isEmpty() || password.length() < 6 || password.length() > 16) {
            return 3;
        } else if (!password.equals(repeatPassword)) {
            return 4;
        } else {
            return 1;
        }
    }
}
package com.example.android_sep4.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.android_sep4.repositories.AuthRepository;

public class SettingsViewModel extends AndroidViewModel {
    private AuthRepository authRepository;

    public SettingsViewModel(@NonNull Application application) {
        super(application);
        authRepository = AuthRepository.getInstance(application);
    }

    public String getEmail() {
        return authRepository.getEmail();
    }

    public int changePassword(String password, String repeatPassword) {
        int validation = validate(password, repeatPassword);
        if (validation == 1) {
            authRepository.changePassword(password);
        }
        return validation;
    }

    private int validate(String password, String repeatPassword)
    {
        if (password.isEmpty() || password.length() < 6 || password.length() > 16) {
            return 2;
        } else if (!password.equals(repeatPassword)) {
            return 3;
        } else {
            return 1;
        }
    }
}

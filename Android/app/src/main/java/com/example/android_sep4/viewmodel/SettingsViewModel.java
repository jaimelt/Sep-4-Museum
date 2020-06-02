package com.example.android_sep4.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.android_sep4.model.Validator;
import com.example.android_sep4.repositories.AuthRepository;

public class SettingsViewModel extends AndroidViewModel {
    private AuthRepository authRepository;
    private Validator validator;

    public SettingsViewModel(@NonNull Application application) {
        super(application);
        authRepository = AuthRepository.getInstance(application);
        this.validator = new Validator();
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

    private int validate(String password, String repeatPassword) {
        return validator.validateChangePassword(password, repeatPassword);
    }
}

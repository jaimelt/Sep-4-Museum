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
}

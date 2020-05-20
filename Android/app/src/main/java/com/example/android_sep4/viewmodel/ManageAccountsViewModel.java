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

    public void registerAccount(String email, String password)
    {
        authRepository.registerUser(email, password);
    }
}

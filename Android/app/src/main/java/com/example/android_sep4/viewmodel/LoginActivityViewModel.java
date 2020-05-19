package com.example.android_sep4.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android_sep4.repositories.AuthRepository;

public class LoginActivityViewModel extends AndroidViewModel {
    AuthRepository authRepository;

    public LoginActivityViewModel(Application application)  {
        super(application);
        authRepository = AuthRepository.getInstance();
    }

    public boolean validateLogin(String email, String password) {
        return authRepository.validateLogin(email, password);
    }

    public LiveData<Boolean> getIsValidating() {
        return authRepository.getIsValidating();
    }

}

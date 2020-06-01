package com.example.android_sep4.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android_sep4.repositories.AuthRepository;

public class LoginActivityViewModel extends AndroidViewModel {
    AuthRepository authRepository;

    public LoginActivityViewModel(Application application)  {
        super(application);
        authRepository = AuthRepository.getInstance(application);
    }

    public LiveData<Boolean> validateLogin(String email, String password) {
        return authRepository.validateLogin(email, password);
    }

    public LiveData<Boolean> getIsValidating() {
        return authRepository.getIsValidating();
    }

    public int validateFields(String email, String password)
    {
        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return 2;
        }
        else if (password.isEmpty() || password.length() < 6 || password.length() > 16) {
            return 3;
        }
        else {
            return 1;
        }
    }

}

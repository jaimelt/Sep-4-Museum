package com.example.android_sep4.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android_sep4.model.Validator;
import com.example.android_sep4.repositories.AuthRepository;

public class LoginActivityViewModel extends AndroidViewModel {
    private AuthRepository authRepository;
    private Validator validator;
    public LoginActivityViewModel(Application application)  {
        super(application);
        authRepository = AuthRepository.getInstance(application);
        this.validator = new Validator();
    }

    public LiveData<Boolean> validateLogin(String email, String password) {
        return authRepository.validateLogin(email, password);
    }

    public LiveData<Boolean> getIsValidating() {
        return authRepository.getIsValidating();
    }

    public int validateFields(String email, String password)
    {
       return validator.validateLogin(email,password);
    }

}

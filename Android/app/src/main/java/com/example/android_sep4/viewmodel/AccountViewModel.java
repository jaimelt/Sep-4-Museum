package com.example.android_sep4.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android_sep4.model.User;
import com.example.android_sep4.model.Validator;
import com.example.android_sep4.repositories.AuthRepository;

import java.util.ArrayList;

public class AccountViewModel extends AndroidViewModel {
    private AuthRepository authRepository;
    private Validator validator;

    public AccountViewModel(@NonNull Application application) {
        super(application);
        authRepository = AuthRepository.getInstance(application);
        validator = new Validator();
    }

    public int registerAccount(String email, String password, String repeatPassword) {
        int validation = validator.validateRegister(email, password, repeatPassword);
        if (validation == 1) {
            authRepository.registerUser(email, password);
        }
        return validation;
    }

    public LiveData<ArrayList<User>> getUsers() {
        return authRepository.getUsers();
    }

    public LiveData<Boolean> getIsLoading() {
        return authRepository.getIsLoading();
    }

    public void deleteUser(int index) {
        authRepository.deleteUser(index);
    }
}

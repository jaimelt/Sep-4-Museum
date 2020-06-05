package com.example.android_sep4.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.android_sep4.model.User;
import com.example.android_sep4.requests.clients.AuthAPIClient;

import java.util.ArrayList;

public class AuthRepository {
    private static AuthRepository instance;
    private AuthAPIClient authAPIClient;
    private Application application;
    private String email;

    public AuthRepository(Application application)
    {
        this.application = application;
        authAPIClient = new AuthAPIClient(application);
    }

    public static AuthRepository getInstance(Application application) {
        if (instance == null) {
            instance = new AuthRepository(application);
        }
        return instance;
    }

    public LiveData<Boolean> validateLogin(String email, String password) {
        this.email = email;
        authAPIClient.validateLogin(email, password);
        return authAPIClient.getValidLogin();
    }

    public void registerUser(String email, String password) {
        authAPIClient.registerUser(email, password);
    }

    public void deleteUser(int index) {
        authAPIClient.deleteUserByIndex(index);
    }

    public void changePassword(String password) {
        User user = new User(email, password);
        authAPIClient.changePassword(user);
    }

    public LiveData<Boolean> getIsValidating() {
        return authAPIClient.getIsValidating();
    }

    public LiveData<ArrayList<User>> getUsers() {
        return authAPIClient.getUsersLive();
    }

    public LiveData<Boolean> getIsLoading() {
        return authAPIClient.getIsLoading();
    }

    public String getEmail() {
        return email;
    }
}

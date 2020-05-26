package com.example.android_sep4.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.android_sep4.model.User;
import com.example.android_sep4.requests.AuthEndpoints;
import com.example.android_sep4.requests.ServiceGenerator;
import com.example.android_sep4.requests.clients.AuthAPIClient;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {
    private static AuthRepository instance;
    private MutableLiveData<Boolean> isValidating = new MutableLiveData<>();
    private AuthAPIClient authAPIClient =  new AuthAPIClient();
    private Boolean valid = false;

    public static AuthRepository getInstance() {
        if (instance == null) {
            instance = new AuthRepository();
        }
        return instance;
    }

    public boolean validateLogin(String email, String password) {
        return authAPIClient.validateLogin(email, password);
    }

    public void registerUser(String email, String password) {
        authAPIClient.registerUser(email, password);
    }

    public void deleteUser(User user) {
        authAPIClient.deleteUser(user);
    }

    public void updateUser(User updatedUser) {
        authAPIClient.updateUser(updatedUser);
    }

    public MutableLiveData<Boolean> getIsValidating() {
        return isValidating;
    }
}

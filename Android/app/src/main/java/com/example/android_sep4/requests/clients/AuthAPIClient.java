package com.example.android_sep4.requests.clients;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.model.User;
import com.example.android_sep4.model.Users;
import com.example.android_sep4.requests.AuthEndpoints;
import com.example.android_sep4.requests.ServiceGenerator;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthAPIClient {
    private MutableLiveData<Boolean> isValidating = new MutableLiveData<>();
    private MutableLiveData<ArrayList<User>> usersData = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private MutableLiveData<Boolean> validResponse = new MutableLiveData<>();
    private ArrayList<User> usersDataSet = new ArrayList<>();
    private Boolean valid = false;
    private User user = new User();
    private Application application;

    public AuthAPIClient(Application application){
        this.application = application;
    }

    private static String convertToHex(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (byte b : data) {
            int halfbyte = (b >>> 4) & 0x0F;
            int two_halves = 0;
            do {
                buf.append(halfbyte <= 9 ? (char) ('0' + halfbyte) : (char) ('a' + halfbyte - 10));
                halfbyte = b & 0x0F;
            } while (two_halves++ < 1);
        }
        return buf.toString();
    }

    private static String hashSHA1(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA-1");
        byte[] textBytes = text.getBytes(StandardCharsets.ISO_8859_1);
        md.update(textBytes, 0, textBytes.length);
        byte[] sha1hash = md.digest();
        return convertToHex(sha1hash);
    }

    public boolean validateLogin(String email, String password) {
        isValidating.setValue(true);

        User user = new User(email, password);

        AuthEndpoints endpoints = ServiceGenerator.getAuthEndpoints();

        Call<Boolean> call = endpoints.validateLogin(user);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(Call<Boolean> call, Response<Boolean> response) {
                valid = response.body();
                isValidating.setValue(false);
            }

            @Override
            public void onFailure(Call<Boolean> call, Throwable t) {

            }
        });
        return valid;
    }

    public void registerUser(String email, String password) {
        User user = new User(email, password);

        AuthEndpoints endpoints = ServiceGenerator.getAuthEndpoints();

        Call<User> call = endpoints.registerUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                validResponse.setValue(true);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                validResponse.setValue(false);
            }
        });
    }

    public void deleteUser(User user) {
        AuthEndpoints endpoints = ServiceGenerator.getAuthEndpoints();

        Call<User> call = endpoints.deleteUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                System.out.println("User deleted");
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println("User was not deleted");
            }
        });
    }

    public void updateUser(User updatedUser) {
        AuthEndpoints endpoints = ServiceGenerator.getAuthEndpoints();

        User user = new User(updatedUser.getEmail(), updatedUser.getPassword());

        Call<User> call = endpoints.updateUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    public void getUsers() {
        AuthEndpoints endpoints = ServiceGenerator.getAuthEndpoints();

        Call<Users> call = endpoints.getUsers();
        isLoading.setValue(true);
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(Call<Users> call, Response<Users> response) {
                if(response.isSuccessful() && response.body() != null)
                {
                    Toast.makeText(application, "IT WORKS", Toast.LENGTH_LONG).show();
                    usersData.setValue(response.body().getUsers());
                    isLoading.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<Users> call, Throwable t) {

            }
        });
    }

    public LiveData<ArrayList<User>> getUsersLive() {
        getUsers();
        return usersData;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<Boolean> getValidResponse() {
        return validResponse;
    }
}

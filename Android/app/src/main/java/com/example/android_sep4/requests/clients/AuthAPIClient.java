package com.example.android_sep4.requests.clients;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.android_sep4.model.User;
import com.example.android_sep4.model.Users;
import com.example.android_sep4.requests.AuthEndpoints;
import com.example.android_sep4.requests.ServiceGenerator;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthAPIClient {
    private MutableLiveData<Boolean> isValidating = new MutableLiveData<>();
    private MutableLiveData<ArrayList<User>> usersData = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private MutableLiveData<Boolean> validLogin = new MutableLiveData<>();
    private Application application;

    public AuthAPIClient(Application application) {
        this.application = application;
    }

    public void validateLogin(String email, String password) {
        isValidating.setValue(true);

        User user = new User(email, password);

        AuthEndpoints endpoints = ServiceGenerator.getAuthEndpoints();

        Call<Boolean> call = endpoints.validateLogin(user);
        call.enqueue(new Callback<Boolean>() {
            @Override
            public void onResponse(@NonNull Call<Boolean> call, @NonNull Response<Boolean> response) {
                validLogin.setValue(response.body());
                isValidating.setValue(false);
            }

            @Override
            public void onFailure(@NonNull Call<Boolean> call, @NonNull Throwable t) {

            }
        });
    }

    public LiveData<Boolean> getValidLogin() {
        return validLogin;
    }

    public void registerUser(String email, String password) {
        User user = new User(email, password);

        AuthEndpoints endpoints = ServiceGenerator.getAuthEndpoints();

        Call<User> call = endpoints.registerUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                getUsers();
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                getUsers();
            }
        });
    }

    public void deleteUser(String email) {
        AuthEndpoints endpoints = ServiceGenerator.getAuthEndpoints();

        Call<User> call = endpoints.deleteUser(email);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                getUsers();
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                getUsers();
            }
        });
    }

    public void changePassword(User user) {
        AuthEndpoints endpoints = ServiceGenerator.getAuthEndpoints();

//        User user = new User(updatedUser.getEmail(), updatedUser.getPassword());

        Call<User> call = endpoints.updateUser(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(@NonNull Call<User> call, @NonNull Response<User> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Toast.makeText(application, "Password changed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<User> call, @NonNull Throwable t) {
                Toast.makeText(application, "Password changed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void getUsers() {
        AuthEndpoints endpoints = ServiceGenerator.getAuthEndpoints();

        Call<Users> call = endpoints.getUsers();
        isLoading.setValue(true);
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(@NonNull Call<Users> call, @NonNull Response<Users> response) {
                if (response.isSuccessful() && response.body() != null) {
                    usersData.setValue(response.body().getUsers());
                }
                isLoading.setValue(false);
            }

            @Override
            public void onFailure(@NonNull Call<Users> call, @NonNull Throwable t) {
                usersData.setValue(new ArrayList<>());
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

    public void deleteUserByIndex(int index) {
        User user = new User();
        if (usersData.getValue() != null) {
            user = usersData.getValue().get(index);
        }
        deleteUser(user.getEmail());
    }

    public LiveData<Boolean> getIsValidating() {
        return isValidating;
    }
}

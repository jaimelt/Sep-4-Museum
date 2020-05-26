package com.example.android_sep4.requests;

import androidx.room.Update;

import com.example.android_sep4.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface AuthEndpoints {

    @GET("/login")
    Call<Boolean> validateLogin(@Body User user);

    @POST("/account")
    Call<User> registerUser(@Body User user);

    @PUT("/account")
    Call<User> updateUser(@Body User user);

    @DELETE("/account")
    Call<User> deleteUser(@Body User user);

}

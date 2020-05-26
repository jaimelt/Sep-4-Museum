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

    @POST("/")
    Call<User> registerUser(@Body User user);

    @PUT("/")
    Call<User> update(@Body User user);

    @DELETE("/")
    Call<User> deleteUser(@Body User user);

}

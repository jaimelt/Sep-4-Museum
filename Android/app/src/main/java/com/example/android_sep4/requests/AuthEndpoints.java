package com.example.android_sep4.requests;

import androidx.room.Update;

import com.example.android_sep4.model.User;
import com.example.android_sep4.model.Users;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AuthEndpoints {
    @GET("/account/users")
    Call<Users> getUsers();

    @POST("account/login")
    Call<Boolean> validateLogin(@Body User user);

    @POST("/account")
    Call<User> registerUser(@Body User user);

    @PUT("/account")
    Call<User> updateUser(@Body User user);

    @DELETE("/account/{email}")
    Call<User> deleteUser(@Path("email") String email);



}

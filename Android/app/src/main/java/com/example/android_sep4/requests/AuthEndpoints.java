package com.example.android_sep4.requests;

import com.example.android_sep4.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AuthEndpoints {

    @GET("/login")
    Call<Boolean> validateLogin(@Body User user);

    @POST
    Call<User> registerUser(@Body User user);

}

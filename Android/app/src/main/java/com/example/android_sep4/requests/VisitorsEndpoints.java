package com.example.android_sep4.requests;

import com.example.android_sep4.model.Visitors;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface VisitorsEndpoints {

    @POST("/")
    Call<Visitors> sendVisitors(@Body Visitors visitors);
}

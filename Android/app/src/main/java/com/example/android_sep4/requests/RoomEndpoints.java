package com.example.android_sep4.requests;

import com.example.android_sep4.model.Room;
import com.example.android_sep4.model.RoomMeasurements;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RoomEndpoints {

    @GET("/")
    Call<ArrayList<Room>> getRoomsDetails();

    @GET("/")
    Call<RoomMeasurements> getMeasurements();
}

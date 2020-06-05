package com.example.android_sep4.requests;

import com.example.android_sep4.model.Artworks;
import com.example.android_sep4.model.Room;
import com.example.android_sep4.model.RoomMeasurements;
import com.example.android_sep4.model.Rooms;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface RoomEndpoints {

    @GET("/rooms/getall")
    Call<Rooms> getRoomsDetails();

    @PUT("rooms/put/{id}")
    Call<Room> editOptimalConditions(@Path("id") String id, @Body Room room);

/*    @GET("/measurements/{roomCode}")
    Call<RoomMeasurements> getLiveRoomMeasurements(@Path("roomCode") String roomCode);*/
}

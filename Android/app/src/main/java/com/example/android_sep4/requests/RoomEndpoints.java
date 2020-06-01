package com.example.android_sep4.requests;

import com.example.android_sep4.model.Artworks;
import com.example.android_sep4.model.Room;
import com.example.android_sep4.model.RoomMeasurements;
import com.example.android_sep4.model.Rooms;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RoomEndpoints {

    @GET("/rooms/getall")
    Call<Rooms> getRoomsDetails();

    @GET("/{locationCode}")
    Call<Room> getRoomByLocation(@Path("locationCode") String locationCode);

    @GET("get/{id}")
    Call<Room> getRoomById(@Path("id") String id);

    @GET("/artworks?roomCode={roomCode}")
    Call<Artworks> getArtworksByRoomId(@Path("roomCode") String roomCode);

}

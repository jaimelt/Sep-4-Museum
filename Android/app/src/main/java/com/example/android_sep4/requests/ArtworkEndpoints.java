package com.example.android_sep4.requests;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.model.Artworks;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ArtworkEndpoints {
    @GET("/artworks/getall")
    Call<Artworks> getArtworks();

    @GET("/artworks?id={id}")
    Call<Artwork> getArtworkById(@Path("id") int id);

    @POST("/artworks")
    Call<Artwork> addArtwork(@Body Artwork artwork);

    @PUT("/artworks?id={id}")
    Call<Artwork> editArtwork(@Path("id") int id, @Body Artwork body);

    @DELETE("/artworks?id={id}")
    Call<Artwork> deleteArtwork(@Path("id") int id);

    @GET("/artworks?roomCode={roomCode}")
    Call<ArrayList<Artwork>> getArtworksByRoomId(@Path("roomCode") String roomCode);

}

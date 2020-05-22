package com.example.android_sep4.requests;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.model.ArtworkResponse;
import com.example.android_sep4.model.Artworks;

import java.util.ArrayList;

import okhttp3.ResponseBody;
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

    @GET("/artworks/getone/{id}")
    Call<Artwork> getArtworkById(@Path("id") int id);

    //not working
    @POST("/artworks/createartwork")
    Call<ArtworkResponse> addArtwork(@Body ArtworkResponse artwork);

    //not working
    @PUT("/artworks/edit/{id}")
    Call<Artwork> editArtwork(@Path("id") int id, @Body Artwork body);

    @DELETE("/artworks/delete/{id}")
    Call<Artwork> deleteArtwork(@Path("id") int id);

    @GET("/artworks?roomCode={roomCode}")
    Call<Artworks> getArtworksByRoomId(@Path("roomCode") String roomCode);

}

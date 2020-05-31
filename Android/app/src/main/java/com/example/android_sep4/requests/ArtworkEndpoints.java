package com.example.android_sep4.requests;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.model.Artworks;

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
    Call<Artwork> addArtwork(@Body Artwork artwork);

    //not working
    @PUT("/artworks/edit/{id}")
    Call<Artwork> editArtwork(@Path("id") int id, @Body Artwork body);

    @DELETE("/artworks/delete/{id}")
    Call<Artwork> deleteArtwork(@Path("id") int id);

    @GET("/artworks/getallbyroom/{roomCode}")
    Call<Artworks> getArtworksByRoomId(@Path("roomCode") String roomCode);

    @PUT("/moveartwork/{id}")
    Call<String> moveArtwork(@Path("id") int id, @Body String locationCode);

}

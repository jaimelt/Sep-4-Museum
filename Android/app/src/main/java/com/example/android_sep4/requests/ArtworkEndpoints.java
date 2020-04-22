package com.example.android_sep4.requests;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.model.ArtworkMeasurements;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ArtworkEndpoints {
    @GET("/artworks")
    Call<ArrayList<Artwork>> getArtworks();

    @GET("/artworks?id={id}")
    Call<Artwork> getArtwork(@Path("id") int id);

    @POST("/artworks")
    Call<Artwork> addArtwork();

    @PUT("/artworks?id={id}")
    Call<Artwork> editArtwork(@Path("id") int id);

    @DELETE("/artworks?id={id}")
    Call<Artwork> deleteArtwork(@Path("id") int id);
}

package com.example.android_sep4.requests;

import com.example.android_sep4.model.Artworks;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NotificationsEndpoints {
    @GET("/measurements")
    Call<Artworks> getArtworksInDanger();
}

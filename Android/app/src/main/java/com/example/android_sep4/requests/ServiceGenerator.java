package com.example.android_sep4.requests;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .baseUrl("APi URL")
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = retrofitBuilder.build();

    private static ArtworkEndpoints artworkEndpoints = retrofit.create(ArtworkEndpoints.class);

    public static ArtworkEndpoints getArtworkEndpoints() {
        return artworkEndpoints;
    }
}

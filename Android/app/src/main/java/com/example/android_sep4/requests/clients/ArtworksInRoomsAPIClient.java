package com.example.android_sep4.requests.clients;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.model.Artworks;
import com.example.android_sep4.requests.ArtworkEndpoints;
import com.example.android_sep4.requests.ServiceGenerator;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class ArtworksInRoomsAPIClient {
    private MutableLiveData<ArrayList<Artwork>> a1Artworks = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Artwork>> a2Artworks = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Artwork>> a3Artworks = new MutableLiveData<>();
    private Application application;

    public ArtworksInRoomsAPIClient(Application application) {
        this.application = application;
    }

    public LiveData<ArrayList<Artwork>> getArtworksFromA1(String roomCode) {
        ArtworkEndpoints endpoints = ServiceGenerator.getArtworkEndpoints();

        Call<Artworks> call = endpoints.getArtworksByRoomId(roomCode);

        call.enqueue(new Callback<Artworks>() {
            @Override
            public void onResponse(Call<Artworks> call, Response<Artworks> response) {
                Log.i(TAG, "onResponse: artworks in room");
                if (response.body() != null && response.isSuccessful()) {
                    a1Artworks.setValue(response.body().getArtworks());
                    a1Artworks = new MutableLiveData<>();
                }
            }

            @Override
            public void onFailure(Call<Artworks> call, Throwable t) {
                //HERE YOU ARE CALLING THE ROOM DATABASE AND SETTING artworksDataSet TO THE ARTWORKS FROM ROOM BY ROOM CODE
                ArrayList<Artwork> artworkArrayList = new ArrayList<>();
                a1Artworks.setValue(artworkArrayList);
            }
        });
        return a1Artworks;
    }

    public LiveData<ArrayList<Artwork>> getArtworksFromA2(String roomCode) {
        ArtworkEndpoints endpoints = ServiceGenerator.getArtworkEndpoints();

        Call<Artworks> call = endpoints.getArtworksByRoomId(roomCode);

        call.enqueue(new Callback<Artworks>() {
            @Override
            public void onResponse(Call<Artworks> call, Response<Artworks> response) {
                Log.i(TAG, "onResponse: artworks in room");
                if (response.body() != null && response.isSuccessful()) {
                    a2Artworks.setValue(response.body().getArtworks());
                    a2Artworks = new MutableLiveData<>();
                }
            }

            @Override
            public void onFailure(Call<Artworks> call, Throwable t) {
                //HERE YOU ARE CALLING THE ROOM DATABASE AND SETTING artworksDataSet TO THE ARTWORKS FROM ROOM BY ROOM CODE
                ArrayList<Artwork> artworkArrayList = new ArrayList<>();
                a2Artworks.setValue(artworkArrayList);
            }
        });
        return a2Artworks;
    }

    public LiveData<ArrayList<Artwork>> getArtworksFromA3(String roomCode) {
        ArtworkEndpoints endpoints = ServiceGenerator.getArtworkEndpoints();

        Call<Artworks> call = endpoints.getArtworksByRoomId(roomCode);

        call.enqueue(new Callback<Artworks>() {
            @Override
            public void onResponse(Call<Artworks> call, Response<Artworks> response) {
                Log.i(TAG, "onResponse: artworks in room");
                if (response.body() != null && response.isSuccessful()) {
                    a3Artworks.setValue(response.body().getArtworks());
                    a3Artworks = new MutableLiveData<>();
                }
            }

            @Override
            public void onFailure(Call<Artworks> call, Throwable t) {
                //HERE YOU ARE CALLING THE ROOM DATABASE AND SETTING artworksDataSet TO THE ARTWORKS FROM ROOM BY ROOM CODE
                ArrayList<Artwork> artworkArrayList = new ArrayList<>();
                a3Artworks.setValue(artworkArrayList);
            }
        });
        return a3Artworks;
    }

}

package com.example.android_sep4.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.requests.clients.ArtworksInRoomsAPIClient;

import java.util.ArrayList;

public class ArtworksFromRoomsRepository {
    private static ArtworksFromRoomsRepository instance;
    private ArtworksInRoomsAPIClient artworksInRoomsAPIClient;
    private Application application;

    public ArtworksFromRoomsRepository(Application application) {
        this.application = application;
        artworksInRoomsAPIClient = new ArtworksInRoomsAPIClient(application);
    }

    public static ArtworksFromRoomsRepository getInstance(Application application) {
        if (instance == null) {
            instance = new ArtworksFromRoomsRepository(application);
        }
        return instance;
    }

    public LiveData<ArrayList<Artwork>> getArtworkFromA1(String id) {
        return artworksInRoomsAPIClient.getArtworksFromA1(id);
    }

    public LiveData<ArrayList<Artwork>> getArtworkFromA2(String id) {
        return artworksInRoomsAPIClient.getArtworksFromA2(id);
    }

    public LiveData<ArrayList<Artwork>> getArtworkFromA3(String id) {
        return artworksInRoomsAPIClient.getArtworksFromA3(id);
    }


}

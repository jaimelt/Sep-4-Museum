package com.example.android_sep4.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.requests.clients.ArtworksAPIClient;

import java.util.ArrayList;

public class ArtworksRepository {
    private static ArtworksRepository instance;
    private ArtworksAPIClient artworksAPIClient;
    private Application application;

    public ArtworksRepository(Application application) {
        artworksAPIClient = new ArtworksAPIClient(application);
        this.application = application;

    }

    public static synchronized ArtworksRepository getInstance(Application application) {
        if (instance == null) {
            instance = new ArtworksRepository(application);
        }
        return instance;
    }

    public LiveData<ArrayList<Artwork>> getArtworksData() {
        artworksAPIClient.getArtworksData();
        return artworksAPIClient.getArtworksDataLive();
    }

    public LiveData<Artwork> getArtworkById(int id) {
        return artworksAPIClient.getArtworkById(id);
    }

    public void editArtwork(Artwork editedArtwork) {
        artworksAPIClient.editArtwork(editedArtwork);
    }

    public void addNewArtwork(Artwork artwork) {
        artworksAPIClient.addNewArtwork(artwork);
    }

    public void deleteArtwork(int id) {
        artworksAPIClient.deleteArtwork(id);
    }


    public LiveData<Boolean> getIsLoading() {
        return artworksAPIClient.getIsLoading();
    }

    public LiveData<ArrayList<Artwork>> getArtworksByRoomId(String roomCode) {
        return artworksAPIClient.getArtworksByRoomId(roomCode);
    }

    public LiveData<ArrayList<Artwork>> getArtworksFromStorage(String roomCode) {
        return artworksAPIClient.getArtworksFromStorage(roomCode);
    }

    public void moveArtwork(int artworkID, String location) {
        artworksAPIClient.moveArtwork(artworkID, location);
    }
}

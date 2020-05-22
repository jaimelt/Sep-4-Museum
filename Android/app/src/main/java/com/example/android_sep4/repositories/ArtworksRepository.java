package com.example.android_sep4.repositories;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.android_sep4.database.ArtworkDao;
import com.example.android_sep4.database.ArtworkWithMeasurements;
import com.example.android_sep4.database.MuseumDb;
import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.requests.clients.ArtworksAPIClient;

import java.util.ArrayList;
import java.util.List;

public class ArtworksRepository {
    private static ArtworksRepository instance;
    private ArtworksAPIClient artworksAPIClient;
    private MutableLiveData<ArrayList<Artwork>> artworksData = new MutableLiveData<>();
    private MutableLiveData<Artwork> artworkData = new MutableLiveData<>();
    private ArrayList<Artwork> artworksDataSet = new ArrayList<>();
    private ArtworkDao artworkDao;
    private Artwork artwork = new Artwork();
    //TODO: needs to be updated, rn it is for testing
    private LiveData<List<ArtworkWithMeasurements>> artworks;

    //ArtworksRepository should not be singleton
    public ArtworksRepository(Application application) {
        MuseumDb database = MuseumDb.getInstance(application);
        artworkDao = database.artworkDao();
        artworks = artworkDao.getAllLiveArtworks();
    }

    public static synchronized ArtworksRepository getInstance(Application application) {
        if (instance == null) {
            instance = new ArtworksRepository(application);
        }
        return instance;
    }

    //This is the method where we are retrieving the artworks data from the webservice
    @RequiresApi(api = Build.VERSION_CODES.O)
    public LiveData<ArrayList<Artwork>> getArtworksData() {
        return artworksAPIClient.getArtworksData();
    }

    public LiveData<ArrayList<Artwork>> getArtworksByRoomId(String roomCode) {
        return artworksAPIClient.getArtworksByRoomId(roomCode);
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

    public void positionToId(int position) {
        int id = 0;
        for (Artwork artwork : artworksDataSet) {
            id = artworksDataSet.get(position).getId();
        }
        deleteArtwork(id);
    }

    public Artwork getArtwork(int position) {
        return artworksDataSet.get(position);
    }

    public void addArtwork(int position, Artwork artwork) {
        artworksDataSet.add(position, artwork);
    }

    public void addArtwork(Artwork artwork) {
        artworksDataSet.add(artwork);
    }

    public void editArtwork(Artwork artwork, int position) {
        artworksDataSet.remove(position);
        artworksDataSet.add(position, artwork);
    }

    //From Room Database
    public List<Artwork> getArtworks() {
        List<ArtworkWithMeasurements> artworkWithMeasurements = artworkDao.getAllArtworks();
        List<Artwork> artworks = new ArrayList<>();
        for (ArtworkWithMeasurements artwork : artworkWithMeasurements) {
            artwork.artwork.setArtworkMeasurements(artwork.roomMeasurements);
            artworks.add(artwork.artwork);
        }
        return artworks;
    }
}

package com.example.android_sep4.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.requests.clients.ArtworksAPIClient;

import java.util.ArrayList;

public class ArtworksRepository {
    private static ArtworksRepository instance;
    private ArtworksAPIClient artworksAPIClient;
    private MutableLiveData<ArrayList<Artwork>> artworksData = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Artwork>> artworksInRoomData = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Artwork>> artworksInStorage = new MutableLiveData<>();
    private ArrayList<Artwork> artworksDataSet = new ArrayList<>();
    //    private ArtworkDao artworkDao;
    private Artwork artwork = new Artwork();
    //TODO: needs to be updated, rn it is for testing
//    private LiveData<List<ArtworkWithMeasurements>> artworks;
    private Application application;

    //ArtworksRepository should not be singleton
    public ArtworksRepository(Application application) {
//        MuseumDb database = MuseumDb.getInstance(application);
//        artworkDao = database.artworkDao();
//        artworks = artworkDao.getAllLiveArtworks();
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
        artworksAPIClient.getArtworksDataLive().observeForever(artworks -> {
            if (artworks.isEmpty()) {
                //ROOM DATABASE
            } else {
                artworksData.setValue(artworks);
            }
        });
        return artworksData;
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

    public LiveData<Boolean> getIsLoading() {
        return artworksAPIClient.getIsLoading();
    }

    public LiveData<ArrayList<Artwork>> getArtworksByRoomId(String roomCode) {
        artworksAPIClient.getArtworksByRoomId(roomCode).observeForever(new Observer<ArrayList<Artwork>>() {
            @Override
            public void onChanged(ArrayList<Artwork> artworksArrayList) {
                if (artworksArrayList.isEmpty()) {
                    artworksArrayList = new ArrayList<>();
                    artworksInRoomData.setValue(artworksArrayList);
                    //ROOM DATABASE
                } else {
                    artworksInRoomData.setValue(artworksArrayList);
                    artworksInRoomData = new MutableLiveData<>();
                }
            }
        });
        return artworksInRoomData;
    }

    public LiveData<ArrayList<Artwork>> getArtworksFromStorage(String roomCode) {
        artworksAPIClient.getArtworksFromStorage(roomCode).observeForever(new Observer<ArrayList<Artwork>>() {
            @Override
            public void onChanged(ArrayList<Artwork> artworks) {
                if (artworks.isEmpty()) {
                    //ROOM DATABASE
                } else {
                    artworksInStorage.setValue(artworks);
                }
            }
        });
        return artworksInStorage;
    }

    public void moveArtwork(int artworkID, String location) {
        artworksAPIClient.moveArtwork(artworkID, location);
    }


    //From Room Database
//    public List<Artwork> getArtworks() {
//        List<ArtworkWithMeasurements> artworkWithMeasurements = artworkDao.getAllArtworks();
//        List<Artwork> artworks = new ArrayList<>();
//        for (ArtworkWithMeasurements artwork : artworkWithMeasurements) {
//            artwork.artwork.setArtworkMeasurements(artwork.roomMeasurements);
//            artworks.add(artwork.artwork);
//        }
//        return artworks;
//    }
}

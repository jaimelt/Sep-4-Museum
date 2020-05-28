package com.example.android_sep4.repositories;

import android.app.Application;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.android_sep4.database.ArtworkDao;
import com.example.android_sep4.database.ArtworkWithMeasurements;
import com.example.android_sep4.database.MuseumDb;
import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.requests.clients.ArtworksAPIClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class ArtworksRepository {
    private static ArtworksRepository instance;
    private ArtworksAPIClient artworksAPIClient;
    private MutableLiveData<ArrayList<Artwork>> artworksData = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Artwork>> artworksInRoomData = new MutableLiveData<>();
    private ArrayList<Artwork> artworksDataSet = new ArrayList<>();
    private ArtworkDao artworkDao;
    private Artwork artwork = new Artwork();
    //TODO: needs to be updated, rn it is for testing
    private LiveData<List<ArtworkWithMeasurements>> artworks;
    private Application application;

    //ArtworksRepository should not be singleton
    public ArtworksRepository(Application application) {
        MuseumDb database = MuseumDb.getInstance(application);
        artworkDao = database.artworkDao();
        artworks = artworkDao.getAllLiveArtworks();
        artworksAPIClient = new ArtworksAPIClient(application);
        this.application = application;

    }

    public static synchronized ArtworksRepository getInstance(Application application) {
        if (instance == null) {
            instance = new ArtworksRepository(application);
        }
        return instance;
    }

    //This is the method where we are retrieving the artworks data from the webservice
    public LiveData<ArrayList<Artwork>> getArtworksData() {
        artworksAPIClient.getArtworksData().observeForever(new Observer<ArrayList<Artwork>>() {
            @Override
            public void onChanged(ArrayList<Artwork> artworks) {
                if (artworks.isEmpty()) {
                    //ROOM DATABASE
                } else {
                    artworksData.setValue(artworks);
                }
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
            public void onChanged(ArrayList<Artwork> artworks) {
                if (artworks.isEmpty()) {
                    //ROOM DATABASE
                } else {
                    artworksInRoomData.setValue(artworks);
                }
            }
        });
        return artworksInRoomData;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String encoder(String imagePath) {
        String base64Image = "";
        File file = new File(imagePath);
        try (FileInputStream imageInFile = new FileInputStream(file)) {
            byte[] imageData = new byte[(int) file.length()];
            imageInFile.read(imageData);
            base64Image = Base64.getEncoder().encodeToString(imageData);
        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }
        return base64Image;
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

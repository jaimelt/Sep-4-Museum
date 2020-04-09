package com.example.android_sep4.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.android_sep4.model.Artwork;

import java.util.ArrayList;

public class ArtworksRepository {
    private static ArtworksRepository instance;
    private ArrayList<Artwork> artworksDataSet = new ArrayList<>();

    public static ArtworksRepository getInstance() {
        if(instance == null) {
            instance = new ArtworksRepository();
        }
        return instance;
    }

    //This is the method where we are retrieving the artworks data from the webservice
    public MutableLiveData<ArrayList<Artwork>> getArtworksData() {
        setArtworks();

        MutableLiveData<ArrayList<Artwork>> data = new MutableLiveData<>();
        data.setValue(artworksDataSet);
        return data;
    }

    private void setArtworks() {
        artworksDataSet.add(new Artwork(null, "Artwork1", "This is artwork 1", "aa", "Rare1", "Your Dick1" ));
        artworksDataSet.add(new Artwork(null, "Artwork2", "This is artwork 2", "aa", "Rare2", "Your Dick2" ));
        artworksDataSet.add(new Artwork(null, "Artwork3", "This is artwork 3", "aa", "Rare3", "Your Dick3" ));
        artworksDataSet.add(new Artwork(null, "Artwork4", "This is artwork 4", "aa", "Rare4", "Your Dick4" ));
        artworksDataSet.add(new Artwork(null, "Artwork5", "This is artwork 5", "aa", "Rare5", "Your Dick5" ));
        artworksDataSet.add(new Artwork(null, "Artwork6", "This is artwork 6", "aa", "Rare6", "Your Dick6" ));
        artworksDataSet.add(new Artwork(null, "Artwork7", "This is artwork 7", "aa", "Rare7", "Your Dick7" ));
        artworksDataSet.add(new Artwork(null, "Artwork8", "This is artwork 8", "aa", "Rare8", "Your Dick8" ));
        artworksDataSet.add(new Artwork(null, "Artwork9", "This is artwork 9", "aa", "Rare9", "Your Dick9" ));
        artworksDataSet.add(new Artwork(null, "Artwork10", "This is artwork 10", "aa", "Rare10", "Your Dick10" ));
    }

    public void removeArtwork(int position) {
        artworksDataSet.remove(position);
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

}

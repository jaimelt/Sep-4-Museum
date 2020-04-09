package com.example.android_sep4.repositories;

import android.net.Uri;

import androidx.lifecycle.MutableLiveData;

import com.example.android_sep4.R;
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
        String artwork1 = Uri.parse("android.resource://"+ R.class.getPackage().getName()+"/drawable/" + "artwork1").toString();
        String artwork2 = Uri.parse("android.resource://"+ R.class.getPackage().getName()+"/drawable/" + "artwork2").toString();
        String artwork3 = Uri.parse("android.resource://"+ R.class.getPackage().getName()+"/drawable/" + "artwork3").toString();
        String artwork4 = Uri.parse("android.resource://"+ R.class.getPackage().getName()+"/drawable/" + "artwork4").toString();
        String artwork5 = Uri.parse("android.resource://"+ R.class.getPackage().getName()+"/drawable/" + "artwork5").toString();
        String artwork6 = Uri.parse("android.resource://"+ R.class.getPackage().getName()+"/drawable/" + "artwork6").toString();
        String artwork7 = Uri.parse("android.resource://"+ R.class.getPackage().getName()+"/drawable/" + "artwork7").toString();
        String artwork8 = Uri.parse("android.resource://"+ R.class.getPackage().getName()+"/drawable/" + "artwork8").toString();
        String artwork9 = Uri.parse("android.resource://"+ R.class.getPackage().getName()+"/drawable/" + "artwork9").toString();
        String artwork10 = Uri.parse("android.resource://"+ R.class.getPackage().getName()+"/drawable/" + "artwork10").toString();
        artworksDataSet.add(new Artwork(null, "Artwork1", "This is artwork 1", artwork1, "Rare1", "Your Dick1" ));
        artworksDataSet.add(new Artwork(null, "Artwork2", "This is artwork 2", artwork2, "Rare2", "Your Dick2" ));
        artworksDataSet.add(new Artwork(null, "Artwork3", "This is artwork 3", artwork3, "Rare3", "Your Dick3" ));
        artworksDataSet.add(new Artwork(null, "Artwork4", "This is artwork 4", artwork4, "Rare4", "Your Dick4" ));
        artworksDataSet.add(new Artwork(null, "Artwork5", "This is artwork 5", artwork5, "Rare5", "Your Dick5" ));
        artworksDataSet.add(new Artwork(null, "Artwork6", "This is artwork 6", artwork6, "Rare6", "Your Dick6" ));
        artworksDataSet.add(new Artwork(null, "Artwork7", "This is artwork 7", artwork7, "Rare7", "Your Dick7" ));
        artworksDataSet.add(new Artwork(null, "Artwork8", "This is artwork 8", artwork8, "Rare8", "Your Dick8" ));
        artworksDataSet.add(new Artwork(null, "Artwork9", "This is artwork 9", artwork9, "Rare9", "Your Dick9" ));
        artworksDataSet.add(new Artwork(null, "Artwork10", "This is artwork 10", artwork10, "Rare10", "Your Dick10" ));
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

    public void editArtwork(Artwork artwork, int position) {
        artworksDataSet.remove(position);
        artworksDataSet.add(position, artwork);
    }
}

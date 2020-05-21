package com.example.android_sep4.viewmodel.artwork;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.repositories.ArtworksRepository;

import java.util.ArrayList;

public class ArtworksStorageViewModel extends AndroidViewModel {
    private ArtworksRepository artworksRepository;

    public ArtworksStorageViewModel(Application application) {
        super(application);
        artworksRepository = ArtworksRepository.getInstance(application);
    }

    public LiveData<ArrayList<Artwork>> getArtworks() {
        return artworksRepository.getArtworksData();
    }

    public void removeArtwork(int position) {
        artworksRepository.positionToId(position);
    }

    public Artwork getArtwork(int position) {
        return artworksRepository.getArtwork(position);

        //      THIS WILL BE THE REQUEST TO GET THE ARTWORK AND WE WILL NEED TO PASS THE ID OF THE ARTWORK AND NOT THE POSITION
//        return artworksRepository.getArtworkById(id);
    }

    public void addArtwork(int position, Artwork artwork) {
        artworksRepository.addArtwork(position, artwork);
        //      THIS WILL BE THE REQUEST TO ADD THE ARTWORK AND WE WILL NEED TO PASS THE ARTWORK
//        artworksRepository.addNewArtwork(artwork);
    }
}

package com.example.android_sep4.viewmodel.artwork;

import androidx.lifecycle.ViewModel;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.model.ArtworkMeasurements;
import com.example.android_sep4.repositories.ArtworksRepository;

public class NewArtworkViewModel extends ViewModel {
    private ArtworksRepository artworksRepository;

    public void init() {
        artworksRepository = ArtworksRepository.getInstance();
    }

    public void addArtwork(String name, String author, String type, String description, String image, String location)
    {
        ArtworkMeasurements measurements = new ArtworkMeasurements(1,1,1,1,1,1,1,1);
        Artwork artwork = new Artwork(measurements, name, description, image, type, author, location);
        artworksRepository.addArtwork(artwork);
    }

}

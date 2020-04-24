package com.example.android_sep4.viewmodel.artwork;

import androidx.lifecycle.ViewModel;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.model.ArtworkMeasurements;
import com.example.android_sep4.repositories.ArtworksRepository;

public class NewArtworkViewModel extends ViewModel {
    private ArtworksRepository artworksRepository;
    private Artwork artwork;

    public void init() {
        artworksRepository = ArtworksRepository.getInstance();
    }

    public void addArtwork(String name, String author, String type, String description, String image, String location)
    {
        artwork = new Artwork(null, name, description, image, type, author, location);
        artworksRepository.addArtwork(artwork);
    }

    public void addArtworkMeasurements(int minTemp, int maxTemp, int minLight, int maxLight, int minCO2, int maxCO2, int minHum, int maxHum) {
        artwork.setArtworkMeasurements(minTemp, maxTemp, minLight, maxLight, minCO2, maxCO2, minHum, maxHum);
    }
}

package com.example.android_sep4.viewmodel.artwork;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.repositories.ArtworksRepository;

public class NewArtworkViewModel extends AndroidViewModel {
    private ArtworksRepository artworksRepository;
    private Artwork artwork;

  public NewArtworkViewModel(Application application)
  {
      super(application);
      artworksRepository = ArtworksRepository.getInstance(application);
  }

    public void addArtwork(String name, String author, String type, String description, String image, String location) {
        artwork = new Artwork(null, name, description, image, type, author, location);
        artworksRepository.addArtwork(artwork);
    }

    public void addArtworkMeasurements(int maxLight, int minLight, int maxTemp, int minTemp, int maxHum, int minHum, int maxCO2, int minCO2) {
        artwork.setArtworkMeasurements(maxLight, minLight, maxTemp, minTemp, maxHum, minHum, maxCO2, minCO2);
    }
}

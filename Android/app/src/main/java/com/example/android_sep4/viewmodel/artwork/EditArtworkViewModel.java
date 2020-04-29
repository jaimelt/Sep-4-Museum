package com.example.android_sep4.viewmodel.artwork;

import android.net.Uri;

import androidx.lifecycle.ViewModel;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.model.ArtworkMeasurements;
import com.example.android_sep4.repositories.ArtworksRepository;

public class EditArtworkViewModel extends ViewModel {
    private ArtworksRepository artworksRepository;
    private Artwork artwork;

    public void init(int position) {
        artworksRepository = ArtworksRepository.getInstance();
        artwork = artworksRepository.getArtwork(position);
    }

    public String getName()
    {
        return artwork.getName();
    }

    public String getType()
    {
        return artwork.getType();
    }

    public String getDescription()
    {
        return artwork.getDescription();
    }

    public String getAuthor()
    {
        return artwork.getAuthor();
    }

    public String getLocation() {
        return artwork.getLocation();
    }

    public void editArtwork(String name, String author, String type, String location, String description, String image, int position) {
        artwork.setName(name);
        artwork.setAuthor(author);
        artwork.setType(type);
        artwork.setLocation(location);
        artwork.setDescription(description);
        artwork.setImage(image);
        artworksRepository.editArtwork(artwork, position);
    }

    public void editArtworkMeasurements(int maxLight, int minLight, int maxTemp, int minTemp, int maxHum, int minHum, int maxCO2, int minCO2)
    {
        artwork.setArtworkMeasurements(maxLight, minLight, maxTemp, minTemp, maxHum, minHum, maxCO2, minCO2);
    }

    public Uri getImage() {
        return Uri.parse(artwork.getImage());
    }


}

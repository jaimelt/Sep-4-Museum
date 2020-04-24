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

    public void editArtworkMeasurements(int minTemp, int maxTemp, int minLight, int maxLight, int minCO2, int maxCO2, int minHum, int maxHum)
    {
        artwork.setArtworkMeasurements(minTemp, maxTemp, minLight, maxLight, minCO2, maxCO2, minHum, maxHum);
    }

    public Uri getImage() {
        return Uri.parse(artwork.getImage());
    }


}

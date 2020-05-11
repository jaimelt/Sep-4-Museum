package com.example.android_sep4.viewmodel.artwork;

import android.app.Application;
import android.net.Uri;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.repositories.ArtworksRepository;

public class EditArtworkViewModel extends AndroidViewModel {
    private ArtworksRepository artworksRepository;
    private Artwork artwork;

    public EditArtworkViewModel(Application application, int position)
    {
        super(application);
        artworksRepository = ArtworksRepository.getInstance(application);
        artwork = artworksRepository.getArtwork(position);
    }

    public String getName() {
        return artwork.getName();
    }

    public String getType() {
        return artwork.getType();
    }

    public String getDescription() {
        return artwork.getDescription();
    }

    public String getAuthor() {
        return artwork.getAuthor();
    }

    public String getLocation() {
        return artwork.getRoomCode();
    }

    public void editArtwork(String name, String author, String type, String location, String description, String image, int position) {
        artwork.setName(name);
        artwork.setAuthor(author);
        artwork.setType(type);
        artwork.setRoomCode(location);
        artwork.setDescription(description);
        artwork.setImage(image);
        artworksRepository.editArtwork(artwork, position);
    }

    public void editArtworkMeasurements(int maxLight, int minLight, int maxTemp, int minTemp, int maxHum, int minHum, int maxCO2, int minCO2) {
        artwork.setArtworkMeasurements(maxLight, minLight, maxTemp, minTemp, maxHum, minHum, maxCO2, minCO2);
    }

    public Uri getImage() {
        return Uri.parse(artwork.getImage());
    }


}

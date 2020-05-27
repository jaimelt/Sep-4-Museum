package com.example.android_sep4.viewmodel.artwork;

import android.app.Application;
import android.net.Uri;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.model.ArtworkMeasurements;
import com.example.android_sep4.repositories.ArtworksRepository;

public class EditArtworkViewModel extends AndroidViewModel {
    private ArtworksRepository artworksRepository;
    private Artwork artwork;

    public EditArtworkViewModel(Application application, int id) {
        super(application);
        artworksRepository = ArtworksRepository.getInstance(application);
        artwork = artworksRepository.getArtwork(id);
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

    public String getComment() {
        return artwork.getComment();
    }

    public void editArtwork(int id, String name, String author, String type, String location, String description, String comment, String image, int position, int maxLight, int minLight, int maxTemperature, int minTemperature, int maxHumidity, int minHumidity, int maxCo2, int minCo2) {
        Artwork artwork = new Artwork(id, name, description, comment, image, type, author, location, position, maxLight, minLight, maxTemperature, minTemperature, maxHumidity, minHumidity, maxCo2, minCo2);
        artworksRepository.editArtwork(artwork);
    }

    public Uri getImage() {
        return Uri.parse(artwork.getImage());
    }


}

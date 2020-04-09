package com.example.android_sep4.viewmodel.artwork;

import android.net.Uri;

import androidx.lifecycle.ViewModel;

import com.example.android_sep4.model.Artwork;
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

    public void editArtwork(String name, String author, String type, String description, String image, int position) {
        artwork.setName(name);
        artwork.setAuthor(author);
        artwork.setType(type);
        artwork.setDescription(description);
        artwork.setImage(image);
        artworksRepository.editArtwork(artwork, position);
    }

    public Uri getImage() {
        return Uri.parse(artwork.getImage());
    }
}

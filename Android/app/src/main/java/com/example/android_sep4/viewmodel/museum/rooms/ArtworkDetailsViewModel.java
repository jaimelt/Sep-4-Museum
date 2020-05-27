package com.example.android_sep4.viewmodel.museum.rooms;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.repositories.ArtworksRepository;

public class ArtworkDetailsViewModel extends AndroidViewModel {
    private ArtworksRepository artworksRepository;

    public ArtworkDetailsViewModel(@NonNull Application application) {
        super(application);
        artworksRepository = ArtworksRepository.getInstance(application);
    }

    public LiveData<Artwork> getArtworkById(int id) {
        return  artworksRepository.getArtworkById(id);
    }
}

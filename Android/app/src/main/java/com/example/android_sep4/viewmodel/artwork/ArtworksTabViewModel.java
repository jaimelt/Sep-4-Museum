package com.example.android_sep4.viewmodel.artwork;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.repositories.ArtworksRepository;

import java.util.ArrayList;

public class ArtworksTabViewModel extends AndroidViewModel {
    private ArtworksRepository artworksRepository;

    public ArtworksTabViewModel(Application application) {
        super(application);
        artworksRepository = ArtworksRepository.getInstance(application);
    }

    public LiveData<ArrayList<Artwork>> getArtworks() {
        return artworksRepository.getArtworksData();
    }

    public LiveData<Boolean> getIsLoading() {
        return artworksRepository.getIsLoading();
    }
}

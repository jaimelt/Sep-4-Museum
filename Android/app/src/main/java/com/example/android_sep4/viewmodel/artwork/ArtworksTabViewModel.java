package com.example.android_sep4.viewmodel.artwork;

import android.app.Application;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.repositories.ArtworksRepository;

import java.util.ArrayList;

public class ArtworksTabViewModel extends AndroidViewModel {
    private ArtworksRepository artworksRepository;

    public ArtworksTabViewModel(Application application) {
        super(application);
        artworksRepository = ArtworksRepository.getInstance(application);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public LiveData<ArrayList<Artwork>> getArtworks() {
        return artworksRepository.getArtworksData();
    }

}

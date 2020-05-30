package com.example.android_sep4.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.repositories.NotificationRepository;

import java.util.ArrayList;

public class NotificationsViewModel extends AndroidViewModel {
    private NotificationRepository notificationRepository;

    public NotificationsViewModel(Application application) {
        super(application);
        notificationRepository = NotificationRepository.getInstance(application);
    }

    public LiveData<ArrayList<Artwork>> getArtworksInDanger() {
        return notificationRepository.getArtworksInDanger();
    }

    public LiveData<Boolean> getIsLoaded() {
        return notificationRepository.getIsLoaded();
    }
}

package com.example.android_sep4.repositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.model.Artworks;
import com.example.android_sep4.requests.NotificationsEndpoints;
import com.example.android_sep4.requests.ServiceGenerator;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NotificationRepository {
    private static NotificationRepository instance;
    private Application application;
    private MutableLiveData<ArrayList<Artwork>> artworksInDangerData = new MutableLiveData<>();
    private MutableLiveData<Boolean> loaded = new MutableLiveData<>();

    public NotificationRepository(Application application) {
        this.application = application;

    }

    public static synchronized NotificationRepository getInstance(Application application) {
        if (instance == null) {
            instance = new NotificationRepository(application);
        }
        return instance;
    }

    public LiveData<Boolean> getIsLoaded() {
        return loaded;
    }

    public LiveData<ArrayList<Artwork>> getArtworksInDanger() {
        Log.i("Notification Repository", "getArtworksData: called ");
        NotificationsEndpoints endpoints = ServiceGenerator.getNotificationsEndpoints();

        Call<Artworks> call = endpoints.getArtworksInDanger();

        call.enqueue(new Callback<Artworks>() {
            @Override
            public void onResponse(Call<Artworks> call, Response<Artworks> response) {
                Log.i("Notification Repository", "onResponse: success!");
                if (response.isSuccessful() && response.body() != null) {
                    artworksInDangerData.setValue(response.body().getArtworks());
                    loaded.setValue(true);
                }
            }

            @Override
            public void onFailure(Call<Artworks> call, Throwable t) {
                ArrayList<Artwork> arrayList = new ArrayList<>();
                artworksInDangerData.setValue(arrayList);
                loaded.setValue(false);
                Log.i("Notification Repository", "onFailure: called");
            }
        });
        return artworksInDangerData;
    }

}

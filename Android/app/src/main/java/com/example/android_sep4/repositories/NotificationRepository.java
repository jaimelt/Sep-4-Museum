package com.example.android_sep4.repositories;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.preference.PreferenceManager;

import com.example.android_sep4.R;
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
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(application);
        boolean prefNotification = sharedPreferences.getBoolean(application.getString(R.string.pref_notification_key), application.getResources().getBoolean(R.bool.pref_notification_default));
        if (!prefNotification)
        {
            loaded.setValue(false);
        }
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
                    loaded = new MutableLiveData<>();
                }
            }

            @Override
            public void onFailure(Call<Artworks> call, Throwable t) {
                ArrayList<Artwork> arrayList = new ArrayList<>();
                artworksInDangerData.setValue(arrayList);
                loaded.setValue(false);
                loaded = new MutableLiveData<>();
                Log.i("Notification Repository", "onFailure: called");
            }
        });
        return artworksInDangerData;
    }

}

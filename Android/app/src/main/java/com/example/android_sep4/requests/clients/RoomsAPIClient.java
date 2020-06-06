package com.example.android_sep4.requests.clients;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.preference.PreferenceManager;

import com.example.android_sep4.R;
import com.example.android_sep4.model.Room;
import com.example.android_sep4.model.RoomMeasurements;
import com.example.android_sep4.model.Rooms;
import com.example.android_sep4.requests.RoomEndpoints;
import com.example.android_sep4.requests.ServiceGenerator;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class RoomsAPIClient {
    private MutableLiveData<Rooms> roomsData = new MutableLiveData<>();
    private MutableLiveData<RoomMeasurements> liveRoomMeasurements = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private Application application;


    public RoomsAPIClient(Application application) {
        this.application = application;
    }

    public void getRoomsData() {
        isLoading.setValue(true);
        RoomEndpoints endpoints = ServiceGenerator.getRoomEndpoints();
        Call<Rooms> call = endpoints.getRoomsDetails();
        call.enqueue(new Callback<Rooms>() {
            @Override
            public void onResponse(@NonNull Call<Rooms> call, @NonNull Response<Rooms> response) {
                Log.i(TAG, "onResponse: called!");
                if (response.isSuccessful() && response.body() != null) {
                    Rooms rooms = response.body();
                    SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(application);
                    boolean prefTemperature = sharedPreferences.getBoolean(application.getString(R.string.pref_temperature_key), application.getResources().getBoolean(R.bool.pref_temperature_default));
                    if (!prefTemperature) {
                        rooms.changeCelsiusToFahrenheit();
                    }
                    roomsData.setValue(rooms);
                    roomsData = new MutableLiveData<>();
                }
                isLoading.setValue(false);
            }

            @Override
            public void onFailure(@NonNull Call<Rooms> call, @NonNull Throwable t) {
                Log.i(TAG, "onFailure: called");
                roomsData.setValue(new Rooms(new ArrayList<>()));
            }
        });
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public void editRoomOptimalConditions(Room room) {
        RoomEndpoints endpoints = ServiceGenerator.getRoomEndpoints();
        String locationCode = room.getLocationCode();

        Call<Room> call = endpoints.editOptimalConditions(locationCode, room);

        call.enqueue(new Callback<Room>() {
            @Override
            public void onResponse(@NonNull Call<Room> call, @NonNull Response<Room> response) {
                getRoomsData();
                Log.i(TAG, "onResponse: success!");
            }

            @Override
            public void onFailure(@NonNull Call<Room> call, @NonNull Throwable t) {
                getRoomsData();
                Log.i(TAG, "onFailure: called");
            }
        });
    }

    public LiveData<Rooms> getRoomsDataLive() {
        return roomsData;
    }

}

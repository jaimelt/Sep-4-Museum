package com.example.android_sep4.requests.clients;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.android_sep4.model.Room;
import com.example.android_sep4.model.Rooms;
import com.example.android_sep4.requests.RoomEndpoints;
import com.example.android_sep4.requests.ServiceGenerator;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class RoomsAPIClient {
    private MutableLiveData<ArrayList<Room>> roomsData = new MutableLiveData<>();
    private MutableLiveData<Room> roomByIdData = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private Room room;
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
            public void onResponse(Call<Rooms> call, Response<Rooms> response) {
                Log.i(TAG, "onResponse: success!");
                if (response.isSuccessful() && response.body() != null) {
                    roomsData.setValue(response.body().getRooms());
                }
                isLoading.setValue(false);
            }

            @Override
            public void onFailure(Call<Rooms> call, Throwable t) {
                Log.i(TAG, "onFailure: called");
                roomsData.setValue(new ArrayList<>());
            }
        });
    }


    public LiveData<Room> getRoomById(String id) {
        RoomEndpoints endpoints = ServiceGenerator.getRoomEndpoints();

        Call<Room> call = endpoints.getRoomById(id);

        call.enqueue(new Callback<Room>() {
            @Override
            public void onResponse(Call<Room> call, Response<Room> response) {
                Log.i(TAG, "onResponse: success!");
//                Room apiRoomById = response.body();
//                if (apiRoomById != null) {
//                    room = new Room(apiRoomById.getLocationCode(), apiRoomById.getDescription(), apiRoomById.getTotalCapacity(), apiRoomById.getCurrentCapacity(), apiRoomById.getArtworkList(), apiRoomById.getLight(), apiRoomById.getTemperature(), apiRoomById.getHumidity(), apiRoomById.getCo2(),
//                            apiRoomById.getLiveRoomMeasurements());
//                }
                if(response.isSuccessful() && response.body() != null)
                {
                    roomByIdData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Room> call, Throwable t) {
                Log.i(TAG, "onFailure: called");

            }
        });
        return roomByIdData;
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
            public void onResponse(Call<Room> call, Response<Room> response) {
                getRoomsData();
                Log.i(TAG, "onResponse: success!");
            }

            @Override
            public void onFailure(Call<Room> call, Throwable t) {
                getRoomsData();
                Log.i(TAG, "onFailure: called");
            }
        });
    }

    public LiveData<ArrayList<Room>> getRoomsDataLive() {
        return roomsData;
    }
}

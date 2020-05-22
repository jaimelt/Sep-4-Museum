package com.example.android_sep4.requests.clients;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.model.Room;
import com.example.android_sep4.model.Rooms;
import com.example.android_sep4.requests.ArtworkEndpoints;
import com.example.android_sep4.requests.RoomEndpoints;
import com.example.android_sep4.requests.ServiceGenerator;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class RoomsAPIClient {
    private MutableLiveData<ArrayList<Room>> roomsData = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Artwork>> artworksInRoomData = new MutableLiveData<>();
    private MutableLiveData<Room> roomByIdData = new MutableLiveData<>();
    private ArrayList<Artwork> artworksInRoomDataSet = new ArrayList<>();
    private ArrayList<Room> roomsDataSet = new ArrayList<>();
    private Room room;

    public LiveData<ArrayList<Room>> getRoomsData() {
        RoomEndpoints endpoints = ServiceGenerator.getRoomEndpoints();

        Call<Rooms> call = endpoints.getRoomsDetails();

        call.enqueue(new Callback<Rooms>() {
            @Override
            public void onResponse(Call<Rooms> call, Response<Rooms> response) {
                Log.i(TAG, "onResponse: success!");
                Rooms apiRooms = response.body();
                if (apiRooms != null) {
                    roomsDataSet.addAll(apiRooms.getRooms());
                }
            }
            @Override
            public void onFailure(Call<Rooms> call, Throwable t) {
                Log.i(TAG, "onFailure: called");
            }
        });
        roomsData.setValue(roomsDataSet);
        roomsDataSet = new ArrayList<>();
        return roomsData;
    }

    public LiveData<ArrayList<Artwork>> getArtworksByRoomIdData(String roomCode) {
        ArtworkEndpoints endpoints = ServiceGenerator.getArtworkEndpoints();

        Call<ArrayList<Artwork>> call = endpoints.getArtworksByRoomId(roomCode);

        call.enqueue(new Callback<ArrayList<Artwork>>() {
            @Override
            public void onResponse(Call<ArrayList<Artwork>> call, Response<ArrayList<Artwork>> response) {
                Log.i(TAG, "onResponse: success!");
                ArrayList<Artwork> apiArtworksInRoom = response.body();
                if (apiArtworksInRoom != null) {
                    artworksInRoomDataSet.addAll(apiArtworksInRoom);
                }
            }
            @Override
            public void onFailure(Call<ArrayList<Artwork>> call, Throwable t) {
                Log.i(TAG, "onFailure: called");
            }
        });
        artworksInRoomData.setValue(artworksInRoomDataSet);
        artworksInRoomDataSet = new ArrayList<>();
        return artworksInRoomData;
    }

    public LiveData<Room> getRoomById(String id) {
        RoomEndpoints endpoints = ServiceGenerator.getRoomEndpoints();

        Call<Room> call = endpoints.getRoomById(id);

        call.enqueue(new Callback<Room>() {
            @Override
            public void onResponse(Call<Room> call, Response<Room> response) {
                Log.i(TAG, "onResponse: success!");
                Room apiRoomById = response.body();
                if (apiRoomById != null) {
                    room = new Room(apiRoomById.getArtworkList(), apiRoomById.getOptimalMeasurementConditions(), apiRoomById.getMeasurementConditions(),
                            apiRoomById.getLocationCode(), apiRoomById.getDescription(), apiRoomById.getTotalCapacity(), apiRoomById.getCurrentCapacity());
                }
            }
            @Override
            public void onFailure(Call<Room> call, Throwable t) {
                Log.i(TAG, "onFailure: called");
            }
        });
        roomByIdData.setValue(room);
        return roomByIdData;

    }

}

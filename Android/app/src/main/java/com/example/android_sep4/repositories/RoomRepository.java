package com.example.android_sep4.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.model.ArtworkMeasurements;
import com.example.android_sep4.model.ArtworkResponse;
import com.example.android_sep4.model.Artworks;
import com.example.android_sep4.model.Room;
import com.example.android_sep4.model.RoomMeasurements;
import com.example.android_sep4.model.Rooms;
import com.example.android_sep4.requests.ArtworkEndpoints;
import com.example.android_sep4.requests.RoomEndpoints;
import com.example.android_sep4.requests.ServiceGenerator;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class RoomRepository {
    private static RoomRepository instance;
    private MutableLiveData<ArrayList<Room>> roomsData = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Artwork>> artworksInRoomData = new MutableLiveData<>();
    private MutableLiveData<Room> roomByIdData = new MutableLiveData<>();
    private ArrayList<Artwork> artworksInRoomDataSet = new ArrayList<>();
    private ArrayList<Room> roomsDataSet = new ArrayList<>();
    private Room room;

    public static RoomRepository getInstance() {
        if (instance == null) {
            instance = new RoomRepository();
        }
        return instance;
    }

    //This is the method where we are retrieving the artworks data from the webservice
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

    private void setRooms() {
        roomsDataSet.add(new Room(new ArrayList<Artwork>(), new RoomMeasurements(3000, 30, 14, 400), new RoomMeasurements(5000, 30, 14, 400), "A1", "good room", 10, 6));
        roomsDataSet.add(new Room(new ArrayList<Artwork>(), new RoomMeasurements(5000, 20, 90, 400), new RoomMeasurements(5000, 30, 22, 400), "A2", "bad room", 10, 5));
        roomsDataSet.add(new Room(new ArrayList<Artwork>(), new RoomMeasurements(1000, 15, 59, 400), new RoomMeasurements(5000, 30, 12, 400), "A3", "excellent room", 10, 5));
        roomsDataSet.add(new Room(new ArrayList<Artwork>(), new RoomMeasurements(6000, 33, 50, 400), new RoomMeasurements(5000, 30, 22, 400), "B1", "worst room", 10, 5));
        roomsDataSet.add(new Room(new ArrayList<Artwork>(), new RoomMeasurements(8000, 34, 90, 400), new RoomMeasurements(5000, 30, 14, 400), "B2", "goddest room", 10, 5));
        roomsDataSet.add(new Room(new ArrayList<Artwork>(), new RoomMeasurements(9000, 99, 59, 400), new RoomMeasurements(5000, 30, 12, 400), "B3", "sex room", 10, 5));
        roomsDataSet.add(new Room(new ArrayList<Artwork>(), new RoomMeasurements(3000, 35, 90, 400), new RoomMeasurements(5000, 30, 22, 400), "B4", "play room", 10, 5));
    }

    public Room getRoom(int position) {
        return roomsDataSet.get(position);
    }

    public void editRoomOptimal(int light, int co2, int temperature, int humidity, int position) {
        roomsDataSet.get(position).setOptimalMeasurementConditions(humidity, temperature, co2, light);
    }

}

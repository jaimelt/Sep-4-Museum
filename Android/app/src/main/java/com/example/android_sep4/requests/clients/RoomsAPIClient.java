package com.example.android_sep4.requests.clients;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

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

public class RoomsAPIClient {
    private MutableLiveData<ArrayList<Room>> roomsData = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Artwork>> artworksInRoomData = new MutableLiveData<>();
    private MutableLiveData<Room> roomByIdData = new MutableLiveData<>();
    private ArrayList<Artwork> artworksInRoomDataSet = new ArrayList<>();
    private ArrayList<Room> roomsDataSet = new ArrayList<>();
    private Room room;
    private Artwork artwork;
    private Application application;

    public RoomsAPIClient(Application application)
    {
        this.application = application;
        roomsDataSet = new ArrayList<>();
    }

    public LiveData<ArrayList<Room>> getRoomsData() {
        RoomEndpoints endpoints = ServiceGenerator.getRoomEndpoints();
        Call<Rooms> call = endpoints.getRoomsDetails();
        call.enqueue(new Callback<Rooms>() {
            @Override
            public void onResponse(Call<Rooms> call, Response<Rooms> response) {
                Log.i(TAG, "onResponse: success!");
                Rooms apiRooms = response.body();
                if (apiRooms != null) {
                    Toast.makeText(application, "IT WORKS", Toast.LENGTH_SHORT).show();
                    for(Room apiRoom : apiRooms.getRooms()) {
                        room = new Room(apiRoom.getLocationCode(), apiRoom.getDescription(), apiRoom.getTotalCapacity(), apiRoom.getCurrentCapacity(), apiRoom.getArtworkList(),
                                apiRoom.getLight(), apiRoom.getCo2(), apiRoom.getHumidity(), apiRoom.getTemperature(), apiRoom.getLiveRoomMeasurements());
                        roomsDataSet.add(room);
                    }
                }
            }
            @Override
            public void onFailure(Call<Rooms> call, Throwable t) {
                Log.i(TAG, "onFailure: called");
                Toast.makeText(application, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        roomsData.setValue(roomsDataSet);
        return roomsData;
    }

    public LiveData<ArrayList<Room>> getRooms()
    {
        return roomsData;
    }

    public LiveData<ArrayList<Artwork>> getArtworksByRoomIdData(String roomCode) {
        RoomEndpoints endpoints = ServiceGenerator.getRoomEndpoints();

        Call<Artworks> call = endpoints.getArtworksByRoomId(roomCode);

        call.enqueue(new Callback<Artworks>() {
            @Override
            public void onResponse(Call<Artworks> call, Response<Artworks> response) {
                Artworks artworksFromRoom = response.body();
                if (artworksFromRoom != null) {
                    for (ArtworkResponse apiArtwork : artworksFromRoom.getArtworks()) {
                        ArtworkMeasurements artworkMeasurements = new ArtworkMeasurements(apiArtwork.getMaxLight(), apiArtwork.getMinLight(), apiArtwork.getMaxTemperature(),
                                apiArtwork.getMinTemperature(), apiArtwork.getMaxHumidity(), apiArtwork.getMinHumidity(), apiArtwork.getMaxCo2(), apiArtwork.getMinCo2());
                        artwork = new Artwork(apiArtwork.getId(), apiArtwork.getName(), apiArtwork.getDescription(), null, apiArtwork.getImage(), apiArtwork.getType(),
                                apiArtwork.getAuthor(), apiArtwork.getRoomCode(), /*apiArtwork.getArtworkPosition() ,*/ artworkMeasurements);
                        artworksInRoomDataSet.add(artwork);
                    }
                }
            }

            @Override
            public void onFailure(Call<Artworks> call, Throwable t) {

            }
        });
        artworksInRoomData.setValue(artworksInRoomDataSet);
        artworksInRoomDataSet = new ArrayList<>();
        return artworksInRoomData;
    }

    public LiveData<Room> getRoomById(String id) {
//        RoomEndpoints endpoints = ServiceGenerator.getRoomEndpoints();
//
//        Call<Room> call = endpoints.getRoomById(id);
//
//        call.enqueue(new Callback<Room>() {
//            @Override
//            public void onResponse(Call<Room> call, Response<Room> response) {
//                Log.i(TAG, "onResponse: success!");
//                Room apiRoomById = response.body();
//                if (apiRoomById != null) {
//                    room = new Room(apiRoomById.getArtworkList(), apiRoomById.getOptimalMeasurementConditions(), apiRoomById.getMeasurementConditions(),
//                            apiRoomById.getLocationCode(), apiRoomById.getDescription(), apiRoomById.getTotalCapacity(), apiRoomById.getCurrentCapacity());
//                }
//            }
//            @Override
//            public void onFailure(Call<Room> call, Throwable t) {
//                Log.i(TAG, "onFailure: called");
//            }
//        });
//        roomByIdData.setValue(room);
        return roomByIdData;

    }

}

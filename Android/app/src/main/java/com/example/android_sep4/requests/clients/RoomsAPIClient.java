package com.example.android_sep4.requests.clients;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.model.Artworks;
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
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private ArrayList<Artwork> artworksInRoomDataSet = new ArrayList<>();
    private Room room;
    private Artwork artwork = new Artwork();
    private Application application;


    public RoomsAPIClient(Application application) {
        this.application = application;
    }

    public LiveData<ArrayList<Room>> getRoomsData() {
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
                ArrayList<Room> arrayList = new ArrayList<>();
                roomsData.setValue(arrayList);
            }
        });
        return roomsData;
    }

    public LiveData<ArrayList<Artwork>> getArtworksByRoomId(String roomCode) {
        isLoading.setValue(true);
        ArtworkEndpoints endpoints = ServiceGenerator.getArtworkEndpoints();

        Call<Artworks> call = endpoints.getArtworksByRoomId(roomCode);

        call.enqueue(new Callback<Artworks>() {
            @Override
            public void onResponse(Call<Artworks> call, Response<Artworks> response) {
                Log.i(TAG, "onResponse: artworks in room");
                if (response.body() != null && response.isSuccessful()) {
                    artworksInRoomData.setValue(response.body().getArtworks());
                    isLoading.setValue(false);
                }
            }

            @Override
            public void onFailure(Call<Artworks> call, Throwable t) {
                //HERE YOU ARE CALLING THE ROOM DATABASE AND SETTING artworksDataSet TO THE ARTWORKS FROM ROOM BY ROOM CODE
            }
        });
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
                    room = new Room(apiRoomById.getLocationCode(), apiRoomById.getDescription(), apiRoomById.getTotalCapacity(), apiRoomById.getCurrentCapacity(),apiRoomById.getArtworkList(), apiRoomById.getLight(), apiRoomById.getTemperature(),apiRoomById.getHumidity(),apiRoomById.getCo2(),
                            apiRoomById.getLiveRoomMeasurements()  );
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

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

}

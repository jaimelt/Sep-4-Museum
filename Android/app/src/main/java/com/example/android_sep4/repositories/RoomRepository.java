package com.example.android_sep4.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.model.Room;
import com.example.android_sep4.requests.clients.RoomsAPIClient;

import java.util.ArrayList;

public class RoomRepository {
    private static RoomRepository instance;
        private MutableLiveData<ArrayList<Room>> roomsData = new MutableLiveData<>();
    private MutableLiveData<Room> roomByIdData = new MutableLiveData<>();
    private ArrayList<Room> roomsDataSet = new ArrayList<>();
    private RoomsAPIClient roomsAPIClient;
    private Application application;
    private boolean danger;

    public RoomRepository(Application application) {
        this.application = application;
        roomsAPIClient = new RoomsAPIClient(application);

    }

    public static RoomRepository getInstance(Application application) {
        if (instance == null) {
            instance = new RoomRepository(application);
        }
        return instance;
    }

    public LiveData<ArrayList<Room>> getRoomsData() {
        roomsAPIClient.getRoomsData().observeForever(new Observer<ArrayList<Room>>() {
            @Override
            public void onChanged(ArrayList<Room> rooms) {
                if(rooms.isEmpty())
                {
                    //ROOM DATABASE
                }
                else {
                    roomsData.setValue(rooms);
                    //danger = getIsInDanger();
                }
            }
        });
        return roomsData;
    }

    public boolean getDanger(){
        return danger;
    }

//    public boolean getIsInDanger() {
//        for (Room room : roomsDataSet) {
//            for (Artwork artwork : room.getArtworkList().getArtworks()) {
//                System.out.println(room.getArtworkList().getArtworks().size());
//                if (artwork.getMaxCo2() < room.getLiveRoomMeasurements().getCo2() || artwork.getMinCo2() > room.getLiveRoomMeasurements().getCo2()) {
//                    danger = true;
//                } else if (artwork.getMaxHumidity() < room.getLiveRoomMeasurements().getHumidity() || artwork.getMinHumidity() > room.getLiveRoomMeasurements().getHumidity()) {
//                    danger = true;
//                } else if (artwork.getMaxTemperature() < room.getLiveRoomMeasurements().getTemp() || artwork.getMinTemperature() > room.getLiveRoomMeasurements().getTemp()) {
//                    danger = true;
//                } else if (artwork.getMaxLight() < room.getLiveRoomMeasurements().getLight() || artwork.getMinLight() > room.getLiveRoomMeasurements().getLight()) {
//                    danger = true;
//                }
//            }
//        }
//        return danger;
//    }



    public LiveData<Room> getRoomById(String locationCode) {
        return roomsAPIClient.getRoomById(locationCode);
    }

    public LiveData<Boolean> getIsLoading() {
        return roomsAPIClient.getIsLoading();
    }

    public void editRoomOptimal(int light, int co2, int temperature, int humidity, int position) {
       //to implement the edit room optional

    }
}

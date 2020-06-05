package com.example.android_sep4.requests.clients;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.model.Artworks;
import com.example.android_sep4.requests.ArtworkEndpoints;
import com.example.android_sep4.requests.ServiceGenerator;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;

public class ArtworksAPIClient {
    private MutableLiveData<ArrayList<Artwork>> artworksData = new MutableLiveData<>();
    private MutableLiveData<Artwork> artworkData = new MutableLiveData<>();
    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Artwork>> artworksInRoomData = new MutableLiveData<>();
    private MutableLiveData<ArrayList<Artwork>> artworksInStorage = new MutableLiveData<>();
    private Application application;

    public ArtworksAPIClient(Application application) {
        this.application = application;
    }

    public void getArtworksData() {
        Log.i(TAG, "getArtworksData: called ");
        isLoading.setValue(true);
        ArtworkEndpoints endpoints = ServiceGenerator.getArtworkEndpoints();

        Call<Artworks> call = endpoints.getArtworks();

        call.enqueue(new Callback<Artworks>() {
            @Override
            public void onResponse(@NonNull Call<Artworks> call, @NonNull Response<Artworks> response) {
                Log.i(TAG, "onResponse: success!");
                if (response.isSuccessful() && response.body() != null) {
                    artworksData.setValue(response.body().getArtworks());
                }
                isLoading.setValue(false);
            }

            @Override
            public void onFailure(@NonNull Call<Artworks> call, @NonNull Throwable t) {
                ArrayList<Artwork> arrayList = new ArrayList<>();
                artworksData.setValue(arrayList);
                Log.i(TAG, "onFailure: called");
                // DAVE HERE YOU ARE CALLING THE ROOM DATABASE AND YOU ARE SETTING THE ARTWORKS DATA SET TO THE ARTWORKS THAT WE HAVE IN THERE
            }
        });
    }


    public LiveData<Artwork> getArtworkById(int id) {
        ArtworkEndpoints endpoints = ServiceGenerator.getArtworkEndpoints();
        Call<Artwork> call = endpoints.getArtworkById(id);

        System.out.println("API CLIENT ID " + id);
        call.enqueue(new Callback<Artwork>() {
            @Override
            public void onResponse(@NonNull Call<Artwork> call, @NonNull Response<Artwork> response) {
                if (response.isSuccessful() && response.body() != null) {
                    artworkData.setValue(response.body());
                    artworkData = new MutableLiveData<>();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Artwork> call, @NonNull Throwable t) {
                artworkData.setValue(new Artwork());
                //CALL DATABASE TO SET THE ARTWORK BY ID
            }
        });
        return artworkData;
    }


    public void editArtwork(Artwork editedArtwork) {
        int artworkID = editedArtwork.getId();

        ArtworkEndpoints endpoints = ServiceGenerator.getArtworkEndpoints();

        Call<Artwork> call = endpoints.editArtwork(artworkID, editedArtwork);
        call.enqueue(new Callback<Artwork>() {
            @Override
            public void onResponse(@NonNull Call<Artwork> call, @NonNull Response<Artwork> response) {
                System.out.println("SUCCESSFUL UPDATE!");
                getArtworksData();
                //HERE YOU WILL CALL THE ROOM DATABASE TO EDIT THE ARTWORK FROM THERE
            }

            @Override
            public void onFailure(@NonNull Call<Artwork> call, @NonNull Throwable t) {
                System.out.println("UPDATE FAILED!");
                getArtworksData();
                //ALSO HERE, IF THE CALL IS FAILED AT LEAST WE WILL UPDATE IT IN THE LOCAL DATABASE
            }
        });
    }

    public void addNewArtwork(Artwork artwork) {
        Artwork newArtwork = new Artwork();

        newArtwork.setName(artwork.getName());
        newArtwork.setAuthor(artwork.getAuthor());
        newArtwork.setDescription(artwork.getDescription());
        newArtwork.setComment(artwork.getComment());
        newArtwork.setImage(artwork.getImage());
        newArtwork.setType(artwork.getType());
        newArtwork.setRoomCode(artwork.getRoomCode());
        newArtwork.setArtworkPosition(artwork.getArtworkPosition());
        newArtwork.setMaxCo2(artwork.getMaxCo2());
        newArtwork.setMinCo2(artwork.getMinCo2());
        newArtwork.setMaxHumidity(artwork.getMaxHumidity());
        newArtwork.setMinHumidity(artwork.getMinHumidity());
        newArtwork.setMaxTemperature(artwork.getMaxTemperature());
        newArtwork.setMinTemperature(artwork.getMinTemperature());
        newArtwork.setMinLight(artwork.getMinLight());
        newArtwork.setMaxLight(artwork.getMaxLight());

        ArtworkEndpoints endpoints = ServiceGenerator.getArtworkEndpoints();

        Call<Artwork> call = endpoints.addArtwork(newArtwork);

        call.enqueue(new Callback<Artwork>() {
            @Override
            public void onResponse(@NonNull Call<Artwork> call, @NonNull Response<Artwork> response) {
                getArtworksData();
                System.out.println("SUCCESSFUL UPDATE!");
                //CALL ROOM DATABASE TO ADD
            }

            @Override
            public void onFailure(@NonNull Call<Artwork> call, @NonNull Throwable t) {
                getArtworksData();
                System.out.println("UPDATE FAILED!");
                //CALL THE ROOM TO ADD ALSO IN CASE THE API IS FAILED
            }
        });
    }

    public LiveData<ArrayList<Artwork>> getArtworksByRoomId(String roomCode) {
        isLoading.setValue(true);
        ArtworkEndpoints endpoints = ServiceGenerator.getArtworkEndpoints();

        Call<Artworks> call = endpoints.getArtworksByRoomId(roomCode);

        call.enqueue(new Callback<Artworks>() {
            @Override
            public void onResponse(@NonNull Call<Artworks> call, @NonNull Response<Artworks> response) {
                Log.i(TAG, "onResponse: artworks in room");
                if (response.body() != null && response.isSuccessful()) {
                    artworksInRoomData.setValue(response.body().getArtworks());
                    artworksInRoomData = new MutableLiveData<>();
                    isLoading.setValue(false);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Artworks> call, @NonNull Throwable t) {
                //HERE YOU ARE CALLING THE ROOM DATABASE AND SETTING artworksDataSet TO THE ARTWORKS FROM ROOM BY ROOM CODE
                artworksInRoomData.setValue(new ArrayList<>());
            }
        });
        return artworksInRoomData;
    }

    public LiveData<ArrayList<Artwork>> getArtworksFromStorage(String roomCode) {
        isLoading.setValue(true);
        ArtworkEndpoints endpoints = ServiceGenerator.getArtworkEndpoints();

        Call<Artworks> call = endpoints.getArtworksByRoomId(roomCode);

        call.enqueue(new Callback<Artworks>() {
            @Override
            public void onResponse(@NonNull Call<Artworks> call, @NonNull Response<Artworks> response) {
                Log.i(TAG, "onResponse: artworks in room");
                if (response.body() != null && response.isSuccessful()) {
                    artworksInStorage.setValue(response.body().getArtworks());
                    artworksInStorage = new MutableLiveData<>();
                    isLoading.setValue(false);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Artworks> call, @NonNull Throwable t) {
                //HERE YOU ARE CALLING THE ROOM DATABASE AND SETTING artworksDataSet TO THE ARTWORKS FROM ROOM BY ROOM CODE
                artworksInStorage.setValue(new ArrayList<>());
            }
        });
        return artworksInStorage;
    }

    public void deleteArtwork(int id) {
        ArtworkEndpoints endpoints = ServiceGenerator.getArtworkEndpoints();
        Call<Artwork> call = endpoints.deleteArtwork(id);
        call.enqueue(new Callback<Artwork>() {
            @Override
            public void onResponse(@NonNull Call<Artwork> call, @NonNull Response<Artwork> response) {
                System.out.println("SUCCESSFUL DELETE!");
                getArtworksByRoomId("Storage");
            }

            @Override
            public void onFailure(@NonNull Call<Artwork> call, @NonNull Throwable t) {
                System.out.println("DELETE FAILED!");
                getArtworksByRoomId("Storage");
            }
        });
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<ArrayList<Artwork>> getArtworksDataLive() {
        return artworksData;
    }

    public void moveArtwork(int artworkID, String location) {
        ArtworkEndpoints endpoints = ServiceGenerator.getArtworkEndpoints();

        Call<String> call = endpoints.moveArtwork(artworkID, location);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                System.out.println("SUCCESSFUL MOVE!");
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                System.out.println("MOVE FAILED!");
            }
        });
    }

}

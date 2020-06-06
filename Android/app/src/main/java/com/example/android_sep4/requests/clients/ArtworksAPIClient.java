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
                if (response.isSuccessful() && response.body() != null) {
                    Log.i(TAG, "onResponse: called!");
                    artworksData.setValue(response.body().getArtworks());
                }
                isLoading.setValue(false);
            }

            @Override
            public void onFailure(@NonNull Call<Artworks> call, @NonNull Throwable t) {
                ArrayList<Artwork> arrayList = new ArrayList<>();
                artworksData.setValue(arrayList);
                Log.i(TAG, "onFailure: called");
            }
        });
    }


    public LiveData<Artwork> getArtworkById(int id) {
        ArtworkEndpoints endpoints = ServiceGenerator.getArtworkEndpoints();
        Call<Artwork> call = endpoints.getArtworkById(id);

        call.enqueue(new Callback<Artwork>() {
            @Override
            public void onResponse(@NonNull Call<Artwork> call, @NonNull Response<Artwork> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.i(TAG, "onResponse: called!");
                    artworkData.setValue(response.body());
                    artworkData = new MutableLiveData<>();
                }
            }

            @Override
            public void onFailure(@NonNull Call<Artwork> call, @NonNull Throwable t) {
                Log.i(TAG, "onFailure: called!");
                artworkData.setValue(new Artwork());
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
                Log.i(TAG, "onResponse: called!");
                getArtworksData();
            }

            @Override
            public void onFailure(@NonNull Call<Artwork> call, @NonNull Throwable t) {
                Log.i(TAG, "onFailure: called!");
                getArtworksData();
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
                Log.i(TAG, "onResponse: called!");
                getArtworksData();
            }

            @Override
            public void onFailure(@NonNull Call<Artwork> call, @NonNull Throwable t) {
                Log.i(TAG, "onFailure: called!");
                getArtworksData();
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
                if (response.body() != null && response.isSuccessful()) {
                    Log.i(TAG, "onResponse: called!");
                    artworksInRoomData.setValue(response.body().getArtworks());
                    artworksInRoomData = new MutableLiveData<>();
                    isLoading.setValue(false);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Artworks> call, @NonNull Throwable t) {
                Log.i(TAG, "onFailure: called!");
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
                if (response.body() != null && response.isSuccessful()) {
                    Log.i(TAG, "onResponse: artworks in room");
                    artworksInStorage.setValue(response.body().getArtworks());
                    artworksInStorage = new MutableLiveData<>();
                    isLoading.setValue(false);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Artworks> call, @NonNull Throwable t) {
                Log.i(TAG, "onFailure: called!");
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
                Log.i(TAG, "onResponse: called!");
                getArtworksByRoomId("Storage");
            }

            @Override
            public void onFailure(@NonNull Call<Artwork> call, @NonNull Throwable t) {
                Log.i(TAG, "onFailure: called");
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
                Log.i(TAG, "onResponse: called!");
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                Log.i(TAG, "onFailure: called!");
            }
        });
    }

}

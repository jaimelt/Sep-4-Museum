package com.example.android_sep4.requests.clients;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.android_sep4.database.ArtworkDao;
import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.model.ArtworkMeasurements;
import com.example.android_sep4.model.ArtworkResponse;
import com.example.android_sep4.model.Artworks;
import com.example.android_sep4.model.Room;
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
    private ArrayList<Artwork> artworksDataSet = new ArrayList<>();
    private ArtworkDao artworkDao;
    private Artwork artwork = new Artwork();
    private Application application;

    public ArtworksAPIClient(Application application)
    {
        this.application = application;
    }

    public LiveData<ArrayList<Artwork>> getArtworksData() {
        Log.i(TAG, "getArtworksData: called ");
        ArtworkEndpoints endpoints = ServiceGenerator.getArtworkEndpoints();

        Call<Artworks> call = endpoints.getArtworks();

        call.enqueue(new Callback<Artworks>() {
            @Override
            public void onResponse(Call<Artworks> call, Response<Artworks> response) {
                Log.i(TAG, "onResponse: success!");
                Artworks apiArtworks = response.body();
                if (apiArtworks != null) {
                    for (ArtworkResponse apiArtwork : apiArtworks.getArtworks()) {
                        ArtworkMeasurements artworkMeasurements = new ArtworkMeasurements(apiArtwork.getMaxLight(), apiArtwork.getMinLight(), apiArtwork.getMaxTemperature(),
                                apiArtwork.getMinTemperature(), apiArtwork.getMaxHumidity(), apiArtwork.getMinHumidity(), apiArtwork.getMaxCo2(), apiArtwork.getMinCo2());
                        artwork = new Artwork(apiArtwork.getId(), apiArtwork.getName(), apiArtwork.getDescription(), null, apiArtwork.getImage(), apiArtwork.getType(),
                                apiArtwork.getAuthor(), apiArtwork.getRoomCode(), /*apiArtwork.getArtworkPosition() ,*/ artworkMeasurements);
                        artworksDataSet.add(artwork);
                    }
                }
            }

            @Override
            public void onFailure(Call<Artworks> call, Throwable t) {
                Log.i(TAG, "onFailure: called");
            }
        });
        artworksData.setValue(artworksDataSet);
        artworksDataSet = new ArrayList<>();
        return artworksData;
    }

    public LiveData<ArrayList<Artwork>> getArtworksByRoomId(String roomCode) {
        ArtworkEndpoints endpoints = ServiceGenerator.getArtworkEndpoints();

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
                        artworksDataSet.add(artwork);
                    }
                }
            }

            @Override
            public void onFailure(Call<Artworks> call, Throwable t) {

            }
        });
        artworksData.setValue(artworksDataSet);
        artworksDataSet = new ArrayList<>();
        return artworksData;
    }

    public LiveData<Artwork> getArtworkById(int id) {
        ArtworkEndpoints endpoints = ServiceGenerator.getArtworkEndpoints();

        Call<Artwork> call = endpoints.getArtworkById(id);

        call.enqueue(new Callback<Artwork>() {
            @Override
            public void onResponse(Call<Artwork> call, Response<Artwork> response) {
                Artwork artworkAPI = response.body();
                if (artworkAPI != null) {
                    artworkData.setValue(artworkAPI);
                }
            }

            @Override
            public void onFailure(Call<Artwork> call, Throwable t) {

            }
        });

        return artworkData;
    }

    public void editArtwork(Artwork editedArtwork) {
        int artworkID = editedArtwork.getId();

        Artwork updatedArtwork = new Artwork();
        updatedArtwork.setName(editedArtwork.getName());
        updatedArtwork.setAuthor(editedArtwork.getAuthor());
        updatedArtwork.setDescription(editedArtwork.getDescription());
        updatedArtwork.setComment(editedArtwork.getComment());
        updatedArtwork.setImage(editedArtwork.getImage());
        updatedArtwork.setType(editedArtwork.getType());
        updatedArtwork.setRoomCode(editedArtwork.getRoomCode());
        updatedArtwork.setArtworkPosition(editedArtwork.getArtworkPosition());
        updatedArtwork.setArtworkMeasurements(editedArtwork.getArtworkMeasurements());

        ArtworkEndpoints endpoints = ServiceGenerator.getArtworkEndpoints();

        Call<Artwork> call = endpoints.editArtwork(artworkID, updatedArtwork);

        call.enqueue(new Callback<Artwork>() {
            @Override
            public void onResponse(Call<Artwork> call, Response<Artwork> response) {
                System.out.println("SUCCESSFUL UPDATE!");
            }

            @Override
            public void onFailure(Call<Artwork> call, Throwable t) {
                System.out.println("UPDATE FAILED!");
            }
        });
    }

    public void addNewArtwork(Artwork artwork) {
        ArtworkResponse newArtwork = new ArtworkResponse();

        newArtwork.setName(artwork.getName());
        newArtwork.setAuthor(artwork.getAuthor());
        newArtwork.setDescription(artwork.getDescription());
        newArtwork.setComment(artwork.getComment());
        newArtwork.setImage(artwork.getImage());
        newArtwork.setType(artwork.getType());
        newArtwork.setRoomCode(artwork.getRoomCode());
        newArtwork.setArtworkPosition(artwork.getArtworkPosition());
        newArtwork.setMaxCo2(artwork.getArtworkMeasurements().getMaxCO2());
        newArtwork.setMinCo2(artwork.getArtworkMeasurements().getMinCO2());
        newArtwork.setMaxHumidity(artwork.getArtworkMeasurements().getMaxHumidity());
        newArtwork.setMinHumidity(artwork.getArtworkMeasurements().getMinHumidity());
        newArtwork.setMaxLight(artwork.getArtworkMeasurements().getMaxLight());
        newArtwork.setMinLight(artwork.getArtworkMeasurements().getMinLight());
        newArtwork.setMaxTemperature(artwork.getArtworkMeasurements().getMaxTemp());
        newArtwork.setMinTemperature(artwork.getArtworkMeasurements().getMinTemp());

        ArtworkEndpoints endpoints = ServiceGenerator.getArtworkEndpoints();

        Call<ArtworkResponse> call = endpoints.addArtwork(newArtwork);

        call.enqueue(new Callback<ArtworkResponse>() {
            @Override
            public void onResponse(Call<ArtworkResponse> call, Response<ArtworkResponse> response) {
                System.out.println("SUCCESSFUL UPDATE!");
            }

            @Override
            public void onFailure(Call<ArtworkResponse> call, Throwable t) {
                System.out.println("UPDATE FAILED!");
            }
        });
    }

    public void deleteArtwork(int id) {
        ArtworkEndpoints endpoints = ServiceGenerator.getArtworkEndpoints();

        Call<Artwork> call = endpoints.deleteArtwork(id);
        call.enqueue(new Callback<Artwork>() {
            @Override
            public void onResponse(Call<Artwork> call, Response<Artwork> response) {
                System.out.println("SUCCESSFUL DELETE!");
            }

            @Override
            public void onFailure(Call<Artwork> call, Throwable t) {
                System.out.println("DELETE FAILED!");
            }
        });
    }


}

package com.example.android_sep4.requests.clients;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.android_sep4.database.ArtworkDao;
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
                    Toast.makeText(application, "it works in artwork", Toast.LENGTH_SHORT).show();
                    for (Artwork apiArtwork : apiArtworks.getArtworks()) {
                        artwork = new Artwork(apiArtwork.getId(), apiArtwork.getName(), apiArtwork.getDescription(), null, apiArtwork.getImage(), apiArtwork.getType(),
                                apiArtwork.getAuthor(), apiArtwork.getRoomCode(), apiArtwork.getArtworkPosition(), apiArtwork.getMaxLight(), apiArtwork.getMinLight(), apiArtwork.getMaxTemperature(),
                                apiArtwork.getMinTemperature(), apiArtwork.getMaxHumidity(), apiArtwork.getMinHumidity(), apiArtwork.getMaxCo2(), apiArtwork.getMinCo2());
                        artworksDataSet.add(artwork);
                    }
                }
            }

            @Override
            public void onFailure(Call<Artworks> call, Throwable t) {
                Log.i(TAG, "onFailure: called");
                // DAVE HERE YOU ARE CALLING THE ROOM DATABASE AND YOU ARE SETTING THE ARTWORKS DATA SET TO THE ARTWORKS THAT WE HAVE IN THERE
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
                //CALL DATABASE TO SET THE ARTWORK BY ID
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
        updatedArtwork.setMaxCo2(editedArtwork.getMaxCo2());
        updatedArtwork.setMinCo2(editedArtwork.getMinCo2());
        updatedArtwork.setMaxHumidity(editedArtwork.getMaxHumidity());
        updatedArtwork.setMinHumidity(editedArtwork.getMinHumidity());
        updatedArtwork.setMaxTemperature(editedArtwork.getMaxTemperature());
        updatedArtwork.setMinTemperature(editedArtwork.getMinTemperature());
        updatedArtwork.setMinLight(editedArtwork.getMinLight());
        updatedArtwork.setMaxLight(editedArtwork.getMaxLight());

        ArtworkEndpoints endpoints = ServiceGenerator.getArtworkEndpoints();

        Call<Artwork> call = endpoints.editArtwork(artworkID, updatedArtwork);

        call.enqueue(new Callback<Artwork>() {
            @Override
            public void onResponse(Call<Artwork> call, Response<Artwork> response) {
                System.out.println("SUCCESSFUL UPDATE!");
                //HERE YOU WILL CALL THE ROOM DATABASE TO EDIT THE ARTWORK FROM THERE
            }

            @Override
            public void onFailure(Call<Artwork> call, Throwable t) {
                System.out.println("UPDATE FAILED!");
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
            public void onResponse(Call<Artwork> call, Response<Artwork> response) {
                System.out.println("SUCCESSFUL UPDATE!");
                //CALL ROOM DATABASE TO ADD
            }

            @Override
            public void onFailure(Call<Artwork> call, Throwable t) {
                System.out.println("UPDATE FAILED!");
                //CALL THE ROOM TO ADD ALSO IN CASE THE API IS FAILED
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
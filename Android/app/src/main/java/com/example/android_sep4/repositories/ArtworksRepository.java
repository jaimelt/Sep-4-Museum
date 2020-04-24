package com.example.android_sep4.repositories;

import android.net.Uri;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.android_sep4.R;
import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.requests.ArtworkEndpoints;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArtworksRepository {
    private static ArtworksRepository instance;
    private ArrayList<Artwork> artworksDataSet = new ArrayList<>();
    private MediatorLiveData<ArrayList<Artwork>> artworks = new MediatorLiveData<>();

    public static ArtworksRepository getInstance() {
        if (instance == null) {
            instance = new ArtworksRepository();
        }
        return instance;
    }

    //This is the method where we are retrieving the artworks data from the webservice
    public MutableLiveData<ArrayList<Artwork>> getArtworksData() {
        setArtworks();

        MutableLiveData<ArrayList<Artwork>> data = new MutableLiveData<>();
        data.setValue(artworksDataSet);
        return data;
    }

    private void setArtworks() {
        String artwork1 = Uri.parse("android.resource://" + R.class.getPackage().getName() + "/drawable/" + "artwork1").toString();
        String artwork2 = Uri.parse("android.resource://" + R.class.getPackage().getName() + "/drawable/" + "artwork2").toString();
        String artwork3 = Uri.parse("android.resource://" + R.class.getPackage().getName() + "/drawable/" + "artwork3").toString();
        String artwork4 = Uri.parse("android.resource://" + R.class.getPackage().getName() + "/drawable/" + "artwork4").toString();
        String artwork5 = Uri.parse("android.resource://" + R.class.getPackage().getName() + "/drawable/" + "artwork5").toString();
        String artwork6 = Uri.parse("android.resource://" + R.class.getPackage().getName() + "/drawable/" + "artwork6").toString();
        String artwork7 = Uri.parse("android.resource://" + R.class.getPackage().getName() + "/drawable/" + "artwork7").toString();
        String artwork8 = Uri.parse("android.resource://" + R.class.getPackage().getName() + "/drawable/" + "artwork8").toString();
        String artwork9 = Uri.parse("android.resource://" + R.class.getPackage().getName() + "/drawable/" + "artwork9").toString();
        String artwork10 = Uri.parse("android.resource://" + R.class.getPackage().getName() + "/drawable/" + "artwork10").toString();
        artworksDataSet.add(new Artwork(null, "Artwork1", "This is artwork 1", artwork1, "Painting", "Your Dick1", "A1"));
        artworksDataSet.add(new Artwork(null, "Artwork2", "This is artwork 2", artwork2, "Drawing", "Your Dick2", "A2"));
        artworksDataSet.add(new Artwork(null, "Artwork3", "This is artwork 3", artwork3, "Ceramics", "Your Dick3", "A3"));
        artworksDataSet.add(new Artwork(null, "Artwork4", "This is artwork 4", artwork4, "Photo", "Your Dick4", "B1"));
        artworksDataSet.add(new Artwork(null, "Artwork5", "This is artwork 5", artwork5, "Painting", "Your Dick5", "B2"));
        artworksDataSet.add(new Artwork(null, "Artwork6", "This is artwork 6", artwork6, "Drawing", "Your Dick6", "B3"));
        artworksDataSet.add(new Artwork(null, "Artwork7", "This is artwork 7", artwork7, "Ceramics", "Your Dick7", "B4"));
        artworksDataSet.add(new Artwork(null, "Artwork8", "This is artwork 8", artwork8, "Photo", "Your Dick8", "A1"));
        artworksDataSet.add(new Artwork(null, "Artwork9", "This is artwork 9", artwork9, "Painting", "Your Dick9", "A2"));
        artworksDataSet.add(new Artwork(null, "Artwork10", "This is artwork 10", artwork10, "Drawing", "Your Dick10", "Storage"));

        //THIS IS THE API CALL TO GET ALL THE ARTWORKS!!!!!! WHEN THE API WILL BE READY WE WILL DELETE THE HARDCODED VALUES
//        Retrofit.Builder builder = new Retrofit.Builder()
//                .baseUrl("apiURL")
//                .addConverterFactory(GsonConverterFactory.create());
//
//        Retrofit retrofit = builder.build();
//
//        ArtworkEndpoints endpoints = retrofit.create(ArtworkEndpoints.class);
//
//        Call<ArrayList<Artwork>> call = endpoints.getArtworks();
//
//        call.enqueue(new Callback<ArrayList<Artwork>>() {
//            @Override
//            public void onResponse(Call<ArrayList<Artwork>> call, Response<ArrayList<Artwork>> response) {
//                ArrayList<Artwork> apiArtworks = response.body();
//                if (apiArtworks != null) {
//                    artworksDataSet.addAll(apiArtworks);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<Artwork>> call, Throwable t) {
//
//            }
//        });
    }

    public void removeArtwork(int position) {
        artworksDataSet.remove(position);
    }

    public Artwork getArtwork(int position) {
        return artworksDataSet.get(position);
    }

    public void addArtwork(int position, Artwork artwork) {
        artworksDataSet.add(position, artwork);
    }

    public void addArtwork(Artwork artwork) {
        artworksDataSet.add(artwork);
    }

    public void editArtwork(Artwork artwork, int position) {
        artworksDataSet.remove(position);
        artworksDataSet.add(position, artwork);
    }
}

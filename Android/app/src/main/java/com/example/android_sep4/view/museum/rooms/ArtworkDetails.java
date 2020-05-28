package com.example.android_sep4.view.museum.rooms;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.android_sep4.R;
import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.viewmodel.museum.rooms.ArtworkDetailsViewModel;

public class ArtworkDetails extends AppCompatActivity {
    private ArtworkDetailsViewModel artworkDetailsViewModel;
    private TextView artworkName, artworkAuthor, artworkDescription, artworkType,
            humidity, co2, light, temperature;
    private ImageView artworkImage;
    private int artoworkID;
    private Artwork artworkInMuseum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_artwork_details);

        Intent intent = getIntent();
        artoworkID = intent.getIntExtra("ArtworkID", 0);

        System.out.println(artoworkID);

        setViewModel();
        setMetrics();
        findViews();
    }

    private void setViewModel() {
        artworkDetailsViewModel = new ViewModelProvider(this).get(ArtworkDetailsViewModel.class);

        artworkDetailsViewModel.getArtworkById(artoworkID).observe(this, apiArtwork -> {
            artworkDetailsViewModel.getArtworkById(artoworkID).removeObservers(this);
            String name = apiArtwork.getName();
            String author = apiArtwork.getAuthor();
            String description = apiArtwork.getDescription();
            String type = apiArtwork.getType();
            Uri imageView = Uri.parse(apiArtwork.getImage());
            int maxHumidity = apiArtwork.getMaxHumidity();
            int maxTemp = apiArtwork.getMaxTemperature();
            int maxCo2 = apiArtwork.getMaxCo2();
            int maxLight = apiArtwork.getMaxLight();

            artworkName.setText("Name: " + name);
            artworkAuthor.setText("Author: " + author);
            artworkDescription.setText(description);
            artworkType.setText(type);
            artworkImage.setImageURI(imageView);
            humidity.setText(String.valueOf(maxHumidity));
            co2.setText(String.valueOf(maxCo2));
            light.setText(String.valueOf(maxLight));
            temperature.setText(String.valueOf(maxTemp));

        });
    }

    public void setMetrics() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .6));
    }

    public void findViews() {
        artworkName = findViewById(R.id.artworkNamePopUp);
        artworkAuthor = findViewById(R.id.artworkAuthorPopUp);
        artworkDescription = findViewById(R.id.artworkDescriptionPopUp);
        artworkType = findViewById(R.id.artworkTypePopUp);
        artworkImage = findViewById(R.id.artworkImagePopUp);
        humidity = findViewById(R.id.humidityPopUp);
        co2 = findViewById(R.id.co2PopUp);
        light = findViewById(R.id.lightPopUp);
        temperature = findViewById(R.id.tempPopUp);
    }

//    private void setArtwork() {
//        if(artworkInMuseum != null) {
//            String name = artworkInMuseum.getName();
//            String author = artworkInMuseum.getAuthor();
//            String description = artworkInMuseum.getDescription();
//            String type = artworkInMuseum.getType();
//            Uri imageView = Uri.parse(artworkInMuseum.getImage());
//            int maxHumidity = artworkInMuseum.getMaxHumidity();
//            int maxTemp = artworkInMuseum.getMaxTemperature();
//            int maxCo2 = artworkInMuseum.getMaxCo2();
//            int maxLight = artworkInMuseum.getMaxLight();
//
//            artworkName.setText("Name: " + name);
//            artworkAuthor.setText("Author: " + author);
//            artworkDescription.setText(description);
//            artworkType.setText(type);
//            artworkImage.setImageURI(imageView);
//            humidity.setText(String.valueOf(maxHumidity));
//            co2.setText(String.valueOf(maxCo2));
//            light.setText(String.valueOf(maxLight));
//            temperature.setText(String.valueOf(maxTemp));
//        } else {
//            artworkName.setText("Name: ");
//            artworkAuthor.setText("Author: " );
//            artworkDescription.setText("description");
//            artworkType.setText("type");
//            artworkImage.setImageURI(null);
//            humidity.setText("maxHumidity");
//            co2.setText("maxCo2");
//            light.setText("maxLight");
//            temperature.setText("maxTemp");
//        }
//    }
}

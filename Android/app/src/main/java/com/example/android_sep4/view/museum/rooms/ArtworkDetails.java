package com.example.android_sep4.view.museum.rooms;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.android_sep4.R;
import com.example.android_sep4.viewmodel.museum.rooms.ArtworkDetailsViewModel;

public class ArtworkDetails extends AppCompatActivity {
    private ArtworkDetailsViewModel artworkDetailsViewModel;
    private TextView artworkName, artworkAuthor, artworkDescription, artworkType,
            humidity, co2, light, temperature;
    private ImageView artworkImage;
    private LinearLayout firstLayout, secondLayout;
    private int artworkID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_artwork_details);

        Intent intent = getIntent();
        artworkID = intent.getIntExtra("ArtworkID", 0);

        setMetrics();
        findViews();
        setViewModel();
    }

    @SuppressLint("SetTextI18n")
    private void setViewModel() {
        artworkDetailsViewModel = new ViewModelProvider(this).get(ArtworkDetailsViewModel.class);

        if (artworkID != 0) {
            artworkDetailsViewModel.getArtworkById(artworkID).observe(this, apiArtwork -> {
                artworkDetailsViewModel.getArtworkById(artworkID).removeObservers(this);
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
        } else {
            firstLayout.setVisibility(View.INVISIBLE);
            secondLayout.setVisibility(View.INVISIBLE);
            artworkType.setText("No artwork");
        }
    }

    public void setMetrics() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .6));
    }

    public void findViews() {
        artworkName = findViewById(R.id.artwork_name_pop_up);
        artworkAuthor = findViewById(R.id.artwork_author_pop_up);
        artworkDescription = findViewById(R.id.artwork_description_pop_up);
        artworkType = findViewById(R.id.artwork_type_pop_up);
        artworkImage = findViewById(R.id.artwork_image_pop_up);
        humidity = findViewById(R.id.humidity_pop_up);
        co2 = findViewById(R.id.co2_pop_up);
        light = findViewById(R.id.light_pop_up);
        temperature = findViewById(R.id.temp_pop_up);
        firstLayout = findViewById(R.id.first_layout);
        secondLayout = findViewById(R.id.second_layout);
    }
}

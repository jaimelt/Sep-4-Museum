package com.example.android_sep4.view.museum.rooms;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.android_sep4.R;
import com.example.android_sep4.model.Artwork;

public class ArtworkDetails extends AppCompatActivity {

    private TextView artworkName, artworkAuthor, artworkDescription, artworkType,
            humidity, co2, light, temperature;
    private ImageView artworkImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_artwork_details);
        setMetrics();
        findViews();
        getIntentExtra();
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

    @SuppressLint("SetTextI18n")
    public void getIntentExtra() {
        Intent intent = getIntent();
        Artwork artwork = intent.getParcelableExtra("Artwork");

        if (artwork != null) {
            String name = artwork.getName();
            String author = artwork.getAuthor();
            String description = artwork.getDescription();
            String type = artwork.getType();
            Uri imageView = Uri.parse(artwork.getImage());

            artworkName.setText("Name: " + name);
            artworkAuthor.setText("Author: " + author);
            artworkDescription.setText(description);
            artworkType.setText(type);
            artworkImage.setImageURI(imageView);
            humidity.setText("100");
            co2.setText("100");
            light.setText("100");
            temperature.setText("100");
        }
    }
}

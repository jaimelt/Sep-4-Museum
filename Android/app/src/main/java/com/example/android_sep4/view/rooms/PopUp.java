package com.example.android_sep4.view.rooms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

import com.example.android_sep4.R;
import com.example.android_sep4.model.Artwork;

public class PopUp extends AppCompatActivity {

    private TextView artworkName, artworkDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.popupwindow);
        setMetrics();

        artworkName = findViewById(R.id.artworkNamePopUp);
        artworkDescription = findViewById(R.id.artworkDescriptionPopUp);

        getIntentExtra();
    }

    public void setMetrics() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width*.8), (int) (height*.6));
    }

    public void getIntentExtra() {
        Intent intent = getIntent();
        Artwork artwork = intent.getParcelableExtra("Artwork");

        if(artwork != null) {
            String name = artwork.getName();
            String description = artwork.getDescription();

            System.out.println(name);
            System.out.println(description);

            artworkName.setText(name);
            artworkDescription.setText(description);

            System.out.println(artworkName.getText().toString());
            System.out.println(artworkDescription.getText().toString());
        }
    }
}

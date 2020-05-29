package com.example.android_sep4.view.museum.rooms;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.android_sep4.R;
import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.viewmodel.ViewModelFactory;
import com.example.android_sep4.viewmodel.museum.rooms.RoomA1ViewModel;

import java.util.ArrayList;

public class RoomA1Activity extends AppCompatActivity {
    private final static String ROOM_CODE = "A1";
    private RoomA1ViewModel roomA1ViewModel;
    private ArrayList<Artwork> artworksInRoom = new ArrayList<>();
    private ArrayList<TextView> textViews = new ArrayList<>();
    private TextView place_holder_1, place_holder_2, place_holder_3,
            place_holder_4, place_holder_5, place_holder_6,
            place_holder_7, place_holder_8, place_holder_9;
    private int artworkID;
    private  LiveData<ArrayList<Artwork>> liveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_a1);
        findViews();
        setTextViews();
        setViewModel();
        viewArtworks();
    }

    private void setViewModel() {
        roomA1ViewModel = new ViewModelProvider(this, new ViewModelFactory(this.getApplication())).get(RoomA1ViewModel.class);
        liveData = roomA1ViewModel.getArtworksFromRoom(ROOM_CODE);
        liveData.observe(this, artworks -> {
            liveData.removeObservers(this);
            artworksInRoom.addAll(artworks);
        });


        for (Artwork artwork : artworksInRoom) {
            if (artwork != null) {
                for (TextView textView : textViews) {
                    artwork.setArtworkPosition(Integer.parseInt(textView.getText().toString()));
                }
            }
        }
    }

    @Override
    protected void onPause() {
        liveData.removeObservers(this);
        super.onPause();
    }

    @Override
    protected void onResume() {
        liveData.observe(this, artworks -> artworksInRoom.addAll(artworks));
        super.onResume();
    }

    public void findViews() {
        place_holder_1 = findViewById(R.id.artwork_place_1);
        place_holder_2 = findViewById(R.id.artwork_place_2);
        place_holder_3 = findViewById(R.id.artwork_place_3);
        place_holder_4 = findViewById(R.id.artwork_place_4);
        place_holder_5 = findViewById(R.id.artwork_place_5);
        place_holder_6 = findViewById(R.id.artwork_place_6);
        place_holder_7 = findViewById(R.id.artwork_place_7);
        place_holder_8 = findViewById(R.id.artwork_place_8);
        place_holder_9 = findViewById(R.id.artwork_place_9);
    }

    public void setTextViews() {
        textViews.add(0, place_holder_1);
        textViews.add(1, place_holder_2);
        textViews.add(2, place_holder_3);
        textViews.add(3, place_holder_4);
        textViews.add(4, place_holder_5);
        textViews.add(5, place_holder_6);
        textViews.add(6, place_holder_7);
        textViews.add(7, place_holder_8);
        textViews.add(8, place_holder_9);
    }

    public void viewArtworks() {
        for (TextView textView : textViews) {
                textView.setOnClickListener(view -> {
                    Intent intent = new Intent(RoomA1Activity.this, ArtworkDetails.class);
                    intent.putExtra("ArtworkID", artworksInRoom.get(textViews.indexOf(textView)).getId());
                    startActivity(intent);

                    Toast.makeText(getApplicationContext(), "This is " + artworksInRoom.get(textViews.indexOf(textView)).getName(), Toast.LENGTH_SHORT).show();
                });
        }
    }

    private void createDialog(View dialogView) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, AlertDialog.THEME_DEVICE_DEFAULT_DARK);
        builder.setView(dialogView);
        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
    }
}

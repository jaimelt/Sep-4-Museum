package com.example.android_sep4.view.museum.rooms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.android_sep4.R;
import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.viewmodel.ViewModelFactory;
import com.example.android_sep4.viewmodel.museum.rooms.RoomB3ViewModel;

import java.util.ArrayList;

public class RoomB3Activity extends AppCompatActivity {
    private final static String ROOM_CODE = "B3";
    private RoomB3ViewModel roomB3ViewModel;
    private ArrayList<Artwork> artworksInRoom = new ArrayList<>();
    private ArrayList<TextView> textViews = new ArrayList<>();
    private ProgressBar progressBar;
    private TextView place_holder_1, place_holder_2, place_holder_3,
            place_holder_4, place_holder_5, place_holder_6,
            place_holder_7, place_holder_8, place_holder_9,
            place_holder_10, place_holder_11, place_holder_12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_b3);
        setToolbar();
        findViews();
        setTextViews();
        setViewModel();
        viewArtworks();
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("B3");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        finish();
        return true;
    }

    private void setViewModel() {
        roomB3ViewModel = new ViewModelProvider(this, new ViewModelFactory(this.getApplication())).get(RoomB3ViewModel.class);
        LiveData<ArrayList<Artwork>> liveData = roomB3ViewModel.getArtworksFromRoom(ROOM_CODE);
        liveData.observe(this, artworks -> {
            liveData.removeObservers(this);
            artworksInRoom.addAll(artworks);
        });

        roomB3ViewModel.getIsLoading().observe(this, aBoolean -> {
            if (aBoolean) {
                progressBar.setVisibility(View.VISIBLE);
                for (TextView textView : textViews) {
                    textView.setClickable(false);
                }
            } else {
                progressBar.setVisibility(View.GONE);
                for (TextView textView : textViews) {
                    textView.setClickable(true);
                }
            }
        });

        for (Artwork artwork : artworksInRoom) {
            if (artwork != null) {
                for (TextView textView : textViews) {
                    textView.setText(artwork.getName());
                    artwork.setArtworkPosition(textViews.indexOf(textView));
                }
            }
        }
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
        place_holder_10 = findViewById(R.id.artwork_place_10);
        place_holder_11 = findViewById(R.id.artwork_place_11);
        place_holder_12 = findViewById(R.id.artwork_place_12);
        progressBar = findViewById(R.id.progress_bar_roomB3);
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
        textViews.add(9, place_holder_10);
        textViews.add(10, place_holder_11);
        textViews.add(11, place_holder_12);
    }

    public void viewArtworks() {
        for (TextView textView : textViews) {
            textView.setOnClickListener(view -> {
                try {
                    Intent intent = new Intent(RoomB3Activity.this, ArtworkDetails.class);
                    intent.putExtra("ArtworkID", artworksInRoom.get(textViews.indexOf(textView)).getId());
                    startActivity(intent);
                } catch (IndexOutOfBoundsException e) {
                    Intent intent = new Intent(RoomB3Activity.this, EmptyArtworkActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
}

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
import com.example.android_sep4.viewmodel.museum.rooms.RoomB1ViewModel;

import java.util.ArrayList;

public class RoomB1Activity extends AppCompatActivity {
    private final static String ROOM_CODE = "B1";
    private RoomB1ViewModel roomB1ViewModel;
    private ArrayList<Artwork> artworksInRoom = new ArrayList<>();
    private ArrayList<TextView> textViews = new ArrayList<>();
    private ProgressBar progressBar;
    private TextView place_holder_1, place_holder_2, place_holder_3,
            place_holder_4, place_holder_5, place_holder_6,
            place_holder_7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_b1);
        setToolbar();
        findViews();
        setTextViews();
        setViewModel();
        viewArtworks();
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("B1");
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
        roomB1ViewModel = new ViewModelProvider(this, new ViewModelFactory(this.getApplication())).get(RoomB1ViewModel.class);
        LiveData<ArrayList<Artwork>> liveData = roomB1ViewModel.getArtworksFromRoom(ROOM_CODE);
        liveData.observe(this, artworks -> {
            liveData.removeObservers(this);
            artworksInRoom.addAll(artworks);
        });

        roomB1ViewModel.getIsLoading().observe(this, aBoolean -> {
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
        progressBar = findViewById(R.id.progress_bar_room_b1);
    }

    public void setTextViews() {
        textViews.add(0, place_holder_1);
        textViews.add(1, place_holder_2);
        textViews.add(2, place_holder_3);
        textViews.add(3, place_holder_4);
        textViews.add(4, place_holder_5);
        textViews.add(5, place_holder_6);
        textViews.add(6, place_holder_7);
    }

    public void viewArtworks() {
        for (TextView textView : textViews) {
            textView.setOnClickListener(view -> {
                try {
                    Intent intent = new Intent(RoomB1Activity.this, ArtworkDetails.class);
                    intent.putExtra("ArtworkID", artworksInRoom.get(textViews.indexOf(textView)).getId());
                    startActivity(intent);
                } catch (IndexOutOfBoundsException e) {
                    Intent intent = new Intent(RoomB1Activity.this, EmptyArtworkActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
}

package com.example.android_sep4.view.museum.rooms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.android_sep4.R;
import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.viewmodel.ViewModelFactory;
import com.example.android_sep4.viewmodel.museum.rooms.RoomB3ViewModel;
import com.example.android_sep4.viewmodel.museum.rooms.RoomB4ViewModel;

import java.util.ArrayList;

public class RoomB4Activity extends AppCompatActivity {
    private final static String ROOM_CODE = "B4";
    private RoomB4ViewModel roomB4ViewModel;
    private ArrayList<Artwork> artworksInRoom = new ArrayList<>();
    private ArrayList<TextView> textViews = new ArrayList<>();
    private ProgressBar progressBar;
    private TextView place_holder_1, place_holder_2, place_holder_3,
            place_holder_4, place_holder_5, place_holder_6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_b4);
        findViews();
        setTextViews();
        setViewModel();
        viewArtworks();
    }

    private void setViewModel() {
        roomB4ViewModel = new ViewModelProvider(this, new ViewModelFactory(this.getApplication())).get(RoomB4ViewModel.class);
        LiveData<ArrayList<Artwork>> liveData = roomB4ViewModel.getArtworksFromRoom(ROOM_CODE);
        liveData.observe(this, artworks -> {
            liveData.removeObservers(this);
            artworksInRoom.addAll(artworks);
        });

        roomB4ViewModel.getIsLoading().observe(this, aBoolean -> {
            if (aBoolean) {
                progressBar.setVisibility(View.VISIBLE);
                for (TextView textView : textViews) {
                    textView.setClickable(false);
                }
            } else {
                progressBar.setVisibility(View.GONE);
                for(TextView textView : textViews) {
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
        progressBar = findViewById(R.id.progress_bar_roomB4);
    }

    public void setTextViews() {
        textViews.add(0, place_holder_1);
        textViews.add(1, place_holder_2);
        textViews.add(2, place_holder_3);
        textViews.add(3, place_holder_4);
        textViews.add(4, place_holder_5);
        textViews.add(5, place_holder_6);
    }


    public void viewArtworks() {
        for (TextView textView : textViews) {
            textView.setOnClickListener(view -> {
                try {
                    Intent intent = new Intent(RoomB4Activity.this, ArtworkDetails.class);
                    intent.putExtra("ArtworkID", artworksInRoom.get(textViews.indexOf(textView)).getId());
                    startActivity(intent);
                } catch (IndexOutOfBoundsException e) {
                    Intent intent = new Intent(RoomB4Activity.this, EmptyArtworkActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
}

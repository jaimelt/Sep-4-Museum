package com.example.android_sep4.view.museum.rooms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.android_sep4.R;
import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.viewmodel.ViewModelFactory;
import com.example.android_sep4.viewmodel.museum.rooms.RoomA2ViewModel;

import java.util.ArrayList;

public class RoomA2Activity extends AppCompatActivity {
    private final static int ROOM_CAPACITY = 8;
    private final static String ROOM_CODE = "A2";
    private RoomA2ViewModel roomA2ViewModel;
    private ArrayList<Artwork> artworksInRoom = new ArrayList<>();
    private ArrayList<TextView> textViews = new ArrayList<>();
    private TextView place_holder_1, place_holder_2, place_holder_3,
            place_holder_4, place_holder_5, place_holder_6,
            place_holder_7, place_holder_8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_a2);
        findViews();
        setTextViews();
        setViewModel();
        viewArtworks();
    }

    private void setViewModel() {
        roomA2ViewModel = new ViewModelProvider(this, new ViewModelFactory(this.getApplication())).get(RoomA2ViewModel.class);

        roomA2ViewModel.getArtworksFromRoom(ROOM_CODE).observe(this, artworks -> {
            roomA2ViewModel.getArtworksFromRoom(ROOM_CODE).removeObservers(this);
            artworksInRoom.addAll(artworks);
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
    }

    public void viewArtworks() {
        for (final TextView textView : textViews) {
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(RoomA2Activity.this, ArtworkDetails.class);
                    intent.putExtra("ArtworkID", artworksInRoom.get(textViews.indexOf(textView)).getId());
                    startActivity(intent);

                    Toast.makeText(getApplicationContext(), "This is " + artworksInRoom.get(textViews.indexOf(textView)).getName(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

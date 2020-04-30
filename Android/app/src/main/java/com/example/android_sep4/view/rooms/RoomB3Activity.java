package com.example.android_sep4.view.rooms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_sep4.R;
import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.viewmodel.rooms.RoomB3ViewModel;

import java.util.ArrayList;

public class RoomB3Activity extends AppCompatActivity {
    private final static int ROOM_CAPACITY = 12;
    private RoomB3ViewModel roomB3ViewModel;
    private ArrayList<Artwork> artworksInRoom = new ArrayList<>();
    private ArrayList<TextView> textViews = new ArrayList<>();
    private TextView place_holder_1, place_holder_2, place_holder_3 ,
            place_holder_4 ,place_holder_5 , place_holder_6 ,
            place_holder_7 , place_holder_8, place_holder_9 ,
            place_holder_10, place_holder_11, place_holder_12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_b3);
        findViews();
        setTextViews();
        setViewModel();
        viewArtworks();
    }

    private void setViewModel() {
        roomB3ViewModel = new ViewModelProvider(this).get(RoomB3ViewModel.class);
        roomB3ViewModel.init("B3");

        artworksInRoom = roomB3ViewModel.getArtworksFromRoom().getValue();

        for (int i = 0; i < ROOM_CAPACITY - 1; i++) {
            textViews.get(i).setText(artworksInRoom.get(i).getName());
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
        for(final TextView textView : textViews) {
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(RoomB3Activity.this, ArtworkDetails.class);
                    intent.putExtra("Artwork", artworksInRoom.get(textViews.indexOf(textView)));
                    startActivity(intent);

                    Toast.makeText(getApplicationContext(), "This is " + artworksInRoom.get(textViews.indexOf(textView)).getName(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

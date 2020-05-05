package com.example.android_sep4.view.rooms;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.android_sep4.R;
import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.viewmodel.rooms.RoomB4ViewModel;

import java.util.ArrayList;

public class RoomB4Activity extends AppCompatActivity {
    private final static int ROOM_CAPACITY = 6;
    private RoomB4ViewModel roomB4ViewModel;
    private ArrayList<Artwork> artworksInRoom = new ArrayList<>();
    private ArrayList<TextView> textViews = new ArrayList<>();
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
        roomB4ViewModel = new ViewModelProvider(this).get(RoomB4ViewModel.class);
        roomB4ViewModel.init("B4");

        artworksInRoom = roomB4ViewModel.getArtworksFromRoom().getValue();

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
        for (final TextView textView : textViews) {
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(RoomB4Activity.this, ArtworkDetails.class);
                    intent.putExtra("Artwork", artworksInRoom.get(textViews.indexOf(textView)));
                    startActivity(intent);

                    Toast.makeText(getApplicationContext(), "This is " + artworksInRoom.get(textViews.indexOf(textView)).getName(), Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}

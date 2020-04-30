package com.example.android_sep4.view.rooms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_sep4.R;
import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.viewmodel.artwork.ArtworksTabViewModel;
import com.example.android_sep4.viewmodel.rooms.RoomA1ViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RoomA1Activity extends AppCompatActivity {
    private RoomA1ViewModel roomA1ViewModel;
    private ArrayList<Artwork> artworksInRoom = new ArrayList<>();
    private ArrayList<TextView> textViews = new ArrayList<>();
    private TextView place_holder_1, place_holder_2, place_holder_3 ,
            place_holder_4 ,place_holder_5 , place_holder_6 ,
            place_holder_7 , place_holder_8 , place_holder_9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_a1);
        findViews();
        setTextViews();
        setViewModel();
        setClickListeners();
    }

    private void setViewModel() {
        roomA1ViewModel = new ViewModelProvider(this).get(RoomA1ViewModel.class);
        roomA1ViewModel.init("A1");

        artworksInRoom = roomA1ViewModel.getArtworksFromRoom().getValue();
        System.out.println(Arrays.asList(artworksInRoom));

        for(int i = 0; i < 8; i++) {
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
        System.out.println(Arrays.asList(textViews.toString()));

    }

    private void setClickListeners() {
        viewArtwork1();
        viewArtwork2();
        viewArtwork3();
        viewArtwork4();
        viewArtwork5();
        viewArtwork6();
        viewArtwork7();
        viewArtwork8();
        viewArtwork9();
    }

    public void viewArtwork1() {
        place_holder_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RoomA1Activity.this, PopUp.class);

                intent.putExtra("Artwork", artworksInRoom.get(0));
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "This is artwork " + artworksInRoom.get(0).getName() , Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void viewArtwork2() {
        place_holder_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RoomA1Activity.this, PopUp.class);
                intent.putExtra("Artwork", artworksInRoom.get(1));
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "This is artwork " + artworksInRoom.get(1).getName() , Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void viewArtwork3() {
        place_holder_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RoomA1Activity.this, PopUp.class);
                intent.putExtra("Artwork", artworksInRoom.get(2));
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "This is artwork " + artworksInRoom.get(2).getName() , Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void viewArtwork4() {
        place_holder_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RoomA1Activity.this, PopUp.class);
                intent.putExtra("Artwork", artworksInRoom.get(3));
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "This is artwork " + artworksInRoom.get(3).getName() , Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void viewArtwork5() {
        place_holder_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RoomA1Activity.this, PopUp.class);
                intent.putExtra("Artwork", artworksInRoom.get(4));
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "This is artwork " + artworksInRoom.get(4).getName() , Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void viewArtwork6() {
        place_holder_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RoomA1Activity.this, PopUp.class);
                intent.putExtra("Artwork", artworksInRoom.get(5));
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "This is artwork " + artworksInRoom.get(5).getName() , Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void viewArtwork7() {
        place_holder_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RoomA1Activity.this, PopUp.class);
                intent.putExtra("Artwork", artworksInRoom.get(6));
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "This is artwork " + artworksInRoom.get(6).getName() , Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void viewArtwork8() {
        place_holder_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RoomA1Activity.this, PopUp.class);
                intent.putExtra("Artwork", artworksInRoom.get(7));
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "This is artwork " + artworksInRoom.get(7).getName() , Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void viewArtwork9() {
        place_holder_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RoomA1Activity.this, PopUp.class);
                intent.putExtra("Artwork", artworksInRoom.get(8));
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "This is artwork " + artworksInRoom.get(8).getName() , Toast.LENGTH_SHORT).show();

            }
        });
    }




}

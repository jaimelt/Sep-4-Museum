package com.example.android_sep4.view.rooms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android_sep4.R;

public class RoomB4Activity extends AppCompatActivity {

    TextView place_holder_1, place_holder_2, place_holder_3 ,
            place_holder_4 ,place_holder_5 , place_holder_6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_b4);
        findViews();
        setClickListeners();
    }

    public void findViews() {
        place_holder_1 = findViewById(R.id.artwork_place_1);
        place_holder_2 = findViewById(R.id.artwork_place_2);
        place_holder_3 = findViewById(R.id.artwork_place_3);
        place_holder_4 = findViewById(R.id.artwork_place_4);
        place_holder_5 = findViewById(R.id.artwork_place_5);
        place_holder_6 = findViewById(R.id.artwork_place_6);
    }

    private void setClickListeners() {
        viewArtwork1();
        viewArtwork2();
        viewArtwork3();
        viewArtwork4();
        viewArtwork5();
        viewArtwork6();
    }

    public void viewArtwork1() {
        place_holder_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RoomB4Activity.this, PopUp.class);
                startActivity(intent);
            }
        });
    }

    public void viewArtwork2() {
        place_holder_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RoomB4Activity.this, PopUp.class);
                startActivity(intent);
            }
        });
    }

    public void viewArtwork3() {
        place_holder_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RoomB4Activity.this, PopUp.class);
                startActivity(intent);
            }
        });
    }

    public void viewArtwork4() {
        place_holder_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RoomB4Activity.this, PopUp.class);
                startActivity(intent);
            }
        });
    }

    public void viewArtwork5() {
        place_holder_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RoomB4Activity.this, PopUp.class);
                startActivity(intent);
            }
        });
    }

    public void viewArtwork6() {
        place_holder_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RoomB4Activity.this, PopUp.class);
                startActivity(intent);
            }
        });
    }
}

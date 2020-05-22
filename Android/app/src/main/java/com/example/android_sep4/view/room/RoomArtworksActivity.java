package com.example.android_sep4.view.room;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_sep4.R;
import com.example.android_sep4.adapters.ArtworksInRoomsAdapter;
import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.viewmodel.ViewModelFactory;
import com.example.android_sep4.viewmodel.roomList.RoomArtworksViewModel;

import java.util.ArrayList;

public class RoomArtworksActivity extends AppCompatActivity {
    private static final String TAG = "RoomArtworksActivity";
    private RoomArtworksViewModel roomArtworksViewModel;
    private ArtworksInRoomsAdapter adapter;
    private String locationCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_artworks);
        setViewModel();
        Bundle bundle = getIntent().getExtras();
        locationCode = bundle.getString("locationCode");
    }

    private void setViewModel() {
        roomArtworksViewModel = new ViewModelProvider(this, new ViewModelFactory(this.getApplication(), locationCode)).get(RoomArtworksViewModel.class);
        initRecycleView();

        roomArtworksViewModel.getArtworksFromRoom(locationCode).observe(this, artworks -> {
            adapter.setArtworksInRoom(artworks);
            adapter.notifyDataSetChanged();
        });
    }

    private void initRecycleView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_viewArtworkList);
        adapter = new ArtworksInRoomsAdapter(roomArtworksViewModel.getArtworksFromRoom(locationCode).getValue(), this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
    }
}

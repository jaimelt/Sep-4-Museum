package com.example.android_sep4.view.room;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_sep4.R;
import com.example.android_sep4.adapters.RecyclerViewAdapterRoomArtworks;
import com.example.android_sep4.viewmodel.roomList.RoomArtworksViewModel;

public class RoomArtworksActivity extends AppCompatActivity {
    private static final String TAG = "RoomArtworksActivity";
    private RoomArtworksViewModel roomArtworksViewModel;
    private RecyclerViewAdapterRoomArtworks adapter;
    private String locationCod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_artworks);
        setViewModel();
        Bundle bundle = getIntent().getExtras();
        locationCod = bundle.getString("locationCode");
    }

    private void setViewModel() {
        roomArtworksViewModel = new ViewModelProvider(this).get(RoomArtworksViewModel.class);
        roomArtworksViewModel.init(locationCod);
        initRecycleView();
    }

    private void initRecycleView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_viewArtworkList);
        adapter = new RecyclerViewAdapterRoomArtworks(roomArtworksViewModel.getArtworksFromRoom().getValue(), this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
    }
}

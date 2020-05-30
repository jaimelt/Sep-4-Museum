package com.example.android_sep4.view.room;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

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
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_artworks);
        Bundle bundle = getIntent().getExtras();
        locationCode = bundle.getString("locationCode");

        setViewModel();
    }

    private void setViewModel() {
        roomArtworksViewModel = new ViewModelProvider(this, new ViewModelFactory(this.getApplication())).get(RoomArtworksViewModel.class);
        initRecycleView();

        roomArtworksViewModel.getArtworksFromRoom(locationCode).observe(this, artworks -> {
            adapter.setArtworksInRoom(artworks);
            adapter.notifyDataSetChanged();
        });

        roomArtworksViewModel.getIsLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean) {
                    progressBar.setVisibility(View.VISIBLE);
                } else progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void initRecycleView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_viewArtworkList);
        adapter = new ArtworksInRoomsAdapter(this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        progressBar = findViewById(R.id.progress_bar_artworks_in_room);
    }
}

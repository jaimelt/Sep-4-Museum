package com.example.android_sep4.view.museum.storage;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_sep4.R;
import com.example.android_sep4.adapters.StorageAdapter;
import com.example.android_sep4.viewmodel.roomList.ArtworksStorageViewModel;

public class StorageActivity extends AppCompatActivity implements StorageAdapter.OnListItemClickListener {
    private static final String TAG = "StorageActivity";
    private ArtworksStorageViewModel artworksStorageViewModel;
    private StorageAdapter adapter;
    private Drawable deleteIcon;
    private final static String LOCATION_CODE = "storage";
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        Toolbar toolbar = findViewById(R.id.storage_toolbar);
        setSupportActionBar(toolbar);
        setTitle("Storage");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        progressBar = findViewById(R.id.progress_bar_storage);
        deleteIcon = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_delete);
        setViewModel();
    }

    private void setViewModel() {
        artworksStorageViewModel = new ViewModelProvider(this).get(ArtworksStorageViewModel.class);
        initRecycleView();
        artworksStorageViewModel.getArtworksFromStorage(LOCATION_CODE).observe(this, artworks -> {
            adapter.setArtworks(artworks);
            adapter.notifyDataSetChanged();
        });

        artworksStorageViewModel.getIsLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    progressBar.setVisibility(View.VISIBLE);
                } else progressBar.setVisibility(View.GONE);
            }
        });

    }

    @Override
    //finish on activity when up navigation is clicked - animation slide to right
    public boolean onSupportNavigateUp() {
        onBackPressed();
        finish();
        return true;
    }

    private void initRecycleView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view_storage);
        adapter = new StorageAdapter(this, this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
    }

    public void onListItemClick(int clickedItemIndex) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this, android.R.style.Theme_DeviceDefault_Dialog_Alert);
        alertDialog.setTitle("Delete artwork");
        alertDialog.setMessage("Are you sure about deleting this artwork?");
        alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                artworksStorageViewModel.deleteArtwork(clickedItemIndex);
                adapter.deleteArtwork(clickedItemIndex);
            }
        });
        alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        alertDialog.show();
    }


    @Override
    protected void onPause() {
        super.onPause();
        artworksStorageViewModel.getArtworksFromStorage(LOCATION_CODE).removeObservers(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        artworksStorageViewModel.getArtworksFromStorage(LOCATION_CODE).observe(this, artworks -> {
            adapter.setArtworks(artworks);
            adapter.notifyDataSetChanged();
        });
    }
}

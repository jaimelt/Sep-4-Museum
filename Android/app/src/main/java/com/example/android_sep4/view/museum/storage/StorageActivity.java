package com.example.android_sep4.view.museum.storage;

import android.app.AlertDialog;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_sep4.R;
import com.example.android_sep4.adapters.StorageAdapter;
import com.example.android_sep4.viewmodel.museum.storage.StorageViewModel;

public class StorageActivity extends AppCompatActivity implements StorageAdapter.OnListItemClickListener {
    private static final String TAG = "StorageActivity";
    private final static String LOCATION_CODE = "storage";
    private StorageViewModel storageViewModel;
    private StorageAdapter adapter;
    private Drawable deleteIcon;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);

        setToolbar();


        progressBar = findViewById(R.id.progress_bar_storage);
        deleteIcon = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_delete);
        setViewModel();
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.storage_toolbar);
        setSupportActionBar(toolbar);
        setTitle("Storage");
        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setViewModel() {
        storageViewModel = new ViewModelProvider(this).get(StorageViewModel.class);
        initRecycleView();
        storageViewModel.getArtworksFromStorage(LOCATION_CODE).observe(this, artworks -> {
            adapter.setArtworks(artworks);
            adapter.notifyDataSetChanged();
        });

        storageViewModel.getIsLoading().observe(this, aBoolean -> {
            if (aBoolean) {
                progressBar.setVisibility(View.VISIBLE);
            } else progressBar.setVisibility(View.GONE);
        });

    }

    @Override
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

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP_MR1)
    public void onListItemClick(int clickedItemIndex) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this, android.R.style.Theme_DeviceDefault_Dialog_Alert);
        alertDialog.setTitle("Delete artwork");
        alertDialog.setMessage("Are you sure about deleting this artwork?");
        alertDialog.setPositiveButton("Yes", (dialog, which) -> {
            storageViewModel.deleteArtwork(clickedItemIndex);
            adapter.deleteArtwork(clickedItemIndex);
        });
        alertDialog.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
        alertDialog.show();
    }


    @Override
    protected void onPause() {
        super.onPause();
        storageViewModel.getArtworksFromStorage(LOCATION_CODE).removeObservers(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        storageViewModel.getArtworksFromStorage(LOCATION_CODE).observe(this, artworks -> {
            adapter.setArtworks(artworks);
            adapter.notifyDataSetChanged();
        });
    }
}

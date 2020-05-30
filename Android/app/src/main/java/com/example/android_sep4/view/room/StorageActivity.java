package com.example.android_sep4.view.room;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
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
                if(aBoolean) {
                    progressBar.setVisibility(View.VISIBLE);
                } else progressBar.setVisibility(View.GONE);
            }
        });

    }

    private void initRecycleView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view_storage);
        adapter = new StorageAdapter(this, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
    }
    public void onListItemClick(int clickedItemIndex) {

    }


    private ItemTouchHelper.Callback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            artworksStorageViewModel.positionToId(viewHolder.getAdapterPosition());
            adapter.deleteArtwork(viewHolder.getAdapterPosition());
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
            View itemView = viewHolder.itemView;
            final ColorDrawable background = new ColorDrawable(Color.parseColor("#BF1633"));
            background.setBounds(itemView.getLeft(), itemView.getTop(), ((int) dX), itemView.getBottom());
            background.draw(c);

            int iconMargin = (itemView.getHeight() - deleteIcon.getIntrinsicHeight()) / 2;
            deleteIcon.setBounds(itemView.getLeft() + iconMargin, itemView.getTop() + iconMargin, itemView.getLeft() + iconMargin + deleteIcon.getIntrinsicWidth(), itemView.getBottom() - iconMargin);
            deleteIcon.draw(c);

            super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        }
    };

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

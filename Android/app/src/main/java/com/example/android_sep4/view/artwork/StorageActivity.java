package com.example.android_sep4.view.artwork;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_sep4.R;
import com.example.android_sep4.adapters.StorageAdapter;
import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.viewmodel.artwork.ArtworksStorageViewModel;
import com.google.android.material.snackbar.Snackbar;

public class StorageActivity extends AppCompatActivity implements StorageAdapter.OnListItemClickListener {
    private static final String TAG = "StorageActivity";
    private ArtworksStorageViewModel artworksStorageViewModel;
    private StorageAdapter adapter;
    private int removedPosition = 0;
    private Artwork removedArtwork;
    private Drawable deleteIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_storage);
        deleteIcon = ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_delete);
        setViewModel();
    }

    private void setViewModel() {
        artworksStorageViewModel = new ViewModelProvider(this).get(ArtworksStorageViewModel.class);

        artworksStorageViewModel.getArtworks().observe(this, artworks -> {
            adapter.setArtworks(artworks);
            adapter.notifyDataSetChanged();
        });
        initRecycleView();
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
            //Caching deleted artwork
            removedPosition = viewHolder.getAdapterPosition();
            removedArtwork = artworksStorageViewModel.getArtwork(viewHolder.getAdapterPosition());
            artworksStorageViewModel.removeArtwork(viewHolder.getAdapterPosition());
            adapter.notifyItemRemoved(viewHolder.getAdapterPosition());


            Snackbar snackbar = Snackbar.make(viewHolder.itemView, "1 artwork item was deleted", Snackbar.LENGTH_LONG).setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    artworksStorageViewModel.addArtwork(removedPosition, removedArtwork);
                    adapter.notifyItemInserted(removedPosition);
                }
            });
            snackbar.show();
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

}

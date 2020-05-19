package com.example.android_sep4.view.artwork;


import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_sep4.R;
import com.example.android_sep4.adapters.RecyclerViewAdapterArtworks;
import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.viewmodel.artwork.ArtworksTabViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class ArtworksTab extends Fragment implements RecyclerViewAdapterArtworks.OnListItemClickListener {
    private ArtworksTabViewModel artworksTabViewModel;
    private RecyclerViewAdapterArtworks adapter;
    private int removedPosition = 0;
    private Artwork removedArtwork;
    private Drawable deleteIcon;
    static final String EXTRA_ARTWORK = "Artwork name";

    public ArtworksTab() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setViewModel();
        setHasOptionsMenu(true);
        deleteIcon = ContextCompat.getDrawable(Objects.requireNonNull(this.getContext()), R.drawable.ic_delete);
        return inflater.inflate(R.layout.fragment_artworks_tab, container, false);

    }

    @Override
    public void onResume() {
        super.onResume();

        //update list when getting back from add activity
        adapter.notifyDataSetChanged();
    }

    private void setViewModel() {
        artworksTabViewModel = new ViewModelProvider(this).get(ArtworksTabViewModel.class);

        artworksTabViewModel.getArtworks().observe(getViewLifecycleOwner(), new Observer<List<Artwork>>() {

            @Override
            public void onChanged(List<Artwork> artworks) {
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        adapter = new RecyclerViewAdapterArtworks(artworksTabViewModel.getArtworks().getValue(), this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);
        onClickListenerFAB(view);
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
            removedArtwork = artworksTabViewModel.getArtwork(viewHolder.getAdapterPosition());

            artworksTabViewModel.removeArtwork(viewHolder.getAdapterPosition());
            adapter.notifyItemRemoved(viewHolder.getAdapterPosition());

            Snackbar snackbar = Snackbar.make(viewHolder.itemView, "1 artwork item was deleted", Snackbar.LENGTH_LONG).setAction("UNDO", new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    artworksTabViewModel.addArtwork(removedPosition, removedArtwork);
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

    private void onClickListenerFAB(View view) {
        FloatingActionButton myFab = view.findViewById(R.id.fab);
        myFab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewArtworkActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onListItemClick(int clickedItemIndex) {
        Intent intent = new Intent(getActivity(), EditArtworkActivity.class);
        intent.putExtra(EXTRA_ARTWORK, clickedItemIndex);
        startActivity(intent);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.toolbar_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            //To have real time filtering
            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }
}

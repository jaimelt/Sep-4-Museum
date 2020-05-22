package com.example.android_sep4.view.artwork;


import android.content.Intent;
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
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_sep4.R;
import com.example.android_sep4.adapters.ArtworksAdapter;
import com.example.android_sep4.view.ManageAccountsActivity;
import com.example.android_sep4.view.SettingsActivity;
import com.example.android_sep4.viewmodel.artwork.ArtworksTabViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class ArtworksTab extends Fragment implements ArtworksAdapter.OnListItemClickListener {
    private ArtworksTabViewModel artworksTabViewModel;
    private ArtworksAdapter adapter;
    static final String EXTRA_ARTWORK = "Artwork name";

    public ArtworksTab() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setViewModel();
        setHasOptionsMenu(true);
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

        artworksTabViewModel.getArtworks().observe(getViewLifecycleOwner(), artworks -> {
            adapter.setArtworks(artworks);
            adapter.notifyDataSetChanged();
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        adapter = new ArtworksAdapter(getActivity(), this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);
        onClickListenerFAB(view);
    }


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
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.toolbar_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        MenuItem manageAccountsItem = menu.findItem(R.id.manageAccounts);
        MenuItem settingsItem = menu.findItem(R.id.settings);
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

        manageAccountsItem.setOnMenuItemClickListener(item -> {
            startActivity(new Intent(getContext(), ManageAccountsActivity.class));
            return true;
        });

        settingsItem.setOnMenuItemClickListener(item -> {
            startActivity(new Intent(getContext(), SettingsActivity.class));
            return true;
        });

    }
}

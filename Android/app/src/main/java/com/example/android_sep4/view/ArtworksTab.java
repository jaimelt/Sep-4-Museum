package com.example.android_sep4.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android_sep4.R;
import com.example.android_sep4.adapters.RecyclerViewAdapter;
import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.viewmodel.ArtworksTabViewModel;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ArtworksTab extends Fragment {
    private ArrayList<String> artworksNames;
    private ArtworksTabViewModel artworksTabViewModel;
    private RecyclerViewAdapter adapter;
    public ArtworksTab() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setViewModel();

        return inflater.inflate(R.layout.fragment_artworks_tab, container, false);
    }

    public void setViewModel() {
        artworksTabViewModel = ViewModelProviders.of(this).get(ArtworksTabViewModel.class);
        artworksTabViewModel.init();

        artworksTabViewModel.getArtworks().observe(this, new Observer<List<Artwork>>() {
            @Override
            public void onChanged(List<Artwork> artworks) {
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        adapter = new RecyclerViewAdapter(artworksTabViewModel.getArtworks().getValue());
        recyclerView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);
    }
}

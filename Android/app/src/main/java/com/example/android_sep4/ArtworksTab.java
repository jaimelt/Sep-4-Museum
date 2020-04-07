package com.example.android_sep4;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ArtworksTab extends Fragment {
    private ArrayList<String> artworksNames = new ArrayList<>();
    public ArtworksTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initArtworkNames();
        return inflater.inflate(R.layout.fragment_artworks_tab, container, false);

    }

    private void initArtworkNames() {

        artworksNames.add("Artwork 1");
        artworksNames.add("Artwork 2");
        artworksNames.add("Artwork 3");
        artworksNames.add("Artwork 4");
        artworksNames.add("Artwork 5");
        artworksNames.add("Artwork 6");
        artworksNames.add("Artwork 7");
        artworksNames.add("Artwork 8");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(artworksNames);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);
    }

//    private void initRecyclerView() {
//        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
//        RecyclerViewAdapter adapter = new RecyclerViewAdapter(artworksNames);
//        recyclerView.setAdapter(adapter);
//        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
//        recyclerView.setLayoutManager(llm);
//    }
}

package com.example.android_sep4.view;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android_sep4.R;
import com.example.android_sep4.adapters.RecyclerViewAdapter;
import com.example.android_sep4.adapters.RecyclerViewAdapterRooms;
import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.model.Room;
import com.example.android_sep4.viewmodel.RoomsTabViewModel;

import java.util.List;
import java.util.Objects;



/**
 * A simple {@link Fragment} subclass.
 */
public class RoomsTab extends Fragment {


    private RoomsTabViewModel roomsTabViewModel;
    private RecyclerViewAdapterRooms adapter;
    private int removedPosition = 0;

    public RoomsTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setViewModel();
        return inflater.inflate(R.layout.fragment_rooms_tab, container, false);
    }

    public void setViewModel() {
        roomsTabViewModel = new ViewModelProvider(this).get(RoomsTabViewModel.class);
        roomsTabViewModel.init();

        roomsTabViewModel.getRooms().observe(this, new Observer<List<Room>>() {
            @Override
            public void onChanged(List<Room> rooms) {
                adapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_viewRoom);
        adapter = new RecyclerViewAdapterRooms(roomsTabViewModel.getRooms().getValue());
        recyclerView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);
    }

}

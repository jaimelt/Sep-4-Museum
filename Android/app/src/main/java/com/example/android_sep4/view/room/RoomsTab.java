package com.example.android_sep4.view.room;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_sep4.R;
import com.example.android_sep4.adapters.RecyclerViewAdapterRooms;
import com.example.android_sep4.model.Room;
import com.example.android_sep4.view.ManageAccountsActivity;
import com.example.android_sep4.viewmodel.roomList.RoomsTabViewModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class RoomsTab extends Fragment {
    private RoomsTabViewModel roomsTabViewModel;
    private RecyclerViewAdapterRooms adapter;
    static final String EXTRA_ROOM = "Room Name";


    public RoomsTab() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setViewModel();
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_rooms_tab, container, false);
    }

    public void setViewModel() {
        roomsTabViewModel = new ViewModelProvider(this).get(RoomsTabViewModel.class);

        roomsTabViewModel.getRooms().observe(getViewLifecycleOwner(), (Observer<List<Room>>) rooms -> adapter.notifyDataSetChanged());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView recyclerView = view.findViewById(R.id.recycler_viewRoom);
        adapter = new RecyclerViewAdapterRooms(roomsTabViewModel.getRooms().getValue());
        recyclerView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.toolbar_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        MenuItem manageItem = menu.findItem(R.id.manageAccounts);
        MenuItem settingsItem = menu.findItem(R.id.settings);
        searchItem.setVisible(false);
        manageItem.setOnMenuItemClickListener(item -> {
            startActivity(new Intent(getContext(), ManageAccountsActivity.class));
            return true;
        });

    }

}

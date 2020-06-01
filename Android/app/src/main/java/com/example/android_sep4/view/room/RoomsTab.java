package com.example.android_sep4.view.room;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android_sep4.R;
import com.example.android_sep4.adapters.RoomsAdapter;
import com.example.android_sep4.view.AccountActivity;
import com.example.android_sep4.view.settings.SettingsActivity;
import com.example.android_sep4.view.VisitorsActivity;
import com.example.android_sep4.viewmodel.roomList.RoomsTabViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class RoomsTab extends Fragment implements SharedPreferences.OnSharedPreferenceChangeListener {
    static final String EXTRA_ROOM = "Room Name";
    private RoomsTabViewModel roomsTabViewModel;
    private RoomsAdapter adapter;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private SharedPreferences sharedPreferences;

    public RoomsTab() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setViewModel();
        setHasOptionsMenu(true);
        setSharePreferenceChangeListener();
        return inflater.inflate(R.layout.fragment_rooms_tab, container, false);
    }

    private void setSharePreferenceChangeListener() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    public void setViewModel() {
        roomsTabViewModel = new ViewModelProvider(this).get(RoomsTabViewModel.class);

        roomsTabViewModel.getRooms().observe(getViewLifecycleOwner(), rooms -> {
            adapter.setRooms(rooms);
        });
      /*  roomsTabViewModel.getLiveMeasurements(room).observe(getViewLifecycleOwner(), rooms -> {
            adapter.setRooms(rooms);
        });*/
        roomsTabViewModel.getIsLoading().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean) {
                    progressBar.setVisibility(View.VISIBLE);
                } else progressBar.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recycler_viewRoom);
        adapter = new RoomsAdapter(getContext());
        recyclerView.setAdapter(adapter);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(llm);
        progressBar = view.findViewById(R.id.progress_bar_rooms);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.toolbar_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.search);
        MenuItem manageItem = menu.findItem(R.id.manageAccounts);
        MenuItem settingsItem = menu.findItem(R.id.settings);
        MenuItem visitorsItem = menu.findItem(R.id.visitors);
        searchItem.setVisible(false);
        manageItem.setOnMenuItemClickListener(item -> {
            startActivity(new Intent(getContext(), AccountActivity.class));
            return true;
        });

        settingsItem.setOnMenuItemClickListener(item -> {
            startActivity(new Intent(getContext(), SettingsActivity.class));
            return true;
        });

        visitorsItem.setOnMenuItemClickListener(item -> {
            startActivity(new Intent(getContext(), VisitorsActivity.class));
            return true;
        });

    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Toast.makeText(getContext(), "IT CHANGED", Toast.LENGTH_SHORT).show();
        if (key.equals(getString(R.string.pref_temperature_key))) {
            roomsTabViewModel.getRooms().observe(getViewLifecycleOwner(), rooms -> {
                adapter.setRooms(rooms);
            });
        }
    }
}

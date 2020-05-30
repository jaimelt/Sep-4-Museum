package com.example.android_sep4.view.museum;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.android_sep4.R;
import com.example.android_sep4.view.AccountActivity;
import com.example.android_sep4.view.settings.SettingsActivity;
import com.example.android_sep4.view.VisitorsActivity;
import com.example.android_sep4.view.room.StorageActivity;
import com.example.android_sep4.view.museum.rooms.RoomA1Activity;
import com.example.android_sep4.view.museum.rooms.RoomA2Activity;
import com.example.android_sep4.view.museum.rooms.RoomA3Activity;
import com.example.android_sep4.view.museum.rooms.RoomB1Activity;
import com.example.android_sep4.view.museum.rooms.RoomB2Activity;
import com.example.android_sep4.view.museum.rooms.RoomB3Activity;
import com.example.android_sep4.view.museum.rooms.RoomB4Activity;

/**
 * A simple {@link Fragment} subclass.
 */
public class MuseumTab extends Fragment {

    private TextView a1, a2, a3, b1, b2, b3, b4;
    private Button storage;

    public MuseumTab() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_museum_tab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        storage = view.findViewById(R.id.storage);
        a1 = view.findViewById(R.id.room_a1);
        a2 = view.findViewById(R.id.room_a2);
        a3 = view.findViewById(R.id.room_a3);
        b1 = view.findViewById(R.id.room_b1);
        b2 = view.findViewById(R.id.room_b2);
        b3 = view.findViewById(R.id.room_b3);
        b4 = view.findViewById(R.id.room_b4);

        setUpClickListeners();
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

    private void setUpClickListeners() {
        openRoomA1ClickListener();
        openRoomA2ClickListener();
        openRoomA3ClickListener();
        openRoomB1ClickListener();
        openRoomB2ClickListener();
        openRoomB3ClickListener();
        openRoomB4ClickListener();
        viewStorageClickListener();
    }

    private void viewStorageClickListener() {
        storage.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), StorageActivity.class);
            startActivity(intent);
        });
    }

    private void openRoomA1ClickListener() {
        a1.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), RoomA1Activity.class);
            startActivity(intent);
        });
    }

    private void openRoomA2ClickListener() {
        a2.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), RoomA2Activity.class);
            startActivity(intent);
        });
    }

    private void openRoomA3ClickListener() {
        a3.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), RoomA3Activity.class);
            startActivity(intent);
        });
    }

    private void openRoomB1ClickListener() {
        b1.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), RoomB1Activity.class);
            startActivity(intent);
        });
    }

    private void openRoomB2ClickListener() {
        b2.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), RoomB2Activity.class);
            startActivity(intent);
        });
    }

    private void openRoomB3ClickListener() {
        b3.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), RoomB3Activity.class);
            startActivity(intent);
        });
    }

    private void openRoomB4ClickListener() {
        b4.setOnClickListener(view -> {
            Intent intent = new Intent(getActivity(), RoomB4Activity.class);
            startActivity(intent);

        });
    }

}

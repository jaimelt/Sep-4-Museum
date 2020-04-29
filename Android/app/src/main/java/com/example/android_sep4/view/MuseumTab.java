package com.example.android_sep4.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android_sep4.R;
/**
 * A simple {@link Fragment} subclass.
 */
public class MuseumTab extends Fragment {

    private TextView a1, a2, a3, b1, b2, b3, b4;

    public MuseumTab() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_museum_tab, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        a1 = view.findViewById(R.id.room_a1);
        a2 = view.findViewById(R.id.room_a2);
        a3 = view.findViewById(R.id.room_a3);
        b1 = view.findViewById(R.id.room_b1);
        b2 = view.findViewById(R.id.room_b2);
        b3 = view.findViewById(R.id.room_b3);
        b4 = view.findViewById(R.id.room_b4);
    }


}

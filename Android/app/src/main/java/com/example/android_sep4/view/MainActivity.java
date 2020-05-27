package com.example.android_sep4.view;

import android.os.Bundle;
import android.view.Menu;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import com.example.android_sep4.R;
import com.example.android_sep4.adapters.PageAdapter;
import com.example.android_sep4.view.artwork.ArtworksTab;
import com.example.android_sep4.view.museum.MuseumTab;
import com.example.android_sep4.view.room.RoomsTab;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private LinearLayout viewPager;
    private MuseumTab museumTab;
    private RoomsTab roomsTab;
    private ArtworksTab artworksTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        museumTab = new MuseumTab();
        artworksTab = new ArtworksTab();
        roomsTab = new RoomsTab();

        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewpager);

        setFragment(museumTab);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    setFragment(museumTab);
                } else if (tab.getPosition() == 1) {
                    setFragment(artworksTab);
                } else if (tab.getPosition() == 2) {
                    setFragment(roomsTab);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.viewpager, fragment);
        fragmentTransaction.commit();
    }


}

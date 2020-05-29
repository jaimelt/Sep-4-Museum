package com.example.android_sep4.view;

import android.os.Bundle;
import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.example.android_sep4.R;
import com.example.android_sep4.view.artwork.ArtworksTab;
import com.example.android_sep4.view.museum.MuseumTab;
import com.example.android_sep4.view.room.RoomsTab;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private static final String CURRENT_POSITION_KEY = "Position";
    private static final String CURRENT_POSITION2_KEY = "PositionTab";
    private ViewPager viewPager;
    private MuseumTab museumTab;
    private RoomsTab roomsTab;
    private ArtworksTab artworksTab;
    private int position;

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

        if (savedInstanceState != null) {
            int currentPos = savedInstanceState.getInt(CURRENT_POSITION_KEY);
            int currentTabPos = savedInstanceState.getInt(CURRENT_POSITION2_KEY);
            tabLayout.getTabAt(currentTabPos).select();
            viewPager.setCurrentItem(currentPos);
        }
        else {
            setFragment(museumTab);
        }


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0) {
                    setFragment(museumTab);
                    position = 0;
                } else if (tab.getPosition() == 1) {
                    setFragment(artworksTab);
                    position = 1;
                } else if (tab.getPosition() == 2) {
                    setFragment(roomsTab);
                    position = 2;
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



    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        savedInstanceState.putInt(CURRENT_POSITION_KEY, viewPager.getCurrentItem());
        savedInstanceState.putInt(CURRENT_POSITION2_KEY, position);

        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }


}

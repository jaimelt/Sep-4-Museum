package com.example.android_sep4.view;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.android_sep4.R;
import com.example.android_sep4.model.Artwork;
import com.example.android_sep4.view.artwork.ArtworksTab;
import com.example.android_sep4.view.museum.MuseumTab;
import com.example.android_sep4.view.room.RoomsTab;
import com.example.android_sep4.viewmodel.NotificationsViewModel;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String CURRENT_POSITION_KEY = "Position";
    private static final String CURRENT_POSITION2_KEY = "PositionTab";
    private NotificationsViewModel notificationsViewModel;
    private ArrayList<Artwork> artworksInDanger = new ArrayList<>();
    private ViewPager viewPager;
    private MuseumTab museumTab;
    private RoomsTab roomsTab;
    private ArtworksTab artworksTab;
    private TabLayout tabLayout;
    private boolean doubleBackToExitPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setViewModel();
        setContentView(R.layout.activity_main);

        museumTab = new MuseumTab();
        artworksTab = new ArtworksTab();
        roomsTab = new RoomsTab();

        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);

        if (savedInstanceState != null) {
            int currentPos = savedInstanceState.getInt(CURRENT_POSITION_KEY);
            int currentTabPos = savedInstanceState.getInt(CURRENT_POSITION2_KEY);
            tabLayout.getTabAt(currentTabPos).select();
            viewPager.setCurrentItem(currentPos);
        } else {
            setFragment(museumTab);
        }


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
    protected void onResume() {
        super.onResume();
        this.doubleBackToExitPressedOnce = false;
    }

    private void notification() {
        createNotificationChannel();
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "museum")
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("Artworks are in danger !")
                .setContentText("Check artworks in rooms")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        if (!(artworksInDanger.isEmpty())) {
            notificationManager.notify(100, builder.build());
        }
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "studentChannel";
            String description = "Channel for room";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("museum", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void setViewModel() {
        notificationsViewModel = new ViewModelProvider(this).get(NotificationsViewModel.class);
        LiveData<ArrayList<Artwork>> liveData = notificationsViewModel.getArtworksInDanger();
        liveData.observe(this, artworks -> {
            liveData.removeObservers(this);
            artworksInDanger.addAll(artworks);

            LiveData<Boolean> booleanLiveData = notificationsViewModel.getIsLoaded();
            booleanLiveData.observe(this, aBoolean -> {
                if (aBoolean) {
                    notification();
                }
                booleanLiveData.removeObservers(this);
            });
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, R.string.exit_back_twice, Toast.LENGTH_SHORT).show();
    }


    public void onSaveInstanceState(Bundle savedInstanceState) {
        int position = tabLayout.getSelectedTabPosition();
        savedInstanceState.putInt(CURRENT_POSITION_KEY, viewPager.getCurrentItem());
        savedInstanceState.putInt(CURRENT_POSITION2_KEY, position);

        super.onSaveInstanceState(savedInstanceState);
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

        fragmentTransaction.replace(R.id.frame_container, fragment);
        fragmentTransaction.commit();
    }


}

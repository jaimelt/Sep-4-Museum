package com.example.android_sep4.view.settings;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;

import com.example.android_sep4.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        setFragment();
        setToolbar();

    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.settings_toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setTitle("Settings");
    }

    private void setFragment() {
        SettingsFragment settingsFragment = new SettingsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.activity_settings, settingsFragment).addToBackStack(null);
        transaction.commit();
    }

    @Override
    public boolean onSupportNavigateUp() {
        if (getSupportFragmentManager().getBackStackEntryCount() <= 1) {
            getSupportFragmentManager().popBackStack();
            super.onBackPressed();
            finish();
            return true;
        }
        setTitle("Settings");
        getSupportFragmentManager().popBackStack();
        return true;
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() <= 1) {
            getSupportFragmentManager().popBackStack();
            super.onBackPressed();
            finish();
        }
        setTitle("Settings");
        getSupportFragmentManager().popBackStack();
        setFragment();
        super.onBackPressed();
    }
}

package com.example.android_sep4.view.settings;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import com.example.android_sep4.R;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref_settings);
    }
}

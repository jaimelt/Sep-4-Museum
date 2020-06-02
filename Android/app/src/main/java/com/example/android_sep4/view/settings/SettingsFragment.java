package com.example.android_sep4.view.settings;

import android.os.Bundle;
import android.widget.Toast;

import androidx.fragment.app.FragmentTransaction;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.example.android_sep4.R;

public class SettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.pref_settings);
        getActivity().setTitle("Settings");

        Preference button = findPreference(getString(R.string.pref_user_button_key));
        if (button != null) {
            button.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    UserFragment userFragment = new UserFragment();
                    FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.activity_settings, userFragment).addToBackStack(null);
                    transaction.commit();
                    return true;
                }
            });
        }
    }
}

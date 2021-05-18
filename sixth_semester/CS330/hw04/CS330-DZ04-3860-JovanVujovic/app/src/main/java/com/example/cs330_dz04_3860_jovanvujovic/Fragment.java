package com.example.cs330_dz04_3860_jovanvujovic;

import android.content.Intent;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.widget.Toast;

public class Fragment extends PreferenceFragment {
    public static final String KEY_LIST_PREFERENCE = "listPref";
    public static final String CATEGORY = "info";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        ListPreference mListPreference = (ListPreference)getPreferenceScreen().findPreference(KEY_LIST_PREFERENCE);

        mListPreference.setOnPreferenceChangeListener((preference, newValue) -> {

            if(newValue.toString().equals("2"))
                Toast.makeText(getContext(), "TACNO", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(getContext(), "NETACNO", Toast.LENGTH_SHORT).show();
            return false;
        });

        CheckBoxPreference checkBox = (CheckBoxPreference) getPreferenceScreen().findPreference(CATEGORY);

        checkBox.setOnPreferenceClickListener(preference -> {

            System.out.println(preference);

            Intent intent = new Intent(getContext(), Metropolitan.class);

            startActivity(intent);

            return false;
        });
    }

}

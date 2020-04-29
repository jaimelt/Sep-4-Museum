package com.example.android_sep4.view.room;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_sep4.R;
import com.example.android_sep4.view.artwork.ArtworksTab;
import com.example.android_sep4.viewmodel.EditRoomsConditionsViewModel;
import com.example.android_sep4.viewmodel.artwork.EditArtworkViewModel;

public class EditRoomActivity extends AppCompatActivity {
    EditRoomsConditionsViewModel editArtworkMeasurements;
     EditText temperature;
     EditText humidity;
     EditText co2;
     EditText light;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_room);
        temperature = (EditText) findViewById(R.id.editTemperatureEditText);
        humidity = (EditText) findViewById(R.id.editHumidityEditText);
        co2 = (EditText) findViewById(R.id.editCo2EditText);
        light = (EditText) findViewById(R.id.editLightEditText);

        Bundle bundle = getIntent().getExtras();
        if(bundle != null && bundle.containsKey(RoomsTab.EXTRA_ROOM)) {
            position = bundle.getInt(RoomsTab.EXTRA_ROOM);
        }
    }
    public void onEditRoomOptimal(View view)
    {
        int minTempInt = Integer.parseInt(temperature.getText().toString());
        int maxTempInt = Integer.parseInt(co2.getText().toString());
        int minLightInt = Integer.parseInt(humidity.getText().toString());
        int maxLightInt = Integer.parseInt(light.getText().toString());
        editArtworkMeasurements.editRoomOptimal(minTempInt, maxTempInt, minLightInt, maxLightInt);
        finish();
        Toast.makeText(this, name + " artwork edited", Toast.LENGTH_SHORT).show();
    }
}

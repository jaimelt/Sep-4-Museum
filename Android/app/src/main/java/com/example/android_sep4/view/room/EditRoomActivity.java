package com.example.android_sep4.view.room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

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
     private EditRoomsConditionsViewModel editMeasurements;
     private EditText temperature;
     private EditText humidity;
     private EditText co2;
     private EditText light;
     private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_room);

        setViewModel();

        Bundle bundle = getIntent().getExtras();
        if(bundle != null && bundle.containsKey(RoomsTab.EXTRA_ROOM)) {
            position = bundle.getInt(RoomsTab.EXTRA_ROOM);
        }
        temperature = (EditText) findViewById(R.id.editTemperatureEditText);
        humidity = (EditText) findViewById(R.id.editHumidityEditText);
        co2 = (EditText) findViewById(R.id.editCo2EditText);
        light = (EditText) findViewById(R.id.editLightEditText);
        temperature.setText(Integer.toString(bundle.getInt("temperature")));
        co2.setText(Integer.toString(bundle.getInt("co2")));
        light.setText(Integer.toString(bundle.getInt("light")));
        humidity.setText(Integer.toString(bundle.getInt("humidity")));

    }

    private void setViewModel() {
        editMeasurements = new ViewModelProvider(this).get(EditRoomsConditionsViewModel.class);
        editMeasurements.init(position);
    }

    public void onEditRoomOptimal(View view)
    {
        int newLight = Integer.parseInt(light.getText().toString());
        int newCo2 = Integer.parseInt(co2.getText().toString());
        int newTemperature = Integer.parseInt(temperature.getText().toString());
        int newHumidity = Integer.parseInt(humidity.getText().toString());
        editMeasurements.editRoomOptimal(newLight, newCo2, newTemperature, newHumidity, position);
        finish();
        Toast.makeText(this,  " Optimal conditions edited", Toast.LENGTH_SHORT).show();
    }
}

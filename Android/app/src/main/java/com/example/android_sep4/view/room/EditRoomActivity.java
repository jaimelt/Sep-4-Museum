package com.example.android_sep4.view.room;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.android_sep4.R;
import com.example.android_sep4.viewmodel.roomList.EditRoomsConditionsViewModel;

public class EditRoomActivity extends AppCompatActivity {
    private EditRoomsConditionsViewModel editMeasurements;
    private EditText temperature;
    private EditText humidity;
    private EditText co2;
    private EditText light;
    private String locationCode;
    private String description;
    private int optimalTemperature;
    private int optimalLight;
    private int optimalCo2;
    private int optimalHumidity;
    private int liveTemperature;
    private int liveHumidity;
    private int liveCo2;
    private int liveLight;
    private int totalCapacity;
    private int currentCapacity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_room);

        setViewModel();

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            locationCode = bundle.getString("locationCode");
            description = bundle.getString("description");
            optimalTemperature = bundle.getInt("optimalTemperature");
            optimalLight = bundle.getInt("optimalLight");
            optimalCo2 = bundle.getInt("optimalCo2");
            optimalHumidity = bundle.getInt("optimalHumidity");
            liveTemperature = bundle.getInt("liveTemperature");
            liveHumidity = bundle.getInt("liveHumidity");
            liveCo2 = bundle.getInt("liveCo2");
            liveLight = bundle.getInt("liveLight");
            totalCapacity = bundle.getInt("totalCapacity");
            currentCapacity = bundle.getInt("currentCapacity");
        }
        temperature = findViewById(R.id.editTemperatureEditText);
        humidity = findViewById(R.id.editHumidityEditText);
        co2 = findViewById(R.id.editCo2EditText);
        light = findViewById(R.id.editLightEditText);
        temperature.setText(Integer.toString(optimalTemperature));
        co2.setText(Integer.toString(optimalCo2));
        light.setText(Integer.toString(optimalLight));
        humidity.setText(Integer.toString(optimalHumidity));
    }

    private void setViewModel() {
        editMeasurements = new ViewModelProvider(this).get(EditRoomsConditionsViewModel.class);
    }

    public void onEditRoomOptimal(View view) {
        int newLight = Integer.parseInt(light.getText().toString());
        int newCo2 = Integer.parseInt(co2.getText().toString());
        int newTemperature = Integer.parseInt(temperature.getText().toString());
        int newHumidity = Integer.parseInt(humidity.getText().toString());
        editMeasurements.editRoomOptimal(locationCode, description, totalCapacity, currentCapacity, newLight, newCo2, newTemperature, newHumidity, liveTemperature, liveCo2, liveHumidity, liveLight);
        finish();
        Toast.makeText(this, " Optimal conditions edited", Toast.LENGTH_SHORT).show();
    }
}

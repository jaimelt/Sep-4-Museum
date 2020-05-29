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
    private double optimalTemperature;
    private double optimalLight;
    private double optimalCo2;
    private double optimalHumidity;
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
            optimalTemperature = bundle.getDouble("optimalTemperature");
            optimalLight = bundle.getDouble("optimalLight");
            optimalCo2 = bundle.getDouble("optimalCo2");
            optimalHumidity = bundle.getDouble("optimalHumidity");
            totalCapacity = bundle.getInt("totalCapacity");
            currentCapacity = bundle.getInt("currentCapacity");
        }

        temperature = findViewById(R.id.editTemperatureEditText);
        humidity = findViewById(R.id.editHumidityEditText);
        co2 = findViewById(R.id.editCo2EditText);
        light = findViewById(R.id.editLightEditText);
        temperature.setText(Double.toString(optimalTemperature));
        co2.setText(Double.toString(optimalCo2));
        light.setText(Double.toString(optimalLight));
        humidity.setText(Double.toString(optimalHumidity));
    }

    private void setViewModel() {
        editMeasurements = new ViewModelProvider(this).get(EditRoomsConditionsViewModel.class);
    }

    public void onEditRoomOptimal(View view) {
        double newLight = Double.parseDouble(light.getText().toString());
        double newCo2 = Double.parseDouble(co2.getText().toString());
        double newTemperature = Double.parseDouble(temperature.getText().toString());
        double newHumidity = Double.parseDouble(humidity.getText().toString());
        editMeasurements.editRoomOptimal(locationCode, description, totalCapacity, currentCapacity, newLight, newCo2, newTemperature, newHumidity, null);
        finish();
        Toast.makeText(this, " Optimal conditions edited", Toast.LENGTH_SHORT).show();
    }
}

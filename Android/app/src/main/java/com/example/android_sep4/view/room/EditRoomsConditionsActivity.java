package com.example.android_sep4.view.room;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import com.example.android_sep4.R;
import com.example.android_sep4.viewmodel.roomList.EditRoomsConditionsViewModel;

public class EditRoomsConditionsActivity extends AppCompatActivity {
    private EditRoomsConditionsViewModel viewModel;
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
        setToolbar();

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

        temperature = findViewById(R.id.edit_temperature_field);
        humidity = findViewById(R.id.edit_humidity_field);
        co2 = findViewById(R.id.edit_co2_field);
        light = findViewById(R.id.edit_light_field);

        temperature.setText(String.valueOf(optimalTemperature));
        co2.setText(String.valueOf(optimalCo2));
        light.setText(String.valueOf(optimalLight));
        humidity.setText(String.valueOf(optimalHumidity));
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Edit optimal conditions");
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        finish();
        return true;
    }

    private void setViewModel() {
        viewModel = new ViewModelProvider(this).get(EditRoomsConditionsViewModel.class);
    }

    public void onEditRoomOptimal(View view) {
        String lightText = light.getText().toString();
        String co2Text = co2.getText().toString();
        String temperatureText = temperature.getText().toString();
        String humidityText = humidity.getText().toString();

        int validation = viewModel.validateEditRoomFields(lightText, co2Text, temperatureText, humidityText);
        switch (validation) {
            case 1:
                light.setError("Field can not be empty");
                break;
            case 2:
                co2.setError("Field can not be empty");
                break;
            case 3:
                temperature.setError("Field can not be empty");
                break;
            case 4:
                humidity.setError("Field can not be empty");
                break;
            case 5:
                double newLight = Double.parseDouble(light.getText().toString());
                double newCo2 = Double.parseDouble(co2.getText().toString());
                double newTemperature = Double.parseDouble(temperature.getText().toString());
                double newHumidity = Double.parseDouble(humidity.getText().toString());
                viewModel.editRoomOptimal(locationCode, description, totalCapacity, currentCapacity, newLight, newCo2, newTemperature, newHumidity, null);
                finish();
                Toast.makeText(this, " Optimal conditions edited", Toast.LENGTH_SHORT).show();
        }
    }
}

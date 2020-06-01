package com.example.android_sep4.view.room;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.android_sep4.R;

import org.w3c.dom.Text;

public class VisualizeData extends AppCompatActivity {
    private TextView light;
    private TextView co2;
    private TextView humidity;
    private TextView temperature;

    private TextView liveDataLight;
    private TextView liveDataCo2;
    private TextView liveDataHumidity;
    private TextView liveDataTemperature;

    private String locationCode;
    private String description;
    private double optimalTemperature;
    private double optimalLight;
    private double optimalCo2;
    private double optimalHumidity;
    private double liveTemp;
    private double liveHumidity;
    private double liveCo2;
    private double liveLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualize_data);
        onBindViews();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            locationCode = bundle.getString("locationCode");
            description = bundle.getString("description");
            optimalTemperature = bundle.getDouble("optimalTemperature");
            optimalLight = bundle.getDouble("optimalLight");
            optimalCo2 = bundle.getDouble("optimalCo2");
            optimalHumidity = bundle.getDouble("optimalHumidity");
          /*  liveCo2 = bundle.getDouble("liveCo2");
            liveHumidity = bundle.getDouble("liveHumidity");
            liveLight = bundle.getDouble("liveLight");
            liveTemp = bundle.getDouble("liveTemp");*/
        }

        temperature.setText(Double.toString(optimalTemperature));
        humidity.setText(Double.toString(optimalHumidity));
        co2.setText(Double.toString(optimalCo2));
        light.setText(Double.toString(optimalLight));
     /*   liveDataCo2.setText(Double.toString((liveCo2)));
        liveDataHumidity.setText(Double.toString(liveHumidity));
        liveDataTemperature.setText(Double.toString(liveTemp));
        liveDataLight.setText(Double.toString(liveLight));*/
        liveDataCo2.setText("200");
        liveDataHumidity.setText("30");
        liveDataTemperature.setText("25");
        liveDataLight.setText("400");

    }

    private void onBindViews(){
        light = (TextView) findViewById(R.id.light);
        co2 = (TextView) findViewById(R.id.co2);
        temperature = (TextView) findViewById(R.id.temperature);
        humidity =(TextView) findViewById(R.id.humidity);
        liveDataLight = findViewById(R.id.LiveLight);
        liveDataTemperature = findViewById(R.id.LiveTemperature);
        liveDataHumidity = findViewById(R.id.LiveHumidity);
        liveDataCo2 = findViewById(R.id.liveCo2);
    }
}
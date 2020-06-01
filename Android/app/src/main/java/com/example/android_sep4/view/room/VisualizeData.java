package com.example.android_sep4.view.room;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.android_sep4.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class VisualizeData extends AppCompatActivity {
    private TextView light;
    private TextView co2;
    private TextView humidity;
    private TextView temperature;

    private TextView liveDataLight;
    private TextView liveDataCo2;
    private TextView liveDataHumidity;
    private TextView liveDataTemperature;
    private BarChart chart;
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
        addDataToChart();
    }

    private void addDataToChart(){
        ArrayList<BarEntry> live = new ArrayList<BarEntry>();
        live.add(new BarEntry(1F,30));
        live.add(new BarEntry(2F,40));
        live.add(new BarEntry(3F,50));
        live.add(new BarEntry(4F,50));
        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
        entries.add(new BarEntry(1F,30));
        entries.add(new BarEntry(2F,40));
        entries.add(new BarEntry(3F,50));
        entries.add(new BarEntry(4F,50));
        BarDataSet barDataSet = new BarDataSet(entries, "Data");

        ArrayList<String> measurementsName = new ArrayList<>();
        measurementsName.add("Light");
        measurementsName.add("Co2");
        measurementsName.add("Temp");
        measurementsName.add("Humidity");

        BarData data  = new BarData(barDataSet);
        data.setBarWidth(0.9f);
        chart.setData(data);
        chart.setFitBars(true);
        chart.invalidate();
        chart.setDrawGridBackground(false);
        chart.setDrawBorders(false);
        chart.setDrawValueAboveBar(false);
        chart.setBackgroundColor(00000);

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
        chart = (BarChart) findViewById(R.id.chart);
    }
}
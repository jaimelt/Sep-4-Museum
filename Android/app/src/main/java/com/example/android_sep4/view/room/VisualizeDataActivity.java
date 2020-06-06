package com.example.android_sep4.view.room;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.android_sep4.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class VisualizeDataActivity extends AppCompatActivity {
    private BarChart chart;

    private double optimalTemperature;
    private double optimalLight;
    private double optimalCo2;
    private double optimalHumidity;

    private double liveTemp;
    private double liveHumidity;
    private double liveCo2;
    private double liveLight;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualize_data);
        setToolbar();

        onBindViews();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            optimalTemperature = bundle.getDouble("optimalTemperature");
            optimalLight = bundle.getDouble("optimalLight");
            optimalCo2 = bundle.getDouble("optimalCo2");
            optimalHumidity = bundle.getDouble("optimalHumidity");
            liveCo2 = bundle.getDouble("liveCo2");
            liveHumidity = bundle.getDouble("liveHumidity");
            liveLight = bundle.getDouble("liveLight");
            liveTemp = bundle.getDouble("liveTemp");
        }

        addDataToChart();
    }

    private void setToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
        {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        setTitle("Graph");
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        finish();
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void addDataToChart() {

        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
        entries.add(new BarEntry(1F, (float) optimalLight));
        entries.add(new BarEntry(3F, (float) optimalHumidity));
        entries.add(new BarEntry(5F, (float) optimalCo2));
        entries.add(new BarEntry(7F, (float) optimalTemperature));
        ArrayList<BarEntry> entries2 = new ArrayList<BarEntry>();
        entries2.add(new BarEntry(2F, (float) liveLight));
        entries2.add(new BarEntry(4F, (float) liveHumidity));
        entries2.add(new BarEntry(6F, (float) liveCo2));
        entries2.add(new BarEntry(8F, (float) liveTemp));

        BarDataSet set1 = new BarDataSet(entries, "Optimal Conditions");
        set1.setColor(getColor(R.color.optimalColor));
        BarDataSet set2 = new BarDataSet(entries2, "Actual Conditions");
        set2.setColor(getColor(R.color.actualColor));
        set1.setValueTextColor(getColor(R.color.white));
        set1.setValueTextSize(18);
        set2.setValueTextColor(getColor(R.color.white));
        set2.setValueTextSize(18);

        BarData data = new BarData(set1, set2);
        float groupSpace = 0.5f;
        float barSpace = 0.02f; //
        chart.setData(data);
        chart.getDescription().setEnabled(false);
        chart.getXAxis().setDrawGridLines(false);
        chart.getAxisRight().setEnabled(false);
        chart.getAxisLeft().setEnabled(false);
        chart.getLegend().setTextSize(18);
        chart.getXAxis().setEnabled(false);
        chart.getLegend().setTextColor(getColor(R.color.white));
        chart.groupBars(0f, groupSpace, barSpace);
        chart.invalidate();
    }


    private void onBindViews() {
        chart = (BarChart) findViewById(R.id.chart);
    }
}
package com.example.android_sep4.view.room;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android_sep4.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.BaseDataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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


     /*   liveDataCo2.setText(Double.toString((liveCo2)));
        liveDataHumidity.setText(Double.toString(liveHumidity));
        liveDataTemperature.setText(Double.toString(liveTemp));
        liveDataLight.setText(Double.toString(liveLight));*/

        addDataToChart();
    }

    private void addDataToChart(){

        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
        entries.add(new BarEntry(1F, (float) optimalCo2));
        entries.add(new BarEntry(3F, (float) optimalHumidity));
        entries.add(new BarEntry(5F, (float) optimalLight));
        entries.add(new BarEntry(7F, (float)optimalTemperature));
        ArrayList<BarEntry> entries2 = new ArrayList<BarEntry>();
        entries2.add(new BarEntry(2F, 40f));
        entries2.add(new BarEntry(4F, 50f));
        entries2.add(new BarEntry(6F, 30f));
        entries2.add(new BarEntry(8F, 20f));

        // create 2 datasets
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
        float barSpace = 0.02f; // x2 dataset

// Set the value formatter

        chart.setData(data);// available since release v3.0.0
        chart.getDescription().setEnabled(false);
        chart.getXAxis().setDrawGridLines(false);
        chart.getAxisRight().setEnabled(false);
        chart.getAxisLeft().setEnabled(false);
        chart.getLegend().setTextSize(18);
        chart.getXAxis().setEnabled(false);
        chart.getLegend().setTextColor(getColor(R.color.white));
        chart.groupBars(0f, groupSpace, barSpace); // perform the "explicit" grouping
        chart.invalidate(); // refresh
    }


    private void onBindViews(){
        light = (TextView) findViewById(R.id.light);
        co2 = (TextView) findViewById(R.id.co2);
        temperature = (TextView) findViewById(R.id.temperature);
        humidity =(TextView) findViewById(R.id.humidity);
      /*  liveDataLight = findViewById(R.id.LiveLight);
        liveDataTemperature = findViewById(R.id.LiveTemperature);
        liveDataHumidity = findViewById(R.id.LiveHumidity);
        liveDataCo2 = findViewById(R.id.liveCo2);*/
        chart = (BarChart) findViewById(R.id.chart);
    }
    public class IndexAxisValueFormatter extends ValueFormatter
    {
        private String[] mValues = new String[] {};
        private int mValueCount = 0;

        /**
         * An empty constructor.
         * Use `setValues` to set the axis labels.
         */
        public IndexAxisValueFormatter() {
        }

        /**
         * Constructor that specifies axis labels.
         *
         * @param values The values string array
         */
        public IndexAxisValueFormatter(String[] values) {
            if (values != null)
                setValues(values);
        }

        /**
         * Constructor that specifies axis labels.
         *
         * @param values The values string array
         */
        public IndexAxisValueFormatter(Collection<String> values) {
            if (values != null)
                setValues(values.toArray(new String[values.size()]));
        }

        @Override
        public String getFormattedValue(float value, AxisBase axisBase) {
            int index = Math.round(value);

            if (index < 0 || index >= mValueCount || index != (int)value)
                return "";

            return mValues[index];
        }

        public String[] getValues()
        {
            return mValues;
        }

        public void setValues(String[] values)
        {
            if (values == null)
                values = new String[] {};

            this.mValues = values;
            this.mValueCount = values.length;
        }
    }
}
package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;

public class Result extends AppCompatActivity {

    BarChart barChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        barChart = (BarChart) findViewById(R.id.bargraph);


        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0,0));
        entries.add(new BarEntry(1,1));
        entries.add(new BarEntry(2,2));
        entries.add(new BarEntry(3,5));
        entries.add(new BarEntry(4,5));
        entries.add(new BarEntry(5,2));
        entries.add(new BarEntry(6,7));
        entries.add(new BarEntry(7,5));
        entries.add(new BarEntry(8,4));
        entries.add(new BarEntry(9,4));
        entries.add(new BarEntry(10,3));
        entries.add(new BarEntry(11,1));
        entries.add(new BarEntry(12,2));
        entries.add(new BarEntry(13,2));
        entries.add(new BarEntry(14,3));

        BarDataSet barDataSet = new BarDataSet(entries, "Dataset");

        BarData data = new BarData(barDataSet);

        barChart.setData(data);
        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);

        final ArrayList<String> xAxisLabel = new ArrayList<>();
        xAxisLabel.add("17/2 1 p.m.");
        xAxisLabel.add("2 p.m.");
        xAxisLabel.add("3 p.m.");
        xAxisLabel.add("4 p.m.");
        xAxisLabel.add("5 p.m.");
        xAxisLabel.add("18/2 1 p.m.");
        xAxisLabel.add("2 p.m.");
        xAxisLabel.add("3 p.m.");
        xAxisLabel.add("4 p.m.");
        xAxisLabel.add("5 p.m.");
        xAxisLabel.add("19/2 1 p.m.");
        xAxisLabel.add("2 p.m.");
        xAxisLabel.add("3 p.m.");
        xAxisLabel.add("4 p.m.");
        xAxisLabel.add("5 p.m.");

        XAxis xAxis = barChart.getXAxis();
        xAxis.setDrawAxisLine(false);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisLabel));



    }


}

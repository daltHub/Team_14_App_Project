package com.example.projectmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

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
        final String groupId = getIntent().getStringExtra("GROUPID");
        Log.e("GROUPID - TEST", groupId);

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

        barDataSet.setColor(Color.parseColor("#5eec3f"));
        BarData data = new BarData(barDataSet);

        barChart.setData(data);
        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);
        String name_variable = getIntent().getStringExtra("NAME_VARIABLE");
        String date = getIntent().getStringExtra("Date");
        String date1 = getIntent().getStringExtra("Date1");
        String date2 = getIntent().getStringExtra("Date2");
        String date3 = getIntent().getStringExtra("Date3");
        String date4 = getIntent().getStringExtra("Date4");
        String name = "Hi! " + name_variable;
        TextView message = (TextView) findViewById(R.id.textView3);
        message.setText(name);

        final ArrayList<String> xAxisLabel = new ArrayList<>();
        xAxisLabel.add(date);
        xAxisLabel.add(date1);
        xAxisLabel.add(date2);
        xAxisLabel.add(date3);
        xAxisLabel.add(date4);


        XAxis xAxis = barChart.getXAxis();
        xAxis.setDrawAxisLine(false);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisLabel));



    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu_poll, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        final String groupId = getIntent().getStringExtra("GROUPID");
        Log.e("GROUPID - TEST", groupId);
        switch (item.getItemId()) {
            case R.id.action_homescreen:
                Intent goHome = new Intent(this, Homescreen.class);
                goHome.putExtra("GROUPID",groupId);
                startActivity(goHome);
                return true;
            case R.id.action_back_to_poll:
                Intent goPoll = new Intent(this, Voting.class);
                goPoll.putExtra("GROUPID",groupId);
                startActivity(goPoll);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}

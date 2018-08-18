package com.example.tejas.hack_iiid.Daily;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.tejas.hack_iiid.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class DailyFigures extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_figures);
        
        setUpWeightChart();
        setUpCalorieChart();
    }

    private void setUpCalorieChart() {

        BarChart barChart=findViewById(R.id.weekly_calorie_chart);

        ArrayList<BarEntry> barEntries=new ArrayList<>();
        barEntries.add(new BarEntry(1821f,0));
        barEntries.add(new BarEntry(1733f,1));
        barEntries.add(new BarEntry(1937f,2));
        barEntries.add(new BarEntry(1514f,3));
        barEntries.add(new BarEntry(1933f,4));
        barEntries.add(new BarEntry(1211f,5));
        barEntries.add(new BarEntry(1842f,6));

        BarDataSet dataSet=new BarDataSet(barEntries,"Steps");

        ArrayList<String> lables=new ArrayList<>();
        lables.add("Mon");
        lables.add("Tue");
        lables.add("Wed");
        lables.add("Thurs");
        lables.add("Fri");
        lables.add("Sat");
        lables.add("Sun");

        BarData barData=new BarData(lables,dataSet);
        dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        barChart.setData(barData);
        barChart.getLegend().setEnabled(false);
    }

    private void setUpWeightChart() {

        BarChart barChart=findViewById(R.id.weekly_steps_chart);

        ArrayList<BarEntry> barEntries=new ArrayList<>();
        barEntries.add(new BarEntry(3533f,0));
        barEntries.add(new BarEntry(4333f,1));
        barEntries.add(new BarEntry(2937f,2));
        barEntries.add(new BarEntry(4514f,3));
        barEntries.add(new BarEntry(3533f,4));
        barEntries.add(new BarEntry(3211f,5));
        barEntries.add(new BarEntry(3842f,6));

        BarDataSet dataSet=new BarDataSet(barEntries,"Steps");

        ArrayList<String> lables=new ArrayList<>();
        lables.add("Mon");
        lables.add("Tue");
        lables.add("Wed");
        lables.add("Thurs");
        lables.add("Fri");
        lables.add("Sat");
        lables.add("Sun");

        BarData barData=new BarData(lables,dataSet);
        dataSet.setColors(ColorTemplate.VORDIPLOM_COLORS);
        barChart.setData(barData);
        barChart.getLegend().setEnabled(false);
    }
}

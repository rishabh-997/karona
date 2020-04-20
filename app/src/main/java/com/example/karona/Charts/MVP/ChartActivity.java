package com.example.karona.Charts.MVP;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.karona.Charts.Model.ChartList;
import com.example.karona.R;
import com.example.karona.databinding.ActivityChartBinding;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class ChartActivity extends AppCompatActivity implements ChartContract.view {
    ChartContract.presenter presenter;
    ActivityChartBinding binding;

    List<ChartList> chartLists = new ArrayList<>();
    ArrayList<Entry> values = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        presenter = new ChartPresenter(this);
        presenter.getChartData();
        initListeners();
    }

    private void initListeners() {
        binding.totalCases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                values.clear();
                for (int i = 1; i <= chartLists.size(); i++) {
                    int cases = Integer.parseInt(chartLists.get(i - 1).getTotalConfirmed());
                    values.add(new Entry(i, cases));
                }
                renderData(values);
            }
        });

        binding.totalRecovered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                values.clear();
                for (int i = 1; i <= chartLists.size(); i++) {
                    int cases = Integer.parseInt(chartLists.get(i - 1).getTotalRecovered());
                    values.add(new Entry(i, cases));
                }
                renderData(values);
            }
        });

        binding.totalDeceased.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                values.clear();
                for (int i = 1; i <= chartLists.size(); i++) {
                    int cases = Integer.parseInt(chartLists.get(i - 1).getTotalDeceased());
                    values.add(new Entry(i, cases));
                }
                renderData(values);
            }
        });
    }

    @Override
    public void toast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showChart(List<ChartList> chart) {
        chartLists = chart;
        values.clear();
        for (int i = 1; i <= chartLists.size(); i++) {
            int cases = Integer.parseInt(chartLists.get(i - 1).getTotalConfirmed());
            values.add(new Entry(i, cases));
        }
        renderData(values);
    }

    public void renderData(ArrayList<Entry> values) {
        binding.notice.setVisibility(View.VISIBLE);
        LineDataSet dataSet = new LineDataSet(values, "Customized values");
        dataSet.setColor(ContextCompat.getColor(this, R.color.colorPrimary));
        dataSet.setValueTextColor(ContextCompat.getColor(this, R.color.colorPrimaryDark));

        XAxis xAxis = binding.chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        YAxis yAxisRight = binding.chart.getAxisRight();
        yAxisRight.setEnabled(false);

        YAxis yAxisLeft = binding.chart.getAxisLeft();
        yAxisLeft.setGranularity(1f);

        LineData data = new LineData(dataSet);
        binding.chart.setData(data);
        binding.chart.animateX(2500);
        binding.chart.invalidate();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

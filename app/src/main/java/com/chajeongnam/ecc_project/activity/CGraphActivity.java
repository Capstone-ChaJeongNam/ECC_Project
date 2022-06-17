package com.chajeongnam.ecc_project.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.chajeongnam.ecc_project.R;
import com.chajeongnam.ecc_project.model.Result;
import com.chajeongnam.ecc_project.model.Student;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.utilities.Tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class CGraphActivity extends AppCompatActivity implements View.OnClickListener {
    private LineChart chart;
    List<Integer> results;
    List<String> dates;
    Student student;
    List<Integer> monthResults;
    List<String> monthLabels;

    List<Integer> yearResults;
    List<String> yearLabels;

    int min = 1000, max = 0;
    int monthMin = 1000, monthMax = 0;
    int yearMin = 1000, yearMax = 0;

    Map<String, Integer> sortedHistories;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cgraph);

        setView();
        setActionbar();
        getExtra();
        setChart(results, dates, min, max);
    }

    private void setView() {
        ImageView setClassifyButton = findViewById(R.id.setClassifyButton);
        TextView setClassifyButtonTextView = findViewById(R.id.setClassifyButtonTextView);
        chart = findViewById(R.id.cGraphChart);

        setClassifyButton.setOnClickListener(this);
        setClassifyButtonTextView.setOnClickListener(this);
    }

    private void getExtra() {
        /**
         * TODO 인텐트 데이터 키 값 협의
         */
        dates = getIntent().getStringArrayListExtra("dates");
        student = getIntent().getParcelableExtra("student");
//         List<String> dates = getIntent().getStringArrayListExtra("dates");
//        dates = new ArrayList<>();
////        dates.add("0");
//        dates.add("2022-04-30");
////        dates.add("2021-04-26");
//        dates.add("2022-05-03");
//        dates.add("2022-05-08");
//        dates.add("2022-05-15");
//        dates.add("2021-05-23");
//        histories.put("2022-04-30", 0);
//        histories.put("2022-05-03", 0);
//        histories.put("2022-05-08", 0);
        getResults();
//        results = new ArrayList<>();
//        results.add(2);
//        results.add(1);
//        results.add(3);
//        results.add(2);
//        results.add(4);
//        getResults();

    }



    private void setValueMonth(){
        String month = dates.get(0).split("-")[1];
        monthResults = new ArrayList<>();
        monthLabels = new ArrayList<>();
        monthLabels.add(month);
        int count = 0;


        for (int i = 0; i < dates.size(); i++) {
            String current = dates.get(i).split("-")[1];
            if (!month.equals(current)) {
                monthResults.add(count);
                month = current;
                monthLabels.add(current);
                monthMin = Math.min(monthMin, count);
                monthMax = Math.max(monthMax, count);
                count = results.get(i);
            }else{
                count += results.get(i);
            }

        }

        if (count != 0){
            monthResults.add(count);
            monthMin = Math.min(monthMin, count);
            monthMax = Math.max(monthMax, count);
//            labels.add(dates.get(dates.size()-1).split("-")[1]);
        }
//        Log.d("month", monthResults.get(0).toString());
//        Log.d("month", monthResults.get(1).toString());


    }


    private void setValueYear(){
        String year = dates.get(0).split("-")[0];
        yearResults = new ArrayList<>();
        yearLabels = new ArrayList<>();
        yearLabels.add(year);
        int count = 0;

        for (int i = 1; i < dates.size(); i++) {
            String current = dates.get(i).split("-")[0];
            if (!year.equals(current)) {
                yearResults.add(count);
                year = current;
                yearLabels.add(current);
                yearMin = Math.min(yearMin, count);
                yearMax = Math.max(yearMax, count);
                count = results.get(i);
            }else{
                count += results.get(i);
            }

        }

        if (count != 0){
            yearResults.add(count);
            yearMin = Math.min(yearMin, count);
            yearMax = Math.max(yearMax, count);
//            labels.add(dates.get(dates.size()-1).split("-")[0]);
        }
    }

    private void setChart(List<Integer> results, List<String> labels, int min, int max){
        setData(getEntries(results));
        setYAxis(results, min, max);
        setXAxis(labels);
    }

    private void setActionbar() {
        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.layout_actionbar);
        TextView textView = findViewById(R.id.titleTextView);
        textView.setText("사후 평가 기록");
        ImageButton imageButton = findViewById(R.id.backImageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void getResults() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("histories");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//        String uid = user.getUid();
        String category = getIntent().getStringExtra("category");
        String area = getIntent().getStringExtra("area");

//        String uid = "BN34s1_MlC5";
//        String category = "점자";
//        String area = "한글 점자";

        results = new ArrayList<>();

        HashMap<String, Integer> histories = new HashMap<>();
        DatabaseReference historyRef = myRef.child(student.getUid()).child("post").child(category).child(area);
        for(int i = 0; i < dates.size(); i++){
            String date = dates.get(i);
            DatabaseReference datesRef = historyRef.child(dates.get(i));
            datesRef.addValueEventListener(new ValueEventListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    Log.d("Firebase Realtime DB", dataSnapshot.getKey());
                    int count = 0;

                    for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                        Result result = postSnapshot.getValue(Result.class);
                        Log.d("Firebase Realtime DB", result.getDescription());
                        if(result.getScore() == 4){
                            count += 1;
                        }

                    }
                    histories.put(date, count);
                    min = Math.min(min, count);
                    max = Math.max(max, count);

                    if(histories.size() == dates.size()){
                        sortedHistories = new TreeMap<>(histories);

                        for(String key : sortedHistories.keySet()){
                            results.add(sortedHistories.get(key));
                        }

                        setData(getEntries(results));
                        setXAxis(dates);
                        setYAxis(results, min, max);

                        setValueMonth();
                        setValueYear();
//                        Log.d("history", sortedHistories.get("2022-04-30").toString());
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                }
            });

        }

//        String uid = "BN34s1_MlC5";
//        String category = "점자";
//        String area = "한글 점자";

    }

    public void setData(List<Entry> entries) {

        LineDataSet dataSet = new LineDataSet(entries, student.getName()); // add entries to dataset
        dataSet.setColor(R.color.blue_500);
        dataSet.setCircleRadius(4f);
        dataSet.setCircleHoleRadius(3f);
        dataSet.setCircleHoleColor(Color.WHITE);
        dataSet.setCircleColor(R.color.blue_500);
        dataSet.setValueTextColor(Color.BLACK);
        dataSet.setDrawValues(false);
        LineData lineData = new LineData(dataSet);
        chart.setData(lineData);
        chart.setDescription(null);
    }

    private void setXAxis(List<String> labels) {
        XAxis x = chart.getXAxis();
        x.setPosition(XAxis.XAxisPosition.BOTTOM);
        x.setDrawAxisLine(true);
        x.setDrawGridLines(false);
        x.setSpaceMin(0.5f);
        x.setSpaceMax(0.5f);
        x.setLabelCount((int) labels.size(), false);
        x.setXOffset(0.5f);

        x.setValueFormatter(new IndexAxisValueFormatter(labels));
//        {
//            @Override
//            public String getFormattedValue(float value) {
//                if (value < labels.size()) {
//                    return labels.get((int) value);
//                } else
//                    return "";
//            }
//        });
    }

    private void setYAxis(List<Integer> values, int min, int max) {
        YAxis left = chart.getAxisLeft();
        left.setDrawAxisLine(false);

        left.setLabelCount(max - min + 1, true);
        left.setAxisMinimum(min);
        left.setAxisMaximum(max);
        left.setDrawZeroLine(false);
        left.setDrawGridLines(false);
        YAxis right = chart.getAxisRight();
        right.setEnabled(false);

        List<String> y = new ArrayList<>();
        for(int i = 0; i<values.size(); i++){
            y.add(String.valueOf(i));
        }
//        y.add("0");
//        y.add("1");
//        y.add("2");
//        y.add("3");
//        y.add("C");
//        left.setValueFormatter(new IndexAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value) {
//                return y.get((int) value);
//            }
//        });
    }

    public List<Entry> getEntries( List<Integer> results) {

        chart.invalidate();
        List<Entry> entries = new ArrayList<>();
        for(int i = 0 ; i < results.size(); i++){
            entries.add(new Entry(i, results.get(i)));
        }
        return entries;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.setClassifyButtonTextView: case R.id.setClassifyButton:
                TextView setClassifyButtonTextView = findViewById(R.id.setClassifyButtonTextView);
                if (setClassifyButtonTextView.getText().toString().equals("일 별")) {
                    setClassifyButtonTextView.setText("월 별");
                    if(monthLabels.size() != 1)
                    {
                        setChart(monthResults, monthLabels, monthMin, monthMax);
                    }
                } else if (setClassifyButtonTextView.getText().toString().equals("월 별")) {
                    setClassifyButtonTextView.setText("연도 별");
                    if(yearLabels.size()!=1){
                        setChart(yearResults, yearLabels, yearMin, yearMax);
                    }
                } else {
                    setClassifyButtonTextView.setText("일 별");
                    setChart(results, dates, min, max);
                }
        }
    }
}

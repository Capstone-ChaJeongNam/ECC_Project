package com.chajeongnam.ecc_project.activity;

import android.graphics.Color;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class AdvanceGraphActivity extends AppCompatActivity implements View.OnClickListener{
    LineChart chart;

    List<Integer> results;
    List<String> dates;

    List<Integer> monthResults;
    List<String> monthLabels;

    List<Integer> yearResults;
    List<String> yearLabels;
    Student student;
    int min = 1000, max = 0;
    int monthMin = 1000, monthMax = 0;
    int yearMin = 1000, yearMax = 0;
//
//    List<String> dates;
//    List<Integer> results;
    @Override
    protected void onCreate(Bundle savedStateInstance){
        super.onCreate(savedStateInstance);
        setContentView(R.layout.activity_advance_graph);

        setActionbar();
        setView();
        getExtra();
        setChart(results, dates);
    }


    private void setView() {
        ImageView setClassifyButton = findViewById(R.id.setClassifyButton);
        TextView setClassifyButtonTextView = findViewById(R.id.setClassifyButtonTextView);
        chart = findViewById(R.id.advanceGraphChart);

        setClassifyButton.setOnClickListener(this);
        setClassifyButtonTextView.setOnClickListener(this);
    }
//    }
//    private void setView() {
//        ImageView setClassifyButton = findViewById(R.id.setClassifyButton);
//        TextView setClassifyButtonTextView = findViewById(R.id.setClassifyButtonTextView);
//
//        chart = findViewById(R.id.advanceGraphChart);
//
//
//
//        setClassifyButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (setClassifyButtonTextView.getText().toString().equals("일 별")) {
//                    setClassifyButtonTextView.setText("월 별");
//                    setValueMonth();
//                } else if (setClassifyButtonTextView.getText().toString().equals("월 별")) {
//                    setClassifyButtonTextView.setText("연도 별");
//                    setValueYear();
//                } else {
//                    setClassifyButtonTextView.setText("일 별");
//                    getExtra();
//                }
//
//
//            }
//        });
//        setClassifyButtonTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (setClassifyButtonTextView.getText().toString().equals("일 별")) {
//                    setClassifyButtonTextView.setText("월 별");
//                    setValueMonth();
//                } else if (setClassifyButtonTextView.getText().toString().equals("월 별")) {
//                    setClassifyButtonTextView.setText("연도 별");
//                    setValueYear();
//                } else {
//                    setClassifyButtonTextView.setText("일 별");
//                    getExtra();
//                }
//            }
//        });
//    }

    private void getExtra(){
        /**
         * TODO 인텐트 데이터 키 값 협의
         */

        TextView listItemTextView = findViewById(R.id.listItemTextView);
//        listItemTextView.setText(getIntent().getStringExtra("item"));
        listItemTextView.setText(getIntent().getStringExtra("item"));
//         List<String> dates = getIntent().getStringArrayListExtra("dates");

//        dates.add("0");
//        dates.add("2021-04-15");
//        dates.add("2021-04-26");
//        dates.add("2021-05-08");
//        dates.add("2021-05-17");
//        dates.add("2021-05-23");
        dates = getIntent().getStringArrayListExtra("dates");
//        getResults();
        getResults();
//        results.add(2);
//        results.add(1);
//        results.add(3);
//        results.add(2);
//        results.add(4);
//        int min = 1;
//        int max = 4;
//        setData(getEntries(results));
//        setXAxis(dates);
//        setYAxis(results, min, max);
    }

//    private void setValueMonth(){
//        String month = dates.get(0).split("-")[1];
//        List<Integer> monthResult = new ArrayList<>();
//        List<String> labels = new ArrayList<>();
//        labels.add(month);
//        int count = 0;
//        int min = 1000;
//        int max = 0;
//
//        for (int i = 0; i < dates.size(); i++) {
//            String current = dates.get(i).split("-")[1];
//            if (!month.equals(current)) {
//                monthResult.add(count);
//                month = current;
//                labels.add(current);
//                min = Math.min(min, count);
//                max = Math.max(max, count);
//                count = results.get(i);
//            }else{
//                count += results.get(i);
//            }
//
//        }
//
//        if (count != 0){
//            monthResult.add(count);
//            min = Math.min(min, count);
//            max = Math.max(max, count);
////            labels.add(dates.get(dates.size()-1).split("-")[1]);
//        }
////        Log.d("month", monthResult.get(0).toString());
////        Log.d("month", monthResult.get(1).toString());
//        if(labels.size() != 1)
//        {
//            setData(getEntries(monthResult));
//            setYAxis(monthResult, min, max);
//            setXAxis(labels);
//        }
//
//    }
//
//    private void setValueYear(){
//        String year = dates.get(0).split("-")[0];
//        List<Integer> yearResult = new ArrayList<>();
//        List<String> labels = new ArrayList<>();
//        labels.add(year);
//        int count = 0;
//        int min = 1000;
//        int max = 0;
//        for (int i = 1; i < dates.size(); i++) {
//            String current = dates.get(i).split("-")[0];
//            if (!year.equals(current)) {
//                yearResult.add(count);
//                year = current;
//                labels.add(current);
//                min = Math.min(min, count);
//                max = Math.max(max, count);
//                count = results.get(i);
//            }else{
//                count += results.get(i);
//            }
//
//        }
//
//        if (count != 0){
//            yearResult.add(count);
//            min = Math.min(min, count);
//            max = Math.max(max, count);
////            labels.add(dates.get(dates.size()-1).split("-")[0]);
//            count = 0;
//        }
//        if(labels.size()!=1){
//            setData(getEntries(yearResult));
//            setYAxis(yearResult, min, max);
//            setXAxis(labels);
//        }
//
//    }

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

    private void setChart(List<Integer> results, List<String> labels){
        setData(getEntries(results));
        setYAxis(results);
        setXAxis(labels);
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

        x.setValueFormatter(new IndexAxisValueFormatter(labels) );
//        {
//            @Override
//            public String getFormattedValue(float value) {
//                if (value < labels.size()) {
//                    return labels.get((int) value);
//                } else
//                    return "";
//            }
//        }
//        );
    }

    private void setYAxis(List<Integer> values) {
        YAxis left = chart.getAxisLeft();
        left.setDrawAxisLine(false);

        left.setLabelCount(5, true);
        left.setAxisMinimum(0);
        left.setAxisMaximum(4);
        left.setDrawZeroLine(false);
        left.setDrawGridLines(false);
        YAxis right = chart.getAxisRight();
        right.setEnabled(false);

        List<String> y = new ArrayList<>();

        y.add("0");
        y.add("1");
        y.add("2");
        y.add("3");
        y.add("C");
        left.setValueFormatter(new IndexAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return y.get((int) value);
            }
        });
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
                        setChart(monthResults, monthLabels);
                    }
                } else if (setClassifyButtonTextView.getText().toString().equals("월 별")) {
                    setClassifyButtonTextView.setText("연도 별");
                    if(yearLabels.size()!=1){
                        setChart(yearResults, yearLabels);
                    }
                } else {
                    setClassifyButtonTextView.setText("일 별");
                    setChart(results, dates);
                }
        }
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
//    int min = 10;
//    int max = 0;
    private void getResults() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("histories");

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        student = getIntent().getParcelableExtra("student");
        String category = getIntent().getStringExtra("category");
        String area = getIntent().getStringExtra("area");
        String item = getIntent().getStringExtra("item");
        results = new ArrayList<>();
        DatabaseReference historyRef = myRef.child(student.getUid()).child("post").child(category).child(area);

        for(int i = 0; i < dates.size(); i++){
            String cid = item.substring(0,1);
            DatabaseReference itemRef = historyRef.child(dates.get(i)).child(cid);
            itemRef.get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        Log.e("firebase", "Error getting data", task.getException());
                    }
                    else {
                        Result result = task.getResult().getValue(Result.class);
                        results.add(result.getScore());
//                        max = Math.max(max, result.getScore());
//                        min = Math.min(min, result.getScore());
                        Log.d("firebase", String.valueOf(task.getResult().getValue(Result.class)));
                    }

                    if(results.size() == dates.size()){
                        setData(getEntries(results));
                        setXAxis(dates);
                        setYAxis(results);

                        setValueMonth();
                        setValueYear();
                    }

                }
            });
//            itemRef.addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                    Object ob = dataSnapshot.getValue();
//                    results.add(dataSnapshot.child("score").getValue(Long.class).intValue());
//                }
//
//                @Override
//                public void onCancelled(@NonNull DatabaseError error) {
//                }
//            });
        }

//        String uid = "BN34s1_MlC5";
//        String category = "점자";
//        String area = "한글 점자";

    }



//    public void setData(List<Entry> entries) {
//
//        LineDataSet dataSet = new LineDataSet(entries, "홍길동"); // add entries to dataset
//        dataSet.setColor(R.color.blue_500);
//        dataSet.setCircleRadius(4f);
//        dataSet.setCircleHoleRadius(3f);
//        dataSet.setCircleHoleColor(Color.WHITE);
//        dataSet.setCircleColor(R.color.blue_500);
//        dataSet.setValueTextColor(Color.BLACK);
//        dataSet.setDrawValues(false);
//        LineData lineData = new LineData(dataSet);
//        chart.setData(lineData);
//        chart.setDescription(null);
//
//
//
//    }
//
//    private void setXAxis(List<String> labels) {
//        XAxis x = chart.getXAxis();
//        x.setPosition(XAxis.XAxisPosition.BOTTOM);
//        x.setDrawAxisLine(true);
//        x.setDrawGridLines(false);
//        x.setSpaceMin(0.5f);
//        x.setSpaceMax(0.5f);
//        x.setLabelCount((int) labels.size(), false);
//        x.setXOffset(0.5f);
//
//        x.setValueFormatter(new IndexAxisValueFormatter() {
//            @Override
//            public String getFormattedValue(float value) {
//                if (value < labels.size()) {
//                    return labels.get((int) value);
//                } else
//                    return "";
//            }
//        });
//    }
//
//    private void setYAxis(List<Integer> values, int min, int max) {
//        YAxis left = chart.getAxisLeft();
//        left.setDrawAxisLine(false);
//
//        left.setLabelCount(max, true);
//        left.setAxisMinimum(min);
//        left.setAxisMaximum(max);
//        left.setDrawZeroLine(false);
//        left.setDrawGridLines(false);
//        YAxis right = chart.getAxisRight();
//        right.setEnabled(false);
//
//        List<String> y = new ArrayList<>();
//        for(int i = 0; i<values.size(); i++){
//            y.add(String.valueOf(i));
//        }
////        y.add("0");
////        y.add("1");
////        y.add("2");
////        y.add("3");
////        y.add("C");
////        left.setValueFormatter(new IndexAxisValueFormatter() {
////            @Override
////            public String getFormattedValue(float value) {
////                return y.get((int) value);
////            }
////        });
//    }
//
//    public List<Entry> getEntries( List<Integer> results) {
//
//        chart.invalidate();
//        List<Entry> entries = new ArrayList<>();
//        for(int i = 0 ; i < results.size(); i++){
//            entries.add(new Entry(i, results.get(i)));
//        }
//
//        return entries;
//    }
}

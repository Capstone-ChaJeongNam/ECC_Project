package com.chajeongnam.ecc_project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chajeongnam.ecc_project.R;
import com.chajeongnam.ecc_project.adapter.PostChecklistAdapter;
import com.chajeongnam.ecc_project.adapter.PostHistoryAdapter;
import com.chajeongnam.ecc_project.decoration.SetItemDecoration;
import com.chajeongnam.ecc_project.model.TempList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PostHistoryActivity extends AppCompatActivity {
    private int startYear;
    private int startMonth;
    private int startDay;
    private int endYear;
    private int endMonth;
    private int endDay;

    private RecyclerView recyclerView;
    private List<TempList> tempLists;
    private PostHistoryAdapter postHistoryAdapter;
    private ArrayList<HashMap> result = new ArrayList<>();
    private Button saveBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_history);
        Intent intent = getIntent();
        startYear=intent.getExtras().getInt("startYear");
        startMonth=intent.getExtras().getInt("startMonth");
        startDay=intent.getExtras().getInt("startDay");

        endYear=intent.getExtras().getInt("endYear");
        endMonth=intent.getExtras().getInt("endMonth");
        endDay=intent.getExtras().getInt("endDay");
        tempLists = new ArrayList<>();



        recyclerView = findViewById(R.id.postHistoryRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(PostHistoryActivity.this));
        SetItemDecoration itemDecoration = new SetItemDecoration(20);
        recyclerView.addItemDecoration(itemDecoration);
        postHistoryAdapter = new PostHistoryAdapter(tempLists);
        recyclerView.setAdapter(postHistoryAdapter);

    }
}

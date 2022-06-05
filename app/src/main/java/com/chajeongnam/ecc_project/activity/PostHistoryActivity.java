package com.chajeongnam.ecc_project.activity;

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
    private RecyclerView recyclerView;
    private List<TempList> tempLists;
    private PostHistoryAdapter postHistoryAdapter;
    private ArrayList<HashMap> result=new ArrayList<>();
    private Button saveBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_history);

        tempLists=new ArrayList<>();

        recyclerView = findViewById(R.id.postHistoryRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(PostHistoryActivity.this));
        SetItemDecoration itemDecoration = new SetItemDecoration(20);
        recyclerView.addItemDecoration(itemDecoration);
        postHistoryAdapter = new PostHistoryAdapter(tempLists);
        recyclerView.setAdapter(postHistoryAdapter);


    }
}

package com.chajeongnam.ecc_project.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chajeongnam.ecc_project.R;
import com.chajeongnam.ecc_project.adapter.PostChecklistAdapter;
import com.chajeongnam.ecc_project.adapter.PostHistoryAdapter;
import com.chajeongnam.ecc_project.decoration.SetItemDecoration;
import com.chajeongnam.ecc_project.model.PostHistoryDateTempList;
import com.chajeongnam.ecc_project.model.TempList;

import java.util.ArrayList;
import java.util.List;

public class PostHistoryListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<PostHistoryDateTempList> tempLists;
    private PostHistoryAdapter postHistoryAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        tempLists=new ArrayList<>();
        tempLists.add(new PostHistoryDateTempList("2022-05-20"));
        tempLists.add(new PostHistoryDateTempList("2022-05-25"));
        tempLists.add(new PostHistoryDateTempList("2022-06-30"));
        tempLists.add(new PostHistoryDateTempList("2022-08-20"));
        setContentView(R.layout.activity_post_history);
        recyclerView = findViewById(R.id.postHistoryRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(PostHistoryListActivity.this));
        SetItemDecoration itemDecoration = new SetItemDecoration(20);
        recyclerView.addItemDecoration(itemDecoration);
        postHistoryAdapter = new PostHistoryAdapter(tempLists);
        recyclerView.setAdapter(postHistoryAdapter);



    }
}

package com.chajeongnam.ecc_project.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chajeongnam.ecc_project.R;
import com.chajeongnam.ecc_project.adapter.PostChecklistAdapter;
import com.chajeongnam.ecc_project.decoration.SetItemDecoration;
import com.chajeongnam.ecc_project.model.TempList;

import java.util.ArrayList;
import java.util.List;

public class PostChecklistActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<TempList> tempLists;
    private PostChecklistAdapter evapostChecklistAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_cheklist);
        Intent intent =getIntent();
        tempLists=(ArrayList<TempList>)getIntent().getSerializableExtra("tempLists");
        recyclerView = findViewById(R.id.evaluationPostRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(PostChecklistActivity.this));
        SetItemDecoration itemDecoration = new SetItemDecoration(20);
        recyclerView.addItemDecoration(itemDecoration);
        evapostChecklistAdapter = new PostChecklistAdapter(tempLists);
        recyclerView.setAdapter(evapostChecklistAdapter);

    }

}

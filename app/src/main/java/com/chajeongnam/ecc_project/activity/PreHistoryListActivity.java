package com.chajeongnam.ecc_project.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chajeongnam.ecc_project.R;
import com.chajeongnam.ecc_project.adapter.PreHistoryAdapter;
import com.chajeongnam.ecc_project.decoration.*;
import com.chajeongnam.ecc_project.model.PreHistoryDateTempList;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PreHistoryListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<PreHistoryDateTempList> tempLists;
    private PreHistoryAdapter postHistoryAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pre_history_list);
        recyclerView = findViewById(R.id.preHistoryRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(PreHistoryListActivity.this));
        SetItemDecoration itemDecoration = new SetItemDecoration(20);
        recyclerView.addItemDecoration(itemDecoration);


        tempLists=new ArrayList<>();
        tempLists.add(new PreHistoryDateTempList("2022-05-20"));
        tempLists.add(new PreHistoryDateTempList("2022-05-25"));
        tempLists.add(new PreHistoryDateTempList("2022-05-30"));
        tempLists.add(new PreHistoryDateTempList("2022-06-02"));

        postHistoryAdapter = new PreHistoryAdapter(tempLists);
        recyclerView.setAdapter(postHistoryAdapter);

    }


//    private void getHistories() {
//        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
//        String uid = getIntent().getStringExtra("uid");
//        HistoryList = new ArrayList<>();
//        DatabaseReference studentPostRef = mDatabase.child("histories").child(uid).child("post");
//        studentPostRef.addValueEventListener(new ValueEventListener() {
//    }


}

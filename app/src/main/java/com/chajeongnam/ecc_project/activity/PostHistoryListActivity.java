package com.chajeongnam.ecc_project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chajeongnam.ecc_project.R;
import com.chajeongnam.ecc_project.Util.FirebaseData;
import com.chajeongnam.ecc_project.adapter.PostHistoryListAdapter;
import com.chajeongnam.ecc_project.decoration.SetItemDecoration;
import com.chajeongnam.ecc_project.model.PostHistoryDateTempList;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class PostHistoryListActivity extends AppCompatActivity {
    private int startYear;
    private int startMonth;
    private int startDay;
    private int endYear;
    private int endMonth;
    private int endDay;

    private FirebaseData firebaseData;
    private DatabaseReference databaseReference;
    private RecyclerView recyclerView;
    private List<PostHistoryDateTempList> tempLists;
    private PostHistoryListAdapter postHistoryListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_history_list);
        databaseReference = FirebaseDatabase.getInstance().getReference("histories");
//        firebaseData.setPostHistoryDataFromFirebase();
        TextView startDate = (TextView) findViewById(R.id.getStartDate);
        TextView endDate = (TextView) findViewById(R.id.getEndDate);
        Intent intent = getIntent();
        ArrayList<String> date = (ArrayList<String>) getIntent().getSerializableExtra("date");
        startYear = intent.getExtras().getInt("startYear", 0);
        startMonth = intent.getExtras().getInt("startMonth", 0);
        startDay = intent.getExtras().getInt("startDay", 0);

        endYear = intent.getExtras().getInt("endYear", 0);
        endMonth = intent.getExtras().getInt("endMonth", 0);
        endDay = intent.getExtras().getInt("endDay", 0);

        startDate.setText(startYear + "/" + startMonth + "/" + startDay + "~");
        endDate.setText("~" + endYear + "/" + endMonth + "/" + endDay);


        recyclerView = findViewById(R.id.postHistoryListRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(PostHistoryListActivity.this));
        SetItemDecoration itemDecoration = new SetItemDecoration(20);
        recyclerView.addItemDecoration(itemDecoration);
        postHistoryListAdapter = new PostHistoryListAdapter(date);
        recyclerView.setAdapter(postHistoryListAdapter);


    }
}

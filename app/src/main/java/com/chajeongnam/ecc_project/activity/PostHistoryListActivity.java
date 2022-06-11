package com.chajeongnam.ecc_project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chajeongnam.ecc_project.R;
import com.chajeongnam.ecc_project.Util.FirebaseData;
import com.chajeongnam.ecc_project.adapter.PostHistoryListAdapter;
import com.chajeongnam.ecc_project.decoration.SetItemDecoration;
import com.chajeongnam.ecc_project.model.PostHistoryDateTempList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
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
    private List<String> tempLists;
    private PostHistoryListAdapter postHistoryListAdapter;
    private TextView start, end;
    private Button goCGraph;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_history_list);
        tempLists = new ArrayList<>();
        start = findViewById(R.id.getStartDate);
        end = findViewById(R.id.getEndDate);
        goCGraph = findViewById(R.id.goCGraph);
        Intent intent = getIntent();
        startYear = intent.getExtras().getInt("startYear");
        startMonth = intent.getExtras().getInt("startMonth");
        startDay = intent.getExtras().getInt("startDay");

        endYear = intent.getExtras().getInt("endYear");
        endMonth = intent.getExtras().getInt("endMonth");
        endDay = intent.getExtras().getInt("endDay");

        start.setText(startYear + "/" + startMonth + "/" + startDay + "~~");
        end.setText(endYear + "/" + endMonth + "/" + endDay);

        databaseReference = FirebaseDatabase.getInstance().getReference("histories").child("-N3J6Cm3A_4feS07jZmi").child("post").child("보조공학").child("OCR");

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

            String postHistoryDateTempList;

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    if (dataSnapshot.getKey().equals("recent")) {
                        continue;
                    } else {
                        postHistoryDateTempList = dataSnapshot.getKey();
                    }

                    String[] splitList = postHistoryDateTempList.split("-");
                    if (Integer.parseInt(splitList[0]) > startYear || Integer.parseInt(splitList[0]) < endYear) {

                        if (Integer.parseInt(splitList[1]) > startMonth || Integer.parseInt(splitList[1]) < endMonth) {

                            if (Integer.parseInt(splitList[2]) > startDay || Integer.parseInt(splitList[2]) < endDay) {

                                tempLists.add(postHistoryDateTempList);
                                recyclerView = findViewById(R.id.postHistoryListRecyclerView);
                                recyclerView.setLayoutManager(new LinearLayoutManager(PostHistoryListActivity.this));
                                SetItemDecoration itemDecoration = new SetItemDecoration(20);
                                recyclerView.addItemDecoration(itemDecoration);
                                postHistoryListAdapter = new PostHistoryListAdapter(tempLists);
                                recyclerView.setAdapter(postHistoryListAdapter);
                            }
                        }
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        goCGraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PostHistoryListActivity.this, CGraphActivity.class);
                startActivity(intent);
            }
        });

        databaseReference = FirebaseDatabase.getInstance().getReference("histories");
//        firebaseData.setPostHistoryDataFromFirebase();
        TextView startDate = (TextView) findViewById(R.id.getStartDate);
        TextView endDate = (TextView) findViewById(R.id.getEndDate);
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

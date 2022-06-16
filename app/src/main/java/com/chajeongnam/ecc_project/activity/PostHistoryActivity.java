package com.chajeongnam.ecc_project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chajeongnam.ecc_project.R;
import com.chajeongnam.ecc_project.adapter.PostChecklistAdapter;
import com.chajeongnam.ecc_project.adapter.PostHistoryAdapter;
import com.chajeongnam.ecc_project.decoration.SetItemDecoration;
import com.chajeongnam.ecc_project.model.PostHistoryResult;
import com.chajeongnam.ecc_project.model.Student;
import com.chajeongnam.ecc_project.model.TempList;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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
    private String date;

    private DatabaseReference databaseReference;
    private RecyclerView recyclerView;
    private List<PostHistoryResult> postHistoryResults;
    private PostHistoryAdapter postHistoryAdapter;
    private TextView  userName, infoTextHistoryArea,postHistoryDate;
    private Student student;
    private String category, area;
    private ArrayList<HashMap> result = new ArrayList<>();
    private Button saveBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_history);
        Intent intent = getIntent();
        Intent putDatePostHistoryAdapter = new Intent(PostHistoryActivity.this, PostHistoryAdapter.class);
        userName = findViewById(R.id.userName);
        infoTextHistoryArea = findViewById(R.id.info_text_history_area);
        postHistoryDate=findViewById(R.id.postHistoryDate);
        startYear = intent.getExtras().getInt("startYear");
        startMonth = intent.getExtras().getInt("startMonth");
        startDay = intent.getExtras().getInt("startDay");

        endYear = intent.getExtras().getInt("endYear");
        endMonth = intent.getExtras().getInt("endMonth");
        endDay = intent.getExtras().getInt("endDay");
        date = intent.getExtras().getString("date");

        student = intent.getParcelableExtra("student");
        category = intent.getStringExtra("category");
        area = intent.getStringExtra("area");

        userName.setText(student.getName());
        infoTextHistoryArea.setText(area);
        postHistoryDate.setText(date);
        putDatePostHistoryAdapter.putExtra("date", date);

        Log.d("날짜", date);
        postHistoryResults = new ArrayList<>();

//        인텐트에서 넘겨준 값으로 바꿔야함
        databaseReference = FirebaseDatabase.getInstance().getReference("histories").child(student.getUid()).child("post").child("보조공학").child("OCR").
                child(date);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    postHistoryResults.add(dataSnapshot.getValue(PostHistoryResult.class));
                }
                recyclerView = findViewById(R.id.postHistoryRecyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(PostHistoryActivity.this));
                SetItemDecoration itemDecoration = new SetItemDecoration(20);
                recyclerView.addItemDecoration(itemDecoration);
                postHistoryAdapter = new PostHistoryAdapter(postHistoryResults,date);
                recyclerView.setAdapter(postHistoryAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }
}

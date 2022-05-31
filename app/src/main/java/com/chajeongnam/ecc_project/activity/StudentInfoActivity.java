package com.chajeongnam.ecc_project.activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.chajeongnam.ecc_project.R;
import com.chajeongnam.ecc_project.adapter.StudentInfoHistoryAdapter;
import com.chajeongnam.ecc_project.model.Category;
import com.chajeongnam.ecc_project.model.History;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class StudentInfoActivity extends AppCompatActivity {

    List<History> preHistoryList, postHistoryList, historyList;
    RecyclerView recentHistoryRecyclerView;
    StudentInfoHistoryAdapter studentInfoHistoryAdapter;
    MaterialCardView viewPreTestButton, viewPostTestButton;
    TextView studentProfileClassTextView, studentProfileNameTextView, viewPreTestButtonText, viewPostTestButtonText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);

        setActionbar();
        setDefaultUI();
//        getData();
        getPreHistories();
        getPostHistories();

    }

    private void setDefaultUI(){
        studentProfileClassTextView = findViewById(R.id.studentProfileClassTextView);
        studentProfileNameTextView = findViewById(R.id.studentProfileNameTextView);

        viewPreTestButtonText = findViewById(R.id.viewPreTestButtonText);
        viewPreTestButton = findViewById(R.id.viewPreTestButton);
        viewPreTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeButtonUI(viewPreTestButton, viewPreTestButtonText);
                changeButtonUI(viewPostTestButton, viewPostTestButtonText);
                toPreRecyclerViewList();
            }
        });

        viewPostTestButtonText = findViewById(R.id.viewPostTestButtonText);
        viewPostTestButton = findViewById(R.id.viewPostTestButton);
        viewPostTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                changeButtonUI(viewPostTestButton, viewPostTestButtonText);
                changeButtonUI(viewPreTestButton, viewPreTestButtonText);
                toPostRecyclerViewList();
            }
        });


        Intent intent = getIntent();

        studentProfileClassTextView.setText(intent.getStringExtra("grade") );

        studentProfileNameTextView.setText(intent.getStringExtra("name") );

    }

    private void changeButtonUI(MaterialCardView button, TextView textView){
        ColorStateList color = button.getCardBackgroundColor();
//        int textViewColor = textView.getCurrentTextColor();
        if ( color == ContextCompat.getColorStateList(StudentInfoActivity.this, R.color.blue_500)){
            button.setCardBackgroundColor(ContextCompat.getColorStateList(StudentInfoActivity.this, R.color.white));
            textView.setTextColor(ContextCompat.getColor(StudentInfoActivity.this, R.color.black));
        }else{
            button.setCardBackgroundColor(ContextCompat.getColorStateList(StudentInfoActivity.this, R.color.blue_500));
            textView.setTextColor(ContextCompat.getColor(StudentInfoActivity.this, R.color.white));
        }
    }

    private void setActionbar(){
        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.layout_actionbar);
        TextView textView = findViewById(R.id.titleTextView);
        textView.setText("학생 정보");
        ImageButton imageButton = findViewById(R.id.backImageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setRecyclerView(){
        historyList = new ArrayList<>();
        historyList.addAll(preHistoryList);
        recentHistoryRecyclerView = findViewById(R.id.recentHistoryRecyclerView);
        recentHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(StudentInfoActivity.this));
        studentInfoHistoryAdapter = new StudentInfoHistoryAdapter(historyList);
        recentHistoryRecyclerView.setAdapter(studentInfoHistoryAdapter);
    }

//    private void getData(){
//        preHistoryList = new ArrayList<>();
//        preHistoryList.add(new History(new Category("점자","한글 점자"), "2022-04-30"));
//        preHistoryList.add(new History(new Category("일상생활기술","식생활"), "2022-04-30"));
//        preHistoryList.add(new History(new Category("보조 공학","OCR"), "2022-04-30"));
//        preHistoryList.add(new History(new Category("점자","기호 점자"), "2022-04-30"));
//        preHistoryList.add(new History(new Category("보조 공학","스크린리더 사용"), "2022-04-30"));
//        preHistoryList.add(new History(new Category("보조 공학","컴퓨터 및 프로그램 운영"), "2022-04-30"));
//        preHistoryList.add(new History(new Category("일상생활기술","자기주장 및 보호"), "2022-04-30"));
//
//        postHistoryList = new ArrayList<>();
//        postHistoryList.add(new History(new Category("점자","기호 점자"), "2022-05-30"));
//        postHistoryList.add(new History(new Category("보조 공학","스크린리더 사용"), "2022-05-30"));
//        postHistoryList.add(new History(new Category("보조 공학","컴퓨터 및 프로그램 운영"), "2022-05-30"));
//        postHistoryList.add(new History(new Category("일상생활기술","자기주장 및 보호"), "2022-05-30"));
//        postHistoryList.add(new History(new Category("점자","한글 점자"), "2022-05-30"));
//        postHistoryList.add(new History(new Category("일상생활기술","식생활"), "2022-05-30"));
//        postHistoryList.add(new History(new Category("보조 공학","OCR"), "2022-05-30"));
//
//        setRecyclerView();
//    }

    private void getPreHistories(){
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        String uid = getIntent().getStringExtra("uid");
        DatabaseReference studentPreRef = mDatabase.child("histories").child(uid).child("pre");
        preHistoryList = new ArrayList<>();
        studentPreRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String category = dataSnapshot.getKey();
                    Map<String, Object> areaMap = (Map<String, Object>) dataSnapshot.getValue();
                    String area = (String) areaMap.keySet().toArray()[0];
                    List<Object> history = new ArrayList<Object>(areaMap.values());
                    Map<String, Object> historyMap = (Map<String, Object>) history.get(0);
                    String date = (String) historyMap.keySet().toArray()[0];

//                    List<Object> als = new ArrayList<Object>(maps.values());
//                    Map<Object, Object> mapss = (Map<Object, Object>) als.get(0);
//                    boolean result = (boolean) mapss.get("result");
//                    int score;
//                    if(result) score = 0;
//                    else score = 4;
//                    String content = (String) mapss.get("content");
                    preHistoryList.add(new History(new Category(category, area), date));
                }
                setRecyclerView();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getPostHistories(){
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
        String uid = getIntent().getStringExtra("uid");
        postHistoryList = new ArrayList<>();
        DatabaseReference studentPostRef = mDatabase.child("histories").child(uid).child("post");
        studentPostRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String category = dataSnapshot.getKey();
                    Map<String, Object> areaMap = (Map<String, Object>) dataSnapshot.getValue();
                    String area = (String) areaMap.keySet().toArray()[0];
                    List<Object> history = new ArrayList<Object>(areaMap.values());
                    Map<String, Object> historyMap = (Map<String, Object>) history.get(0);
                    String date = (String) historyMap.keySet().toArray()[0];


                    postHistoryList.add(new History(new Category(category, area), date));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void toPostRecyclerViewList(){
        historyList.clear();
        historyList.addAll(postHistoryList);
        studentInfoHistoryAdapter.notifyDataSetChanged();
    }

    private void toPreRecyclerViewList(){
        historyList.clear();
        historyList.addAll(preHistoryList);
        studentInfoHistoryAdapter.notifyDataSetChanged();
    }

    private void setOnClick(){

    }

}
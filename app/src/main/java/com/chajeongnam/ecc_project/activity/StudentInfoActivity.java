package com.chajeongnam.ecc_project.activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.chajeongnam.ecc_project.R;
import com.chajeongnam.ecc_project.adapter.StudentInfoHistoryAdapter;
import com.chajeongnam.ecc_project.model.Category;
import com.chajeongnam.ecc_project.model.History;
import com.chajeongnam.ecc_project.model.Student;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

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
    Student student;
    Category category;
//    boolean isPost;
    MaterialCardView viewPreTestButton, viewPostTestButton;
    TextView studentProfileClassTextView, studentProfileNameTextView, viewPreTestButtonText, viewPostTestButtonText;
    Button startPreTestButton, startPostTestButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);

        setActionbar();
        setDefaultUI();
//        getData();


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

        startPreTestButton = findViewById(R.id.startPreTestButton);
        startPreTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =  new Intent(StudentInfoActivity.this, PreCheckListActivity.class);
                intent.putExtra("student", student);
                startActivity(intent);
            }
        });

        startPostTestButton = findViewById(R.id.startPostTestButton);
        startPostTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentInfoActivity.this, PostChecklistActivity.class);
                intent.putExtra("student", student);
                startActivity(intent);
            }
        });

        Intent intent = getIntent();

        String studentUid = intent.getStringExtra("uid");
        FirebaseDatabase.getInstance().getReference().child("students").child(studentUid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                student = task.getResult().getValue(Student.class);

                getPreHistories();
                getPostHistories();
            }
        });
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
        studentInfoHistoryAdapter = new StudentInfoHistoryAdapter(historyList, student, true);
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
                preHistoryList.clear();

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String category = dataSnapshot.getKey();
                    Object object = dataSnapshot.getValue(Object.class);
                    String json = new Gson().toJson(object);
                    JsonParser parser = new JsonParser();
                    JsonObject root =  parser.parse(json).getAsJsonObject();
                    for(Entry<String, JsonElement> entry : root.entrySet()){
                        String area = entry.getKey();
                        String recent = root.get(area).getAsJsonObject().get("recent").toString();
                        //큰 따옴표 삭제
                        String filteredRecent = recent.substring(1, recent.length() - 1);
                        preHistoryList.add(new History(new Category(category, (String) area), filteredRecent));
                        Log.d("pre history", category + area + filteredRecent);
                    }
                }
//                setRecyclerView();
                toPreRecyclerViewList();
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
                postHistoryList.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String category = dataSnapshot.getKey();
                    Object object = dataSnapshot.getValue(Object.class);
                    String json = new Gson().toJson(object);
                    JsonParser parser = new JsonParser();
                    JsonObject root =  parser.parse(json).getAsJsonObject();
                    for(Entry<String, JsonElement> entry : root.entrySet()){
                        String area = entry.getKey();
                        String recent = root.get(area).getAsJsonObject().get("recent").toString();
                        //큰 따옴표 삭제
                        String filteredRecent = recent.substring(1, recent.length() - 1);
                        postHistoryList.add(new History(new Category(category, (String) area), filteredRecent));
                        Log.d("postHistoryList", category + area + filteredRecent);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void toPostRecyclerViewList(){
        historyList = new ArrayList<>();
        historyList.addAll(postHistoryList);
        recentHistoryRecyclerView = findViewById(R.id.recentHistoryRecyclerView);
        recentHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(StudentInfoActivity.this));
        studentInfoHistoryAdapter = new StudentInfoHistoryAdapter(historyList, student, true);
        recentHistoryRecyclerView.setAdapter(studentInfoHistoryAdapter);
//        historyList.clear();
//        historyList.addAll(postHistoryList);
//        studentInfoHistoryAdapter.setIsPost(true);
//        studentInfoHistoryAdapter.notifyDataSetChanged();
    }

    private void toPreRecyclerViewList(){
        historyList = new ArrayList<>();
        historyList.addAll(preHistoryList);
        recentHistoryRecyclerView = findViewById(R.id.recentHistoryRecyclerView);
        recentHistoryRecyclerView.setLayoutManager(new LinearLayoutManager(StudentInfoActivity.this));
        studentInfoHistoryAdapter = new StudentInfoHistoryAdapter(historyList, student, false);
        recentHistoryRecyclerView.setAdapter(studentInfoHistoryAdapter);
//        historyList.clear();
//        historyList.addAll(preHistoryList);
//        studentInfoHistoryAdapter.setIsPost(false);
//        studentInfoHistoryAdapter.notifyDataSetChanged();
    }

    private void setOnClick(){

    }

}
package com.chajeongnam.ecc_project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.chajeongnam.ecc_project.R;
import com.chajeongnam.ecc_project.adapter.PreChecklistAdapter;
import com.chajeongnam.ecc_project.model.PreChecklist;
import com.chajeongnam.ecc_project.model.Student;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PreHistoryActivity extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private PreChecklistAdapter adapter;
    private ListView listview;
    private ArrayList<PreChecklist> checklist= new ArrayList<>();
    private ArrayList<String> contentlist= new ArrayList<String>();
    private String studentdate;
    String category = getIntent().getStringExtra("category");
    String area = getIntent().getStringExtra("area");
    Student student = (Student) getIntent().getParcelableExtra("student");




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_history);
        setActionbar();

        adapter = new PreChecklistAdapter(this,checklist);

        listview = (ListView) findViewById(R.id.prehistoryView);
        listview.setAdapter(adapter);

        //날짜설정
        mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference myRef = mDatabase.child("histories");
        DatabaseReference ChecklistRef = myRef.child("pre").child(category).child(area).child("recent");
        ChecklistRef.addListenerForSingleValueEvent (new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String date = dataSnapshot.getValue(String.class);
                    studentdate = date;
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });

        setDefaultUI();
        getPrehistory();


    }

    private void setDefaultUI(){
        TextView sclass = (TextView) findViewById(R.id.prehistoryclass_grade);
        TextView sname = (TextView) findViewById(R.id.prehistoryname);
        TextView sarea = (TextView) findViewById(R.id.prehistoryarea);
        TextView sdate = (TextView) findViewById(R.id.prehistorydate);

        String studentclass = student.getAttrClass();
        String studentgrade = student.getGrade();
        String studenttotal = studentgrade+ "학년 " + studentclass+ "반";
        String studentname = student.getName();


        sname.setText(studentname);
        sclass.setText(studenttotal);
        sarea.setText(area);


        sdate.setText(studentdate);



    }

        public void getPrehistory() {
        String uid = student.getUid();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference myRef = mDatabase.child("histories").child(uid);
        DatabaseReference ChecklistRef = myRef.child("pre").child(category).child(area).child(studentdate);
        ChecklistRef.addListenerForSingleValueEvent (new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                checklist.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    PreChecklist pre = dataSnapshot.getValue(PreChecklist.class);
                    checklist.add(pre);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });
        adapter= new PreChecklistAdapter(this,checklist);
        listview.setAdapter(adapter);


    }
    private void setActionbar() {
        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.layout_actionbar);
        ImageButton imageButton = findViewById(R.id.backImageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
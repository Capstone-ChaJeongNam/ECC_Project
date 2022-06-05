package com.chajeongnam.ecc_project.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.chajeongnam.ecc_project.R;
import com.chajeongnam.ecc_project.adapter.PreChecklistAdapter;
import com.chajeongnam.ecc_project.model.Category;
import com.chajeongnam.ecc_project.model.PreChecklist;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import androidx.appcompat.app.ActionBar;


public class PreCheckListActivity extends AppCompatActivity {
    private Button saveBtn;
    private ListView listview;
    private PreChecklistAdapter adapter;
    private DatabaseReference mDatabase;
    private ArrayList<PreChecklist> checklist;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_checklist);
        setActionbar();
        saveBtn=findViewById(R.id.savePreTestBtn);


        listview = (ListView) findViewById(R.id.listview1);

        getPrechecklist();


        //파이어베이스 데이터 저장//
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.addItem("4","기본모음자 이외 모음자를 알고 읽고 쓴다.");


            }
        });
    }

    String category = "보조공학";
    String area = "OCR";

    //파이어베이스 데이터 로드//
    private void getPrechecklist() {
        checklist = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference myRef = mDatabase.child("ECC");
        DatabaseReference ChecklistRef = myRef.child(category).child(area);
        ChecklistRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                checklist.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    PreChecklist pre = dataSnapshot.getValue(PreChecklist.class);
                    checklist.add(pre);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });
        adapter= new PreChecklistAdapter(checklist, this);
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
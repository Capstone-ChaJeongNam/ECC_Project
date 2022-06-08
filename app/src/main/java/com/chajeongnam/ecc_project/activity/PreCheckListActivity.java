package com.chajeongnam.ecc_project.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import androidx.appcompat.app.ActionBar;


public class PreCheckListActivity extends AppCompatActivity {
    private Button saveBtn;
    private ListView listview;
    private PreChecklistAdapter adapter;
    private DatabaseReference mDatabase;
    private ArrayList<PreChecklist> checklist= new ArrayList<>();
    private ArrayList<String> contentlist= new ArrayList<String>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_checklist);
        setActionbar();
        saveBtn=findViewById(R.id.savePreTestBtn);
        listview = (ListView) findViewById(R.id.listview1);


        getPrechecklist();
        SparseBooleanArray checkedItems = listview.getCheckedItemPositions();

        //파이어베이스 데이터 저장//
        saveBtn.setOnClickListener(new View.OnClickListener() {
            String key = getIntent().getStringExtra("student");
            String category = getIntent().getStringExtra("category");
            String area = getIntent().getStringExtra("area");

            @Override
            public void onClick(View view) {
                mDatabase = FirebaseDatabase.getInstance().getReference();
                DatabaseReference myRef = mDatabase.child("histories");
                DatabaseReference ChecklistRef = myRef.child(key).child("pre").child(category).child(area);
                ChecklistRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for(int i =0; i< adapter.getCount(); i++){
                            ChecklistRef.child("2022-04-13").child(String.valueOf(i)).child("result").setValue(checkedItems.get(i));
                            ChecklistRef.child("2022-04-13").child(String.valueOf(i)).child("content").setValue(contentlist.get(i));
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });




            }
        });
    }



    //파이어베이스 데이터 로드//
    public void getPrechecklist() {
        String category = getIntent().getStringExtra("category");
        String area = getIntent().getStringExtra("area");

        mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference myRef = mDatabase.child("ECC");
        DatabaseReference ChecklistRef = myRef.child(category).child(area);
        ChecklistRef.addListenerForSingleValueEvent (new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                checklist.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    PreChecklist pre = dataSnapshot.getValue(PreChecklist.class);
                    checklist.add(pre);
                    String content = pre.getContent();
                    contentlist.add(content);
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
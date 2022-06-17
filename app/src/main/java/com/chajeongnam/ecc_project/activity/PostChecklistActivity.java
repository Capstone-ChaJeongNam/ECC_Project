package com.chajeongnam.ecc_project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chajeongnam.ecc_project.R;
import com.chajeongnam.ecc_project.Util.FirebaseData;
import com.chajeongnam.ecc_project.adapter.PostChecklistAdapter;
import com.chajeongnam.ecc_project.decoration.SetItemDecoration;
import com.chajeongnam.ecc_project.model.Result;
import com.chajeongnam.ecc_project.model.Student;
import com.chajeongnam.ecc_project.model.TempList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostChecklistActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PostChecklistAdapter evapostChecklistAdapter;
    private ArrayList<HashMap> result = new ArrayList<>();
    private List<TempList> tempLists = new ArrayList<>();
    private Button saveBtn;
    private Student student;
    private int bigId;
    private Spinner spinner1, spinner2;
    private FirebaseData firebaseData = new FirebaseData();
    private String selectedItem, bigCategoryChild, mediumCategoryChild;
    private List<String> bigCategory, mediumCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_cheklist);
        Intent intent = getIntent();
        student = intent.getParcelableExtra("student");
        saveBtn = findViewById(R.id.savePostTestBtn);

        spinner1 = (Spinner) findViewById(R.id.bigCategory);
        spinner2 = (Spinner) findViewById(R.id.mediumCategory);

        DatabaseReference database = FirebaseDatabase.getInstance().getReference("ECC");
        ;
        bigCategory = new ArrayList<>();
        mediumCategory = new ArrayList<>();


        database.addListenerForSingleValueEvent(new ValueEventListener() {
            String tempBigCategory;

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    tempBigCategory = dataSnapshot.getKey();
                    bigCategory.add(tempBigCategory);


                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(PostChecklistActivity.this, android.R.layout.simple_spinner_item, bigCategory);
                spinner1.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItem = adapterView.getItemAtPosition(i).toString();
                if (selectedItem.equals("보조공학")) {
                    bigId = 0;
                } else if (selectedItem.equals("점자")) {
                    bigId = 1;
                }
                bigCategoryChild = selectedItem;

                setSpinner(bigId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItem = adapterView.getItemAtPosition(i).toString();
                tempLists.clear();
                mediumCategoryChild = selectedItem;

                database.child(bigCategoryChild).child(mediumCategoryChild).addListenerForSingleValueEvent(new ValueEventListener() {
                    TempList getDataFromFireBasetempList;

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            getDataFromFireBasetempList = snapshot.getValue(TempList.class);
                            tempLists.add(getDataFromFireBasetempList); //어레이리스트에 데이터 넣은 상태}

                        }

                        recyclerView = findViewById(R.id.evaluationPostRecyclerView);
                        recyclerView.setLayoutManager(new LinearLayoutManager(PostChecklistActivity.this));
                        SetItemDecoration itemDecoration = new SetItemDecoration(20);
                        recyclerView.addItemDecoration(itemDecoration);
                        evapostChecklistAdapter = new PostChecklistAdapter(tempLists);
                        recyclerView.setAdapter(evapostChecklistAdapter);

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }

                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


//        tempLists=(ArrayList<TempList>)getIntent().getSerializableExtra("tempLists");


//        학생 저장
        saveBtn.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                result.add(evapostChecklistAdapter.getResult());
                DatabaseReference getTempleStudent = FirebaseDatabase.getInstance().getReference("students").child(student.getUid());
                getTempleStudent.addListenerForSingleValueEvent(new ValueEventListener() {


                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        student = dataSnapshot.getValue(Student.class);


                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }

                });

                long now;
                Date mdDate;
                String recent;
                SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd");
                now = System.currentTimeMillis();
                mdDate = new Date(now);
                recent = mFormat.format(mdDate);
//해쉬맵의 key를 child로 하고 그 값을 value로 지정하여 파베에 저장

                for (Map.Entry<Integer, Result> entrySet : evapostChecklistAdapter.getResult().entrySet()) {
                    FirebaseDatabase.getInstance().getReference("histories").child(student.getUid()).child("post").child("보조공학").child("OCR").child(recent).child(String.valueOf(entrySet.getKey())).setValue(entrySet.getValue());
                }
                Toast.makeText(getApplicationContext(), "성공적으로 저장 되었습니다!", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });

    }

    protected void setSpinner(int i) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("ECC");
        mediumCategory = new ArrayList<>();

        switch (i) {
            case 0:
                database.child("보조공학").addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            mediumCategory.add(dataSnapshot.getKey());

                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(PostChecklistActivity.this, android.R.layout.simple_spinner_item, mediumCategory);
                        spinner2.setAdapter(adapter);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                break;
            case 1:
                database.child("점자").addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            mediumCategory.add(dataSnapshot.getKey());


                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(PostChecklistActivity.this, android.R.layout.simple_spinner_item, mediumCategory);
                        spinner2.setAdapter(adapter);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


        }


    }


}

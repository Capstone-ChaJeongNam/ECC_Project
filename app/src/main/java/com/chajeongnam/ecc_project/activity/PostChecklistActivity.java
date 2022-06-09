package com.chajeongnam.ecc_project.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chajeongnam.ecc_project.R;
import com.chajeongnam.ecc_project.Util.FirebaseData;
import com.chajeongnam.ecc_project.adapter.PostChecklistAdapter;
import com.chajeongnam.ecc_project.decoration.SetItemDecoration;
import com.chajeongnam.ecc_project.model.History;
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
    private HashMap<String, Object> mediumCategoryList = new HashMap<>();
    private Button saveBtn;
    private int bigId;
    private Spinner spinner1, spinner2;
    private FirebaseData firebaseData = new FirebaseData();
    private String selectedItem;
    private List<String> bigCategory, mediumCategory;

    DatabaseReference database = FirebaseDatabase.getInstance().getReference("ECC");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_cheklist);
        saveBtn = findViewById(R.id.savePostTestBtn);

        spinner1 = (Spinner) findViewById(R.id.bigCategory);
        spinner2 = (Spinner) findViewById(R.id.mediumCategory);

        DatabaseReference database = FirebaseDatabase.getInstance().getReference("ECC");
        DatabaseReference myRef = database.child("보조공학");
        DatabaseReference myRef2 = database.child("점자");
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

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            List<String> tempMediumCategory = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    tempMediumCategory.add(dataSnapshot.getKey());


                }
                mediumCategoryList.put("보조공학", tempMediumCategory);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myRef2.addListenerForSingleValueEvent(new ValueEventListener() {
            List<String> tempMediumCategory = new ArrayList<>();

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    tempMediumCategory.add(dataSnapshot.getKey());

                }
                mediumCategoryList.put("점자", tempMediumCategory);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

       spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               selectedItem = adapterView.getItemAtPosition(i).toString();
         if(selectedItem.equals("보조공학")){
             bigId=1;
         }else if(selectedItem.equals("점자")){
             bigId=2;
         }
           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });

        myRef.child("OCR").addListenerForSingleValueEvent(new ValueEventListener() {
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
//        tempLists=(ArrayList<TempList>)getIntent().getSerializableExtra("tempLists");

        saveBtn.setOnClickListener(new View.OnClickListener() {

            Student student = new Student();

            @Override
            public void onClick(View view) {
                result.add(evapostChecklistAdapter.getResult());
                DatabaseReference getTempleStudent = FirebaseDatabase.getInstance().getReference("students").child("-N3J6Cm3A_4feS07jZmi");
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

                FirebaseDatabase.getInstance().getReference("histories").child("-N3J6Cm3A_4feS07jZmi").child("post").child("보조공학").child("OCR").child(recent).setValue(evapostChecklistAdapter.getResult());


//                for (Map.Entry<String, String> entrySet : evapostChecklistAdapter.getResult().entrySet()) {
//                    Log.d("확인", entrySet.getKey() + " : " + entrySet.getValue());
//                }

            }
        });

    }


}

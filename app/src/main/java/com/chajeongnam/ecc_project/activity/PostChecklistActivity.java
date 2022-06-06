package com.chajeongnam.ecc_project.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
    private List<TempList> tempLists = new ArrayList<>();
    private PostChecklistAdapter evapostChecklistAdapter;
    private ArrayList<HashMap> result = new ArrayList<>();
    private Button saveBtn, getBtn;
    private History history;
    private FirebaseData firebaseData = new FirebaseData();
    DatabaseReference database = FirebaseDatabase.getInstance().getReference("ECC");
    DatabaseReference myRef = database.child("보조공학");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_cheklist);
        saveBtn = findViewById(R.id.savePostTestBtn);
        getBtn = findViewById(R.id.getListBtn);

        firebaseData.setDAtaFromFirebase();

        getBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getBtn.setVisibility(View.GONE);
                recyclerView = findViewById(R.id.evaluationPostRecyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(PostChecklistActivity.this));
                SetItemDecoration itemDecoration = new SetItemDecoration(20);
                recyclerView.addItemDecoration(itemDecoration);
                evapostChecklistAdapter = new PostChecklistAdapter(firebaseData.getDatas());
                recyclerView.setAdapter(evapostChecklistAdapter);

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

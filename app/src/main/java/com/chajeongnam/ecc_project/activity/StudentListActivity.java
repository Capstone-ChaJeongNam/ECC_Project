package com.chajeongnam.ecc_project.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chajeongnam.ecc_project.R;
import com.chajeongnam.ecc_project.adapter.CategoryListAdapter;
import com.chajeongnam.ecc_project.adapter.StudentListAdapter;
import com.chajeongnam.ecc_project.model.Student;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class StudentListActivity extends AppCompatActivity {
    TextView areaTextView;
    List<Student> studentList;
    List<String> studentUids;
    private DatabaseReference mDatabase;

    // ...
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        setActionbar();
        setTextView();

        getStudentsUid();
    }

    private void setActionbar() {
        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        // showing the back button in action bar
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.layout_actionbar);
        TextView textView = findViewById(R.id.titleTextView);
        textView.setText("카테고리");
        ImageButton imageButton = findViewById(R.id.backImageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
    private void setTextView() {

        areaTextView = findViewById(R.id.areaTextView);
        String area = getIntent().getStringExtra("area");
        areaTextView.setText(area);
    }

//    private void getStudentList(){
////        studentList.add(new Student("홍길동", "3학년", "A반", "2022-04-20"));
////        studentList.add(new Student("고길동", "1학년", "B반", "2022-04-22"));
////        studentList.add(new Student("노길동", "2학년", "C반", "2022-04-30"));
////        studentList.add(new Student("도길동", "3학년", "A반", "2022-04-11"));
////        studentList.add(new Student("로길동", "1학년", "E반", "2022-04-13"));
//
//
//
//    }

    private void setRecyclerView(List<Student> studentList) {
        RecyclerView recyclerView = findViewById(R.id.studentListRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(StudentListActivity.this));
        StudentListAdapter studentListAdapter = new StudentListAdapter(studentList);
        recyclerView.setAdapter(studentListAdapter);
    }

    private void getStudents() {
        studentList = new ArrayList<>();
        DatabaseReference studentRef = mDatabase.child("students");

        for (int i = 0; i < studentUids.size(); i++) {
            studentRef.child(studentUids.get(i)).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> task) {
                    if (!task.isSuccessful()) {
                        Log.e("firebase", "Error getting data", task.getException());
                    } else {
//                        Log.d("firebase", String.valueOf(task.getResult().getValue()));
                        studentList.add(task.getResult().getValue(Student.class));
                    }
                    if (studentList.size() == studentUids.size())
                        setRecyclerView(studentList);

                }
            });

        }


    }

    private void getStudentsUid() {
        studentUids = new ArrayList<>();
        String category = getIntent().getStringExtra("category").trim();
        String area = getIntent().getStringExtra("area");
//        area = area.trim();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference Ref = mDatabase.child("category").child(category).child(area);
        Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    studentUids.add(dataSnapshot.getKey());
                    Log.d("Student", dataSnapshot.getKey());
                }
//                if(studentUids.size() == snapshot.)
                getStudents();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

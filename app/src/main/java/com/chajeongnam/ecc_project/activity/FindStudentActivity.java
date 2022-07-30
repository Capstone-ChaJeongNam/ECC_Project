package com.chajeongnam.ecc_project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.chajeongnam.ecc_project.R;
import com.chajeongnam.ecc_project.adapter.StudentListAdapter;
import com.chajeongnam.ecc_project.adapter.StudentSearchResultAdapter;
import com.chajeongnam.ecc_project.model.Category;
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

public class FindStudentActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    FloatingActionButton addStudentButton;
    private DatabaseReference mDatabase;
    List<Student> studentList;
    List<String> studentUids;


    private StudentSearchResultAdapter studentSearchResultAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_student);
        addStudentButton = findViewById(R.id.addStudentButton);

        addStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*
                  TODO 학생 추가 화면 추가 및 이동
                 */
                startActivity(new Intent(FindStudentActivity.this, AddStudentActivity.class));

            }
        });
        setActionbar();
        getStudents();
//        getStudentList();
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
        textView.setText("학생 검색");
        ImageButton imageButton = findViewById(R.id.backImageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void search(List<Student> studentList, EditText studentSearchEditText){
        List<Student> resultList =new ArrayList<>();
        for(Student student : studentList){
            if (student.getName().trim().contains(studentSearchEditText.getText().toString().trim())){
                Log.d("student: ", student.getName());
                resultList.add(student);
            }
        }
        studentSearchEditText.setText("");
        setRecyclerView(resultList);
    }

    private void setSearchButton(List<Student> studentList){
        EditText studentSearchEditText = findViewById(R.id.studentSearchEditText);
        ImageButton searchStudentButton = findViewById(R.id.searchStudentButton);

        searchStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search(studentList, studentSearchEditText);
            }
        });

        studentSearchEditText.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                List<Student> resultList =new ArrayList<>();
                switch (keyCode){
                case KeyEvent.KEYCODE_ENTER:
                    // Perform action on key press
                    search(studentList, studentSearchEditText);
                    return true;
                }

                return false;
            }
        });
    }

//    private void getStudentList(){
//        List<Student> studentList = new ArrayList<>();
////        studentList.add(new Student("홍길동", "3학년", "A반", "2022-04-20"));
////        studentList.add(new Student("고길동", "1학년", "B반", "2022-04-22"));
////        studentList.add(new Student("노길동", "2학년", "C반", "2022-04-30"));
////        studentList.add(new Student("도길동", "3학년", "A반", "2022-04-11"));
////        studentList.add(new Student("로길동", "1학년", "E반", "2022-04-13"));
//
//        setSearchButton(studentList);
//    }

    private void setRecyclerView(List<Student> studentList) {
        RecyclerView recyclerView = findViewById(R.id.studentSearchResults);
        recyclerView.setLayoutManager(new LinearLayoutManager(FindStudentActivity.this));
        StudentSearchResultAdapter studentSearchResultAdapter = new StudentSearchResultAdapter(studentList);
        recyclerView.setAdapter(studentSearchResultAdapter);
    }

//    private void getStudent(){
//        mDatabase = FirebaseDatabase.getInstance().getReference();
//        DatabaseReference Ref = mDatabase.child("students");
//        Ref.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    studentList.add(dataSnapshot.getValue(Student.class));
//                    Log.d("Student", dataSnapshot.getValue(Student.class).getName());
//                }
//            }
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }

    private void getStudents() {
        mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference studentRef = mDatabase.child("students");

        studentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                studentList = new ArrayList<>();

                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    studentList.add(dataSnapshot.getValue(Student.class));
                }

                setSearchButton(studentList);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}

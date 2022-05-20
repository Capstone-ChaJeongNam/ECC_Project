package com.chajeongnam.ecc_project.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.chajeongnam.ecc_project.R;
import com.chajeongnam.ecc_project.adapter.CategoryListAdapter;
import com.chajeongnam.ecc_project.adapter.StudentListAdapter;
import com.chajeongnam.ecc_project.model.Student;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class StudentListActivity extends AppCompatActivity {
    TextView areaTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_list);
        setActionbar();
        setTextView();

        getStudentList();
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
        textView.setText("카테고리");
        ImageButton imageButton = findViewById(R.id.backImageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void setTextView(){
        String category = getIntent().getStringExtra("category");
        String area = getIntent().getStringExtra("area");

        areaTextView = findViewById(R.id.areaTextView);
        areaTextView.setText(area);
    }

    private void getStudentList(){
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("홍길동", "3학년", "A반", "2022-04-20"));
        studentList.add(new Student("고길동", "1학년", "B반", "2022-04-22"));
        studentList.add(new Student("노길동", "2학년", "C반", "2022-04-30"));
        studentList.add(new Student("도길동", "3학년", "A반", "2022-04-11"));
        studentList.add(new Student("로길동", "1학년", "E반", "2022-04-13"));

        setRecyclerView(studentList);


    }

    private void setRecyclerView(List<Student> studentList) {
        RecyclerView recyclerView = findViewById(R.id.studentListRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(StudentListActivity.this));
        StudentListAdapter studentListAdapter = new StudentListAdapter(studentList);
        recyclerView.setAdapter(studentListAdapter);
    }
}

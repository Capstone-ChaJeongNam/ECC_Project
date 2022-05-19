package com.chajeongnam.ecc_project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.chajeongnam.ecc_project.R;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    ImageView categoryImageView, studentImageView;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);

        init();
        setClick();
    }

    private void init(){
        categoryImageView = findViewById(R.id.categoryImageView);
        studentImageView = findViewById(R.id.studentImageView);
    }

    private void setClick(){
        categoryImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, CategoryListActivity.class));
            }
        });

        studentImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, FindStudentActivity.class));
            }
        });

    }
}

package com.unittest.ecc_project;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

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

    }
}

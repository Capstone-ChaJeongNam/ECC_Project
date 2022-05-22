package com.chajeongnam.ecc_project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.chajeongnam.ecc_project.R;
import com.chajeongnam.ecc_project.adapter.CategoryListAdapter;
import com.chajeongnam.ecc_project.model.Category;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryListActivity extends AppCompatActivity {
    private Button button;

    private RecyclerView recyclerView;
    private CategoryListAdapter categoryListAdapter;
    private List<Category> categoryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        setActionbar();


        categoryList = new ArrayList<>();
        categoryList.add(new Category("점자", "기초 점자"));
        categoryList.add(new Category("점자", "수학 점자"));
        categoryList.add(new Category("점자", "한글 점자"));
        categoryList.add(new Category("점자", "촉각 훈련"));
        categoryList.add(new Category("일상생활기술", "실생활"));
        categoryList.add(new Category("일상생활기술", "건강과 안전"));
        recyclerView = findViewById(R.id.categoryListRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(CategoryListActivity.this));
        categoryListAdapter = new CategoryListAdapter(categoryList);
        recyclerView.setAdapter(categoryListAdapter);
//        mumu: create new method that go pretest
        toPretest();

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

//    mumu: new method 22.05.22
    private void toPretest() {
        button = findViewById(R.id.toPretest);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CategoryListActivity.this, EvaPreTestListActivity.class));
            }
        });

    }
}

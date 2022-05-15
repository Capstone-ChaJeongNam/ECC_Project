package com.chajeongnam.ecc_project;

import android.os.Bundle;

import com.chajeongnam.ecc_project.adapter.CategoryListAdapter;
import com.chajeongnam.ecc_project.model.Category;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CategoryListAdapter categoryListAdapter;
    private List<Category> categoryList;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);

        categoryList = new ArrayList<>();
        categoryList.add(new Category("점자","기초 점자"));
        categoryList.add(new Category("점자","수학 점자"));
        categoryList.add(new Category("점자","한글 점자"));
        categoryList.add(new Category("점자","촉각 훈련"));
        categoryList.add(new Category("일상생활기술","실생활"));
        categoryList.add(new Category("일상생활기술","건강과 안전"));
        recyclerView = findViewById(R.id.categoryListRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(CategoryListActivity.this));
        categoryListAdapter = new CategoryListAdapter(categoryList);
        recyclerView.setAdapter(categoryListAdapter);


    }
}

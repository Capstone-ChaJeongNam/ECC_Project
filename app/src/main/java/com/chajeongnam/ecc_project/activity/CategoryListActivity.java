package com.chajeongnam.ecc_project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.chajeongnam.ecc_project.R;
import com.chajeongnam.ecc_project.adapter.CategoryListAdapter;
import com.chajeongnam.ecc_project.model.Category;
import com.chajeongnam.ecc_project.model.TempList;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryListActivity extends AppCompatActivity {

//    mumu: goto pre and post test

    private Button buttonToPre;
    private Button buttonToPost;

    private RecyclerView recyclerView;
    private CategoryListAdapter categoryListAdapter;
    private List<Category> categoryList;
    private List<TempList> tempLists;

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
        tempLists = new ArrayList<>();
        tempLists.add(new TempList("1.초성 자음자와 겹글자를 알고 읽고 쓴다."));
        tempLists.add(new TempList("2. 기본 모음자를 알고 읽고 쓴다."));
        tempLists.add(new TempList("3. 수표와 수를 알고 읽고 쓴다."));
        tempLists.add(new TempList("4. 기본모음자 이외 모음자를 알고 읽고 쓴다. "));
        tempLists.add(new TempList("5. 기본받침, 쌍받침, 겹받침을 알고 읽고 쓴다."));
        tempLists.add(new TempList("6. 지팡이의 구조와 용도를 안다."));
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
        buttonToPre = findViewById(R.id.toPretest);
        buttonToPost=findViewById(R.id.toPosttest);


        buttonToPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CategoryListActivity.this, EvaPreTestListActivity.class);

                intent.putExtra("tempLists", (Serializable) tempLists);
                startActivity(intent);

            }
        });

        buttonToPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CategoryListActivity.this, PostChecklistActivity.class);

                intent.putExtra("tempLists", (Serializable) tempLists);
                startActivity(intent);
            }
        });


    }
}

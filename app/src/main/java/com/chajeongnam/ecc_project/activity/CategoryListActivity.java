package com.chajeongnam.ecc_project.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.chajeongnam.ecc_project.R;
import com.chajeongnam.ecc_project.adapter.CategoryListAdapter;
import com.chajeongnam.ecc_project.model.Category;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONException;
import org.json.JSONObject;

public class CategoryListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CategoryListAdapter categoryListAdapter;
    private List<Category> categoryList;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        setActionbar();


//        categoryList = new ArrayList<>();
//        categoryList.add(new Category("점자","기초 점자"));
//        categoryList.add(new Category("점자","수학 점자"));
//        categoryList.add(new Category("점자","한글 점자"));
//        categoryList.add(new Category("점자","촉각 훈련"));
//        categoryList.add(new Category("일상생활기술","실생활"));
//        categoryList.add(new Category("일상생활기술","건강과 안전"));


        getCategories();
    }
    Iterable<DataSnapshot> children;

    private void getCategories() {
        categoryList = new ArrayList<>();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        DatabaseReference studentRef = mDatabase.child("ECC");
        studentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    dataSnapshot.getKey();
                    Object object = dataSnapshot.getValue(Object.class);
                    String jsonString = new Gson().toJson(object);
                    try {
                        Map<String, Object> jsonObject = new ObjectMapper().readValue(jsonString, Map.class);
                        String[] arr = (String[]) jsonObject.keySet().toArray();
                        JSONObject jObject = new JSONObject(jsonString);
                        Log.d("Json", jObject.keys().toString());
                    } catch (JSONException | JsonProcessingException e) {
                        e.printStackTrace();
                    }
//                    categoryList.add(new Category(dataSnapshot.getKey(),  dataSnapshot.getChildren()));
                }
//                recyclerView = findViewById(R.id.categoryListRecyclerView);
//                recyclerView.setLayoutManager(new LinearLayoutManager(CategoryListActivity.this));
//                categoryListAdapter = new CategoryListAdapter(categoryList);
//                recyclerView.setAdapter(categoryListAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void setActionbar(){
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
}

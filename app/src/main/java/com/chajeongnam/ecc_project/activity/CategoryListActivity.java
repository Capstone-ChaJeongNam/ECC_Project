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
import java.util.Arrays;
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
        
        getCategories();
    }

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
                        Map jsonObject = new ObjectMapper().readValue(jsonString, Map.class);
                        Object[] arr = (Object[]) jsonObject.keySet().toArray();
                        for(Object area : arr){
                            categoryList.add(new Category(dataSnapshot.getKey(),  setTextLine(area.toString())));
                        }

                    } catch (JsonProcessingException e) {
                        e.printStackTrace();
                    }
                }
                recyclerView = findViewById(R.id.categoryListRecyclerView);
                recyclerView.setLayoutManager(new LinearLayoutManager(CategoryListActivity.this));
                categoryListAdapter = new CategoryListAdapter(categoryList);
                recyclerView.setAdapter(categoryListAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private String setTextLine(String area){

        if(area.length() >5){
            int count = 0;

            List<String> chArray = Arrays.asList(area.split("(?!^)"));
            List<Integer> locations = new ArrayList<>();
            for(int i = 0; i< chArray.size(); i++){
                if(chArray.get(i).equals(" ")){
                    locations.add(i);
                    count += 1;
                }
            }
            if(count != 0)
                return area.substring(0, locations.get(count/2)) + "\n" + area.substring(locations.get(count/2) + 1);
            else
                return area;

        }else{
            return area;
        }

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

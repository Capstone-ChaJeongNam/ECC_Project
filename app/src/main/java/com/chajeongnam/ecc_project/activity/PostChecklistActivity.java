package com.chajeongnam.ecc_project.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chajeongnam.ecc_project.R;
import com.chajeongnam.ecc_project.adapter.PostChecklistAdapter;
import com.chajeongnam.ecc_project.decoration.SetItemDecoration;
import com.chajeongnam.ecc_project.model.TempList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostChecklistActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<TempList> tempLists;
    private PostChecklistAdapter evapostChecklistAdapter;
    private ArrayList<HashMap> result=new ArrayList<>();
    private Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_cheklist);
        saveBtn=findViewById(R.id.savePostTestBtn);

        tempLists = new ArrayList<>();
        tempLists.add(new TempList("한글점자"));
        tempLists.add(new TempList("기호점자"));
//        tempLists=(ArrayList<TempList>)getIntent().getSerializableExtra("tempLists");
        recyclerView = findViewById(R.id.evaluationPostRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(PostChecklistActivity.this));
        SetItemDecoration itemDecoration = new SetItemDecoration(20);
        recyclerView.addItemDecoration(itemDecoration);
        evapostChecklistAdapter = new PostChecklistAdapter(tempLists);
        recyclerView.setAdapter(evapostChecklistAdapter);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               result.add(evapostChecklistAdapter.getResult());

               for (Map.Entry<String, String> entrySet : evapostChecklistAdapter.getResult().entrySet()) {
                   Log.d("확인", entrySet.getKey() + " : " + entrySet.getValue());
               }

            }
        });

    }

}

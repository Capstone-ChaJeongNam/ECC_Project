package com.chajeongnam.ecc_project.activity;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chajeongnam.ecc_project.R;
import com.chajeongnam.ecc_project.adapter.PostChecklistAdapter;
import com.chajeongnam.ecc_project.adapter.PostHistoryAdapter;
import com.chajeongnam.ecc_project.decoration.SetItemDecoration;
import com.chajeongnam.ecc_project.model.TempList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PostHistoryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<TempList> tempLists;
    private PostHistoryAdapter postHistoryAdapter;
    private ArrayList<HashMap> result=new ArrayList<>();
    private Button saveBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_history);

        tempLists=new ArrayList<>();
        tempLists = new ArrayList<>();
        tempLists.add(new TempList("1.초성 자음자와 겹글자를 알고 읽고 쓴다."));
        tempLists.add(new TempList("2. 기본 모음자를 알고 읽고 쓴다."));
        tempLists.add(new TempList("3. 수표와 수를 알고 읽고 쓴다."));
        tempLists.add(new TempList("4. 기본모음자 이외 모음자를 알고 읽고 쓴다. "));
        tempLists.add(new TempList("5. 기본받침, 쌍받침, 겹받침을 알고 읽고 쓴다."));
        tempLists.add(new TempList("6. 지팡이의 구조와 용도를 안다."));
        recyclerView = findViewById(R.id.postHistoryRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(PostHistoryActivity.this));
        SetItemDecoration itemDecoration = new SetItemDecoration(20);
        recyclerView.addItemDecoration(itemDecoration);
        postHistoryAdapter = new PostHistoryAdapter(tempLists);
        recyclerView.setAdapter(postHistoryAdapter);


    }
}

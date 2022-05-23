package com.chajeongnam.ecc_project.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chajeongnam.ecc_project.R;
import com.chajeongnam.ecc_project.adapter.EvaPostTestAdapter;
import com.chajeongnam.ecc_project.adapter.EvaPreTestAdapter;
import com.chajeongnam.ecc_project.decoration.SetItemDecoration;
import com.chajeongnam.ecc_project.model.TempList;

import java.util.ArrayList;
import java.util.List;

public class EvaPostTestListActivity  extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<TempList> tempLists;
    private EvaPostTestAdapter evapostTestAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate_posttest);
        Intent intent =getIntent();
        tempLists=(ArrayList<TempList>)getIntent().getSerializableExtra("tempLists");
        recyclerView = findViewById(R.id.evaluationPostRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(EvaPostTestListActivity.this));
        SetItemDecoration itemDecoration = new SetItemDecoration(20);
        recyclerView.addItemDecoration(itemDecoration);
        evapostTestAdapter = new EvaPostTestAdapter(tempLists);
        recyclerView.setAdapter(evapostTestAdapter);

    }

}

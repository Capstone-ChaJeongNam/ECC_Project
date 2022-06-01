package com.chajeongnam.ecc_project.activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chajeongnam.ecc_project.R;
import com.chajeongnam.ecc_project.adapter.CategoryListAdapter;
import com.chajeongnam.ecc_project.adapter.EvaPreTestAdapter;
import com.chajeongnam.ecc_project.decoration.SetItemDecoration;
import com.chajeongnam.ecc_project.model.TempList;

import java.util.ArrayList;
import java.util.List;

public class EvaPreTestListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<TempList> tempLists;
    private EvaPreTestAdapter evapreTestAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_evaluate_pretest);
        Intent intent = getIntent();
        tempLists = (ArrayList<TempList>) getIntent().getSerializableExtra("tempLists");
        recyclerView = findViewById(R.id.evaluationPreRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(EvaPreTestListActivity.this));
        SetItemDecoration itemDecoration = new SetItemDecoration(20);
        recyclerView.addItemDecoration(itemDecoration);
        evapreTestAdapter = new EvaPreTestAdapter(tempLists);
        recyclerView.setAdapter(evapreTestAdapter);

    }


}

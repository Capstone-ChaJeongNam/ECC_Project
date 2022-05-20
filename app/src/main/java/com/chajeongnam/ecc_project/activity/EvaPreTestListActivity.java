package com.chajeongnam.ecc_project.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chajeongnam.ecc_project.R;
import com.chajeongnam.ecc_project.adapter.CategoryListAdapter;
import com.chajeongnam.ecc_project.adapter.EvaPreTestAdapter;
import com.chajeongnam.ecc_project.model.TempList;

import java.util.ArrayList;

public class EvaPreTestListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ArrayList<TempList> tempLists;
    private EvaPreTestAdapter evapreTestAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_evaluate_pretest);
        tempLists = new ArrayList<>();
        tempLists.add(new TempList("첫번째 타이틀"));
        tempLists.add(new TempList("두번째 타이틀"));
        recyclerView=findViewById(R.id.evaluationRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(EvaPreTestListActivity.this));
        evapreTestAdapter = new EvaPreTestAdapter(tempLists);
        recyclerView.setAdapter(evapreTestAdapter);

    }


}

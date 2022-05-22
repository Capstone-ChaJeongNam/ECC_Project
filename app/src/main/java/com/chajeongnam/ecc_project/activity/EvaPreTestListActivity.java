package com.chajeongnam.ecc_project.activity;

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
        tempLists = new ArrayList<>();
        tempLists.add(new TempList("첫번째 타이틀"));
        tempLists.add(new TempList("두번째 타이틀"));
        tempLists.add(new TempList("세번째 타이틀"));
        tempLists.add(new TempList("네번째 타이틀"));
        tempLists.add(new TempList("다서번째 타이틀"));
        tempLists.add(new TempList("여섯번째타이틀"));
        tempLists.add(new TempList("일곱번째 타이틀"));
        tempLists.add(new TempList("여덟뻔째 타이틀"));
        tempLists.add(new TempList("아홉번째 타이틀"));
        tempLists.add(new TempList("열번째 타이틀"));

        recyclerView = findViewById(R.id.evaluationRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(EvaPreTestListActivity.this));
        SetItemDecoration itemDecoration = new SetItemDecoration(-8);
        recyclerView.addItemDecoration(itemDecoration);
        evapreTestAdapter = new EvaPreTestAdapter(tempLists);
        recyclerView.setAdapter(evapreTestAdapter);

    }


}

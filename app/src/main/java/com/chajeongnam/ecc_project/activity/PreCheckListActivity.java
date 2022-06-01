package com.chajeongnam.ecc_project.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.chajeongnam.ecc_project.R;
import com.chajeongnam.ecc_project.adapter.PreChecklistAdapter;

import java.util.Map;

public class PreCheckListActivity extends AppCompatActivity {
    private Button saveBtn;
    private ListView listview;
    private PreChecklistAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_checklist);
        setActionbar();
        saveBtn=findViewById(R.id.savePreTestBtn);


        adapter = new PreChecklistAdapter();

        listview = (ListView) findViewById(R.id.listview1);
        listview.setAdapter(adapter);

        //파이어베이스 데이터 로딩//
        adapter.getItem();



        //파이어베이스 데이터 저장//
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.addItem("4","기본모음자 이외 모음자를 알고 읽고 쓴다.");


            }
        });



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
}
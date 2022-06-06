package com.chajeongnam.ecc_project.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.chajeongnam.ecc_project.R;
import com.chajeongnam.ecc_project.adapter.PreChecklistAdapter;
import com.chajeongnam.ecc_project.model.PreChecklist;

import java.util.ArrayList;

public class PreHistoryActivity extends AppCompatActivity {
    private ArrayList<PreChecklist> checklist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_history);
        setActionbar();
        checklist = new ArrayList<>();

        ListView listview;
        PreChecklistAdapter adapter;

        adapter = new PreChecklistAdapter(this,checklist);

        listview = (ListView) findViewById(R.id.prehistory1);
        listview.setAdapter(adapter);

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
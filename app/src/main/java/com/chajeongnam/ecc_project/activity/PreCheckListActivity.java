package com.chajeongnam.ecc_project.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import com.chajeongnam.ecc_project.R;
import com.chajeongnam.ecc_project.adapter.PreChecklistAdapter;
import com.chajeongnam.ecc_project.model.PreChecklist;
import com.chajeongnam.ecc_project.model.Student;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class PreCheckListActivity extends AppCompatActivity {
    private Button saveBtn;
    private ListView listview;
    private PreChecklistAdapter adapter;
    private DatabaseReference mDatabase;
    private ArrayList<PreChecklist> checklist= new ArrayList<>();
    private ArrayList<String> contentlist= new ArrayList<String>();
    private ArrayList<Integer> idlist= new ArrayList<Integer>();
    private Spinner spinner1, spinner2;
    private int bigId;
    private List<String> bigCategory, mediumCategory;
    private String selectedItem,bigCategoryChild,mediumCategoryChild;

    private View container;
    String category, area;
    Student student;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_checklist);
        setActionbar();
        saveBtn=findViewById(R.id.savePreTestBtn);
        listview = (ListView) findViewById(R.id.listview1);
        spinner1 = (Spinner) findViewById(R.id.bigCategory);
        spinner2 = (Spinner) findViewById(R.id.mediumCategory);
        container = findViewById(R.id.container);

        category = getIntent().getStringExtra("category");
        area = getIntent().getStringExtra("area");

        DatabaseReference database = FirebaseDatabase.getInstance().getReference("ECC");
        ;
        bigCategory = new ArrayList<>();
        mediumCategory = new ArrayList<>();


        database.addListenerForSingleValueEvent(new ValueEventListener() {
            String tempBigCategory;

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    tempBigCategory = dataSnapshot.getKey();
                    bigCategory.add(tempBigCategory);

                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(PreCheckListActivity.this, android.R.layout.simple_spinner_item, bigCategory);
                spinner1.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItem = adapterView.getItemAtPosition(i).toString();
                if (selectedItem.equals("보조공학")) {
                    bigId = 0;
                } else if (selectedItem.equals("점자")) {
                    bigId = 1;
                }
                bigCategoryChild=selectedItem;

                setSpinner(bigId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItem = adapterView.getItemAtPosition(i).toString();
                checklist.clear();
                contentlist.clear();
                idlist.clear();
                mediumCategoryChild=selectedItem;

                database.child(bigCategoryChild).child(mediumCategoryChild).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                PreChecklist pre = snapshot.getValue(PreChecklist.class);
                                checklist.add(pre); //어레이리스트에 데이터 넣은 상태}
                                String content = pre.getContent();
                                contentlist.add(content);
                                int id = pre.getId();
                                idlist.add(id);
                            }
                            adapter= new PreChecklistAdapter(checklist);
                            listview.setAdapter(adapter);
                        }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }

                });
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }

        });


        SparseBooleanArray checkedItems = listview.getCheckedItemPositions();
        student = (Student) getIntent().getParcelableExtra("student");

        //파이어베이스 데이터 저장//
        saveBtn.setOnClickListener(new View.OnClickListener() {
            String key = student.getUid();

            SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd");
            long now = System.currentTimeMillis();
            Date mdDate = new Date(now);
            String recent = mFormat.format(mdDate);

            @Override
            public void onClick(View view) {
                mDatabase = FirebaseDatabase.getInstance().getReference();
                DatabaseReference myRef = mDatabase.child("histories").child(key).child("pre");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        myRef.child(bigCategoryChild).child(mediumCategoryChild).child("recent").setValue(recent);

                        for(int i =0; i< idlist.size(); i++){
                            if(checkedItems.get(i)){
                                myRef.child(bigCategoryChild).child(mediumCategoryChild).child(recent).child(String.valueOf(i)).child("result").setValue(checkedItems.get(i));
                                myRef.child(bigCategoryChild).child(mediumCategoryChild).child(recent).child(String.valueOf(i)).child("content").setValue(contentlist.get(i));
                                myRef.child(bigCategoryChild).child(mediumCategoryChild).child(recent).child(String.valueOf(i)).child("id").setValue(idlist.get(i));
                            }
                        }
                        Snackbar snackbar = Snackbar.make(container, "저장이 완료되었습니다.", 800);
                        snackbar.show();

                        snackbar.addCallback(new Snackbar.Callback(){
                            @Override
                            public void onDismissed(Snackbar transientBottomBar, int event) {
                                finish();
                            }
                        });
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
    }



    //파이어베이스 데이터 로드//
//    public void getPrechecklist() {
//
//        String category = getIntent().getStringExtra("category");
//        String area = getIntent().getStringExtra("area");
//
//        mDatabase = FirebaseDatabase.getInstance().getReference();
//        DatabaseReference myRef = mDatabase.child("ECC");
//        DatabaseReference ChecklistRef = myRef.child(category).child(area);
//        ChecklistRef.addListenerForSingleValueEvent (new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                checklist.clear();
//                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
//                    PreChecklist pre = dataSnapshot.getValue(PreChecklist.class);
//                    checklist.add(pre);
//                    String content = pre.getContent();
//                    contentlist.add(content);
//                    int id = pre.getId();
//                    idlist.add(id);
//                }
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//
//            }
//        });
//        adapter= new PreChecklistAdapter(this,checklist);
//        listview.setAdapter(adapter);
//
//
//    }

    protected void setSpinner(int i) {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("ECC");
        mediumCategory=new ArrayList<>();

        switch (i) {
            case 0:
                database.child("보조공학").addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            mediumCategory.add(dataSnapshot.getKey());

                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(PreCheckListActivity.this, android.R.layout.simple_spinner_item, mediumCategory);
                        spinner2.setAdapter(adapter);


                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                break;
            case 1:
                database.child("점자").addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            mediumCategory.add(dataSnapshot.getKey());


                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(PreCheckListActivity.this, android.R.layout.simple_spinner_item, mediumCategory);
                        spinner2.setAdapter(adapter);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


        }


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
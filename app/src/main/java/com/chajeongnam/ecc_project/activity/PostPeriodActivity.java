package com.chajeongnam.ecc_project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chajeongnam.ecc_project.R;
import com.chajeongnam.ecc_project.Util.FirebaseData;
import com.chajeongnam.ecc_project.adapter.PostChecklistAdapter;
import com.chajeongnam.ecc_project.decoration.SetItemDecoration;
import com.chajeongnam.ecc_project.model.Student;
import com.chajeongnam.ecc_project.model.TempList;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PostPeriodActivity extends AppCompatActivity {
    private int startYear;
    private int startMonth;
    private int startDay;

    private int endYear;


    private int endMonth;
    private int endDay;
    private TextView infoTextHistoryCategory, infoTextHistoryArea,studentName;
    private Student student;
    private String category, area;
    private FirebaseData firebaseData = new FirebaseData();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_period);
        studentName = findViewById(R.id.studentName);
        infoTextHistoryCategory = findViewById(R.id.info_text_history_category);
        infoTextHistoryArea = findViewById(R.id.info_text_history_area);


        final InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        TextView editText1 = (TextView) findViewById(R.id.setStartDate);
        TextView editText2 = (TextView) findViewById(R.id.setEndDate);
        Intent intent=getIntent();
        student=intent.getParcelableExtra("student");
        category=intent.getStringExtra("category");
        area=intent.getStringExtra("area");
        setActionbar();
//        ui 동적 할당
        studentName.setText(student.getName());
//        infoTextHistoryCategory.setText(category);
        infoTextHistoryArea.setText(area);

        DatePicker datePicker = (DatePicker) findViewById(R.id.dataPicker);
        Button button = (Button) findViewById(R.id.dateBtn);
        Button toButton = (Button) findViewById(R.id.toButton);
        firebaseData.setPostHistoryDataFromFirebase();
        editText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Transition transition = new Fade();
                transition.setDuration(600);
                transition.addTarget(datePicker);
                datePicker.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        setStartYear(datePicker.getYear());
                        setStartMonth(datePicker.getMonth() + 1);
                        setStartDay(datePicker.getDayOfMonth());
                        editText1.setText(getStartYear() + "년" + getStartMonth() + "월" + getStartDay());
                        datePicker.setVisibility(View.INVISIBLE);
                        button.setVisibility(View.INVISIBLE);
                    }
                });

            }


        });

        editText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText1.getText() == "") {
                    Toast.makeText(getApplicationContext(), "먼저 시작날짜를 입력해주세요", Toast.LENGTH_SHORT).show();
                    editText2.setText("0000년00월00일");

                } else {
                    datePicker.setVisibility(View.VISIBLE);
                    button.setVisibility(View.VISIBLE);

                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            setEndYear(datePicker.getYear());
                            setEndMonth(datePicker.getMonth() + 1);
                            setEndDay(datePicker.getDayOfMonth());
                            editText2.setText(getEndYear() + "년" + getEndMonth() + "월" + getEndDay());
                            datePicker.setVisibility(View.INVISIBLE);
                            button.setVisibility(View.INVISIBLE);
                        }
                    });
                }

            }
        });

        toButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(PostPeriodActivity.this, PostHistoryListActivity.class);
                intent.putExtra("date", (Serializable) firebaseData.getEvaluationDate());
                intent.putExtra("startYear", startYear);
                intent.putExtra("startMonth", startMonth);
                intent.putExtra("startDay", startDay);
                intent.putExtra("endYear", endYear);
                intent.putExtra("endMonth", endMonth);
                intent.putExtra("endDay", endDay);
//                StudentInfoHistoryAdapter에서 인텐트로 불러옴
                intent.putExtra("student", student);
                intent.putExtra("category", category);
                intent.putExtra("area", area);

                startActivity(intent);

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
        TextView textView = findViewById(R.id.titleTextView);
        textView.setText("사후 평가 기록");
        ImageButton imageButton = findViewById(R.id.backImageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(int startMonth) {
        this.startMonth = startMonth;
    }

    public int getStartDay() {
        return startDay;
    }

    public void setStartDay(int startDay) {
        this.startDay = startDay;
    }

    public int getEndYear() {
        return endYear;
    }

    public void setEndYear(int endYear) {
        this.endYear = endYear;
    }

    public int getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(int endMonth) {
        this.endMonth = endMonth;
    }

    public int getEndDay() {
        return endDay;
    }

    public void setEndDay(int endDay) {
        this.endDay = endDay;
    }


}

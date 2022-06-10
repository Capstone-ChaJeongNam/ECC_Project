package com.chajeongnam.ecc_project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Transition;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.chajeongnam.ecc_project.R;
import com.chajeongnam.ecc_project.model.Student;

import java.util.Calendar;


public class PrePeriodActivity extends AppCompatActivity {
    private int startYear;
    private int startMonth;
    private int startDay;

    private int endYear;


    private int endMonth;
    private int endDay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_period);

        final InputMethodManager manager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        TextView editText1 = (TextView) findViewById(R.id.setStartDate);
        TextView editText2 = (TextView) findViewById(R.id.setEndDate);
        DatePicker datePicker = (DatePicker) findViewById(R.id.dataPicker);
        Button button = (Button) findViewById(R.id.dateBtn);
        Button toButton=(Button) findViewById(R.id.toButton);


        TextView sclass = (TextView) findViewById(R.id.studentclass);
        TextView sname = (TextView) findViewById(R.id.studentname);
        TextView sarea = (TextView) findViewById(R.id.studentarea);

        Intent intent = getIntent();
        Student student = (Student) intent.getParcelableExtra("student");
        String studentclass = student.getAttrClass();
        String studentgrade = student.getGrade();
        String studenttotal = studentgrade+ "학년 " + studentclass+ "반";
        String studentname = student.getName();
        String studentarea = intent.getExtras().getString("area");


        sname.setText(studentname);
        sclass.setText(studenttotal);
        sarea.setText(studentarea);

        editText1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Transition transition = new Fade();
                transition.setDuration(600);
                transition.addTarget(datePicker);
                datePicker.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);
                setStartYear(datePicker.getYear());
                setStartMonth(datePicker.getMonth());
                setStartDay(datePicker.getDayOfMonth());
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
                    setEndYear(datePicker.getYear());
                    setEndMonth(datePicker.getMonth());
                    setEndDay(datePicker.getDayOfMonth());
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

    Intent intent=new Intent(PrePeriodActivity.this, PreHistoryListActivity.class);
          intent.putExtra("startYear",startYear);
          intent.putExtra("startMonth",startMonth);
          intent.putExtra("startDay",startDay);
          intent.putExtra("endYear",endYear);
          intent.putExtra("endMonth",endMonth);
          intent.putExtra("endDay",endDay);
          startActivity(intent);
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

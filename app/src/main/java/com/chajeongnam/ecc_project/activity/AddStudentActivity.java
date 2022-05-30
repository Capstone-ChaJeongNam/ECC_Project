package com.chajeongnam.ecc_project.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.chajeongnam.ecc_project.R;
import com.chajeongnam.ecc_project.model.Student;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddStudentActivity extends AppCompatActivity {
    private DatabaseReference databaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
        setActionbar();

        setView();
    }

    String gender="";

    private void setView() {
        Button addStudentButton = findViewById(R.id.addStudentButton);
        RadioButton maleRadioButton = findViewById(R.id.maleRadioButton);
        RadioButton femaleRadioButton = findViewById(R.id.femaleRadioButton);
        RadioGroup genderRadioGroup = findViewById(R.id.genderRadioGroup);


        genderRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // checkedId is the RadioButton selected
                if (checkedId == R.id.maleRadioButton) {
                    gender = "남";
                } else {
                    gender = "여";
                }
            }
        });

//        if(maleRadioButton.isChecked())
        addStudentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTextFromEditText(gender, view);

            }
        });
    }

    private void getTextFromEditText(String gender, View view) {
        EditText nameEditText = findViewById(R.id.nameEditText);
        EditText birthEditText = findViewById(R.id.birthEditText);
        EditText gradeEditText = findViewById(R.id.gradeEditText);
        EditText classEditText = findViewById(R.id.classEditText);

        String nameStr = nameEditText.getText().toString();
        String birthStr = birthEditText.getText().toString();
        String gradeStr = gradeEditText.getText().toString();
        String classStr = classEditText.getText().toString();
        databaseRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference ref = databaseRef.child("students").push();
        String uid = ref.getKey();
        Student student = new Student(nameStr, birthStr, gender, gradeStr, classStr, uid);
        if(nameStr.equals("") || birthStr.equals("") || gradeStr.equals("") || classStr.equals("") || gender.equals("")){
            Snackbar.make(view, "전체 공란을 채워주세요.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }else{

            ref.setValue(student);
            Snackbar snackbar = Snackbar.make(view, "학생이 추가되었습니다.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null);
            snackbar.addCallback(new Snackbar.Callback(){
                @Override
                public void onShown(Snackbar sb) {
                    super.onShown(sb);
                }

                @Override
                public void onDismissed(Snackbar transientBottomBar, int event) {
                    super.onDismissed(transientBottomBar, event);
                    finish();
                }
            });
            snackbar.show();

        }

//        Intent intent = new Intent();
//        intent.putExtra("student", student);

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
        textView.setText("학생추가");
        ImageButton imageButton = findViewById(R.id.backImageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

}

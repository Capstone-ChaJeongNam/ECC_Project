package com.chajeongnam.ecc_project;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class FindEmailActivity extends AppCompatActivity {

    private Button findEmail;
    private EditText mEtName, mEtBirth;
    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_email);

        mEtName = findViewById(R.id.et_name);
        mEtBirth = findViewById(R.id.et_birth);
        findEmail = findViewById(R.id.btn_findId);

        mFirebaseAuth = FirebaseAuth.getInstance();

        findEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
package com.chajeongnam.ecc_project.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.chajeongnam.ecc_project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class FindPasswordActivity extends AppCompatActivity {

    private Button BtnReset;
    private EditText mEtEmail, mEtName, mEtBirth;
    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password);

        mEtEmail = findViewById(R.id.et_email);
        mEtName = findViewById(R.id.et_name);
        mEtBirth = findViewById(R.id.et_birth);
        BtnReset = findViewById(R.id.btn_reset);

        mFirebaseAuth = FirebaseAuth.getInstance();

        BtnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mFirebaseAuth.sendPasswordResetEmail(mEtEmail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(FindPasswordActivity.this, StartActivity.class);
                            startActivity(intent);

                            Toast.makeText(FindPasswordActivity.this, "이메일을 발송했습니다", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(FindPasswordActivity.this, "이메일 발송이 실패했습니다", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
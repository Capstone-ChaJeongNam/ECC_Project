package com.chajeongnam.ecc_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignInActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabaseRef;
    private EditText mEtEmail, mEtPwd;

    private String loginId, loginPwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        setActionbar();

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("ECC moblie checklist");

        mEtEmail = findViewById(R.id.et_email);
        mEtPwd = findViewById(R.id.et_pwd);

        /*SharedPreferences sharedPreferences = getSharedPreferences("sharedPreferences", Activity.MODE_PRIVATE);

        loginId = sharedPreferences.getString("inputId", null);
        loginPwd = sharedPreferences.getString("inputPwd", null);

        if(loginId != null && loginPwd != null) {
            if(loginId.equals("hj") && loginPwd.equals("xxxx")) {
                Toast.makeText(getApplicationContext(), loginId + "님 자동로그인 입니다!", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("id", loginId);
                startActivity( intent);
                finish();
            }
        }else if(loginId == null && loginPwd == null) {*/
            Button btn_signIn = findViewById(R.id.btn_login);
            btn_signIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    /*if (mEtEmail.getText().toString().equals("hj") && mEtPwd.getText().toString().equals("xxxx")) {
                        SharedPreferences sharedPreferences = getSharedPreferences("sharedPreferences", Activity.MODE_PRIVATE);

                        SharedPreferences.Editor autoLogin = sharedPreferences.edit();

                        autoLogin.putString("inputId", mEtEmail.getText().toString());
                        autoLogin.putString("inputPwd", mEtPwd.getText().toString());

                        autoLogin.commit();
                        Toast.makeText(getApplicationContext(), mEtEmail.getText().toString()+"님 환영합니다.", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }*/

                    String strEmail = mEtEmail.getText().toString().trim();
                    String strPwd = mEtPwd.getText().toString().trim();

                    mFirebaseAuth.signInWithEmailAndPassword(strEmail, strPwd).addOnCompleteListener(SignInActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                                startActivity(intent);
                                finish();
                            } else {
                                Toast.makeText(SignInActivity.this, "아이디와 비밀번호를 확인해주세요", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            });
        //}

        TextView et_findId = findViewById(R.id.et_findId);
        et_findId.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, FindEmailActivity.class);
                startActivity(intent);
            }
        });

        TextView et_findPwd = findViewById(R.id.et_findPwd);
        et_findPwd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignInActivity.this, ResetPasswordActivity.class);
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
        textView.setText("  로그인");
        ImageButton imageButton = findViewById(R.id.backImageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
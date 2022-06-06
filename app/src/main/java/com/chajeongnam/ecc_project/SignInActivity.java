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
import android.widget.CheckBox;
import android.widget.CompoundButton;
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
    private CheckBox autoLogin;

    private Boolean loginChecked;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    private SharedPreferences appData;
    private boolean saveLoginData;
    private String id, pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        setActionbar();

        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("ECC moblie checklist");

        mEtEmail = findViewById(R.id.et_email);
        mEtPwd = findViewById(R.id.et_pwd);
        autoLogin = (CheckBox) findViewById(R.id.checkBox);

        /*
        // 설정값 불러오기
        appData = getSharedPreferences("appData", MODE_PRIVATE);
        load();

        // 이전에 로그인 정보를 저장시킨 기록이 있다면
        if (saveLoginData) {
            mEtEmail.setText(id);
            mEtPwd.setText(pwd);
            autoLogin.setChecked(saveLoginData);
        }
        */

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


        /*
        if (pref.getBoolean("autoLogin", false)) {
            mEtEmail.setText(pref.getString("id", ""));
            mEtPwd.setText(pref.getString("pw", ""));
            autoLogin.setChecked(true);
            // goto mainActivity
        } else {
            // if autoLogin unChecked
            String logId = mEtEmail.getText().toString();
            String logPwd = mEtPwd.getText().toString();
            Boolean validation = loginValidation(logId, logPwd);

            if(validation) {
                Toast.makeText(SignInActivity.this, "Login Success", Toast.LENGTH_LONG).show();
                // save id, password to Database

                if(loginChecked) {
                    // if autoLogin Checked, save values
                    editor.putString("id", logId);
                    editor.putString("pw", logPwd);
                    editor.putBoolean("autoLogin", true);
                    editor.commit();
                }
                // goto mainActivity
            } else {
                Toast.makeText(SignInActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                // goto LoginActivity
            }
        }
        // set checkBoxListener

        autoLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    loginChecked = true;
                } else {
                    // if unChecked, removeAll
                    loginChecked = false;
                    editor.clear();
                    editor.commit();
                }
            }
        });
        */


            Button btn_signIn = findViewById(R.id.btn_login);
            btn_signIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //save();

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

                    if(strEmail == null || strPwd == null) {
                        Toast.makeText(SignInActivity.this, "아이디와 비밀번호를 기입해주세요", Toast.LENGTH_LONG).show();
                    } else {
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
        textView.setText("로그인");
        ImageButton imageButton = findViewById(R.id.backImageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    /*
    private boolean loginValidation(String logId, String logPwd) {
        if(pref.getString("id","").equals(logId) && pref.getString("pw","").equals(logPwd)) {
            return true;
        } else if (pref.getString("id","").equals(null)){
            Toast.makeText(SignInActivity.this, "Please Sign in first", Toast.LENGTH_LONG).show();
            return false;
        } else {
            return false;
        }
    }*/

    /*
    // 설정값을 저장하는 함수
    private void save() {
        // SharedPreferences 객체만으론 저장 불가능 Editor 사용
        SharedPreferences.Editor editor = appData.edit();

        // 에디터객체.put타입( 저장시킬 이름, 저장시킬 값 )
        // 저장시킬 이름이 이미 존재하면 덮어씌움
        editor.putBoolean("SAVE_LOGIN_DATA", autoLogin.isChecked());
        editor.putString("ID", mEtEmail.getText().toString().trim());
        editor.putString("PWD", mEtPwd.getText().toString().trim());

        // apply, commit 을 안하면 변경된 내용이 저장되지 않음
        editor.apply();
    }

    // 설정값을 불러오는 함수
    private void load() {
        // SharedPreferences 객체.get타입( 저장된 이름, 기본값 )
        // 저장된 이름이 존재하지 않을 시 기본값
        saveLoginData = appData.getBoolean("SAVE_LOGIN_DATA", false);
        id = appData.getString("ID", "");
        pwd = appData.getString("PWD", "");
    }
    
     */

}
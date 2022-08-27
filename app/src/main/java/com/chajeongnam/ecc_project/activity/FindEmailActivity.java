package com.chajeongnam.ecc_project.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.chajeongnam.ecc_project.R;
import com.chajeongnam.ecc_project.model.UserAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class FindEmailActivity extends AppCompatActivity {

    private static final String TAG = "ReadAndWriteSnippets";
    private Button findEmail;
    private EditText mEtName, mEtBirth;
    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabase;
    private AlertDialog dialog;

    private String emailId;
    private String username;
    private String userbirth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_email);
        setActionbar();

        mEtName = findViewById(R.id.et_name);
        mEtBirth = findViewById(R.id.et_birth);
        findEmail = findViewById(R.id.btn_findId);


        mFirebaseAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();

        findEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String strName = mEtName.getText().toString().trim();
                String strBirth = mEtBirth.getText().toString().trim();

                ValueEventListener mValueEventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {

                            String key = postSnapshot.getKey();
                            UserAccount account = postSnapshot.getValue(UserAccount.class);

                            emailId = account.getEmailId();
                            username = account.getName();
                            userbirth = account.getBirth();
                            if (strName.equals(username) && strBirth.equals(userbirth)) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(FindEmailActivity.this);
                                dialog = builder.setMessage(strName + "님의 아이디는 " + emailId + " 입니다").setNegativeButton("확인", null).create();
                                dialog.show();
                            }
//                            else{
//                                AlertDialog.Builder builder = new AlertDialog.Builder(FindEmailActivity.this);
//                                dialog = builder.setMessage("아이디가 존재하지 않습니다").setNegativeButton("확인", null).create();
//                                dialog.show();
//
//                            }

                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                };
                mDatabase.child("UserAccount").addValueEventListener(mValueEventListener);
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
        textView.setText("아이디 찾기");
        ImageButton imageButton = findViewById(R.id.backImageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
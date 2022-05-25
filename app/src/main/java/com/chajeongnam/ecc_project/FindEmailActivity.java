package com.chajeongnam.ecc_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

                    /*String email = user.getEmail();

                    boolean emailVerified = user.isEmailVerified();
                    String uid = user.getUid();*/

                ValueEventListener mValueEventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {

                            String key = postSnapshot.getKey();
                            UserAccount account = postSnapshot.getValue(UserAccount.class);

                            emailId = account.getEmailId();
                            username = account.getName();
                            userbirth = account.getBirth();


                        }
                        if (strName.equals(username) && strBirth.equals(userbirth)) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(FindEmailActivity.this);
                            dialog = builder.setMessage(strName + "님의 아이디는 " + emailId + " 입니다").setNegativeButton("확인", null).create();
                            dialog.show();
                        }else{
                            AlertDialog.Builder builder = new AlertDialog.Builder(FindEmailActivity.this);
                            dialog = builder.setMessage("아이디가 존재하지 않습니다").setNegativeButton("확인", null).create();
                            dialog.show();

                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                };
                mDatabase.child("ECC moblie checklist").child("UserAccount").addValueEventListener(mValueEventListener);











                /*
                mDatabase.child("ECC moblie checklist").child("UserAccount").child(firebaseUser.getUid()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for(DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                            UserAccount account = dataSnapshot.getValue(UserAccount.class);

                            emailId = account.getEmailId();
                            username = account.getName();
                            userbirth = account.getBirth();

                            if (strName.equals(username) && strBirth.equals(userbirth)) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(FindEmailActivity.this);
                                dialog = builder.setMessage(strName + "님의 아이디는 " + emailId + " 입니다").setNegativeButton("확인", null).create();
                                dialog.show();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(FindEmailActivity.this);
                                dialog = builder.setMessage("아이디가 존재하지 않습니다").setNegativeButton("확인", null).create();
                                dialog.show();
                            }
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
                */




                    /*
                    mDatabase.child("UserAccount").child(String.valueOf(user)).child("birth").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            String value = dataSnapshot.getValue(String.class);
                            userbirth = value;
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                    if(strName.equals(username) && strBirth.equals(userbirth)) {
                        mDatabase.child("UserAccount").child(String.valueOf(user)).child("emailId").addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                String value = dataSnapshot.getValue(String.class);
                                emailId = value;
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });
                    */

            }
        });
        /*
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Post post = dataSnapshot.getValue(Post.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        mPostReference.addValueEventListener(postListener);*/
    }
}
package com.chajeongnam.ecc_project.activity;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.chajeongnam.ecc_project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class TempSignIn extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private String email,password;
    private FirebaseStorage mStorage;
    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabaseRef;
    EditText editText1, editText2;
    Button button;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temp_sign_in);
        editText1 = (EditText) findViewById(R.id.logInid);
        editText2 = (EditText) findViewById(R.id.logInpassword);
        button = (Button) findViewById(R.id.logInBtn);
        mAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("ECC moblie checklist");
        mStorage = FirebaseStorage.getInstance();

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                 email=editText1.getText().toString().trim();
                 password=editText2.getText().toString().trim();
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(TempSignIn.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.d(TAG, "signInWithEmail:success");

                                    FirebaseUser user = mAuth.getCurrentUser();

                                    Intent intent = new Intent(TempSignIn.this, PostHistoryListActivity.class);
                                    startActivity(intent);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(TempSignIn.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


            }
        });




    }
}

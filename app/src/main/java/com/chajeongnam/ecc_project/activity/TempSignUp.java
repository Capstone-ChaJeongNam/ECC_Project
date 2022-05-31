package com.chajeongnam.ecc_project.activity;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import com.chajeongnam.ecc_project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import java.io.File;
import java.util.HashMap;

public class TempSignUp extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private String email;
    private String password;
    private FirebaseStorage mStorage;
    private FirebaseAuth mFirebaseAuth;
    private DatabaseReference mDatabaseRef;
    EditText editText1, editText2;
    Button button;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            currentUser.reload();
        }

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temp_sign_up);
        editText1 = (EditText) findViewById(R.id.id);
        editText2 = (EditText) findViewById(R.id.password);
        button = (Button) findViewById(R.id.signUpBtn);
        mAuth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("ECC moblie checklist");
        mStorage = FirebaseStorage.getInstance();
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=editText1.getText().toString().trim();
                password=editText2.getText().toString().trim();

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(TempSignUp.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser firebaseUser = mAuth.getCurrentUser();
                                        String email=firebaseUser.getEmail();
                                        String uid= firebaseUser.getUid();



                                        Intent intent = new Intent(TempSignUp.this, PostHistoryListActivity.class);
                                        startActivity(intent);

                                        Toast.makeText(TempSignUp.this, "회원가입을 성공하였습니다", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "회원가입을 실패하였습니다", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    Toast.makeText(getApplicationContext(), "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });




    }

}


//package com.chajeongnam.ecc_project;
//
//        import androidx.annotation.NonNull;
//        import androidx.annotation.Nullable;
//        import androidx.appcompat.app.AlertDialog;
//        import androidx.appcompat.app.AppCompatActivity;
//        import androidx.loader.content.CursorLoader;
//
//        import android.content.Intent;
//        import android.database.Cursor;
//        import android.net.Uri;
//        import android.os.Bundle;
//        import android.provider.MediaStore;
//        import android.util.Log;
//        import android.view.View;
//        import android.widget.Button;
//        import android.widget.EditText;
//        import android.widget.ImageView;
//        import android.widget.Toast;
//
//        import com.google.android.gms.tasks.OnCompleteListener;
//        import com.google.android.gms.tasks.Task;
//        import com.google.firebase.auth.AuthResult;
//        import com.google.firebase.auth.FirebaseAuth;
//        import com.google.firebase.auth.FirebaseUser;
//        import com.google.firebase.database.DatabaseReference;
//        import com.google.firebase.database.FirebaseDatabase;
//        import com.google.firebase.storage.FirebaseStorage;
//
//        import java.io.File;
//
//
//public class SignUpActivity extends AppCompatActivity {
//
//    private static final String TAG = "MyTag";
//    public static final int PICK_FROM_ALBUM = 1;
//    private Uri imageUri;
//    private String pathUri;
//    private File tempFile;
//    private FirebaseStorage mStorage;
//
//    private FirebaseAuth mFirebaseAuth;
//    private DatabaseReference mDatabaseRef;
//    private EditText mEtEmail, mEtPwd, mEtName, mEtBirth, mEtRePwd;
//    private ImageView profile;
//    private Button mBtnSignUp;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sign_up);
//
//        mFirebaseAuth = FirebaseAuth.getInstance();
//        mDatabaseRef = FirebaseDatabase.getInstance().getReference("ECC moblie checklist");
//        mStorage = FirebaseStorage.getInstance();
//
//        profile = findViewById(R.id.user_image);
//        mEtEmail = findViewById(R.id.et_email);
//        mEtPwd = findViewById(R.id.et_pwd);
//        mEtName = findViewById(R.id.et_name);
//        mEtBirth = findViewById(R.id.et_birth);
//        mEtRePwd = findViewById(R.id.et_repwd);
//        mBtnSignUp = findViewById(R.id.btn_signup);
//
//        profile.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                gotoAlbum();
//            }
//        });
//
//        mBtnSignUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String strEmail = mEtEmail.getText().toString().trim();
//                String strPwd = mEtPwd.getText().toString().trim();
//                String strName = mEtName.getText().toString().trim();
//                String strBirth = mEtBirth.getText().toString().trim();
//                String strRePwd = mEtRePwd.getText().toString().trim();
//
//                if (strPwd.equals(strRePwd)) {
//                    mFirebaseAuth.createUserWithEmailAndPassword(strEmail, strPwd).addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (task.isSuccessful()) {
//                                FirebaseUser firebaseUser = mFirebaseAuth.getCurrentUser();
//                                UserAccount account = new UserAccount();
//                                account.setIdToken(firebaseUser.getUid());
//                                account.setEmailId(firebaseUser.getEmail());
//                                account.setPassword(strPwd);
//                                account.setBirth(strBirth);
//                                account.setName(strName);
//
//
//                                mDatabaseRef.child("UserAccount").child(firebaseUser.getUid()).setValue(account);
//
//                                Intent intent = new Intent(SignUpActivity.this, StartActivity.class);
//                                startActivity(intent);
//
//                                Toast.makeText(SignUpActivity.this, "회원가입을 성공하였습니다", Toast.LENGTH_LONG).show();
//                            } else {
//                                Toast.makeText(SignUpActivity.this, "회원가입을 실패하였습니다", Toast.LENGTH_LONG).show();
//                            }
//                        }
//                    });
//                } else {
//                    Toast.makeText(SignUpActivity.this, "비밀번호가 일치하지 않습니다", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//    }
//
//    private void gotoAlbum() {
//        Intent intent = new Intent(Intent.ACTION_PICK);
//        intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
//        startActivityForResult(intent, PICK_FROM_ALBUM);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode != RESULT_OK) {
//            Toast.makeText(SignUpActivity.this, "취소되었습니다", Toast.LENGTH_LONG).show();
//            if (tempFile != null) {
//                if (tempFile.exists()) {
//                    if (tempFile.delete()) {
//                        Log.e(TAG, tempFile.getAbsolutePath() + " 삭제 성공");
//                        tempFile = null;
//                    }
//                }
//            }
//            return;
//        }
//
//        switch (requestCode) {
//            case PICK_FROM_ALBUM: {
//                imageUri = data.getData();
//                pathUri = getPath(data.getData());
//                Log.d(TAG, "PICK_FROM_ALBUM photoUri : " + imageUri);
//                profile.setImageURI(imageUri);
//                break;
//            }
//        }
//    }
//
//    public String getPath(Uri uri) {
//
//        String[] proj = {MediaStore.Images.Media.DATA};
//        CursorLoader cursorLoader = new CursorLoader(this, uri, proj, null, null, null);
//
//        Cursor cursor = cursorLoader.loadInBackground();
//        int index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//
//        cursor.moveToFirst();
//        return cursor.getString(index);
//    }
//}

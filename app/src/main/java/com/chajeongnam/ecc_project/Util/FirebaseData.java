package com.chajeongnam.ecc_project.Util;

import android.widget.Button;

import com.chajeongnam.ecc_project.adapter.PostChecklistAdapter;
import com.chajeongnam.ecc_project.model.TempList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FirebaseData {
    private List<TempList> tempLists = new ArrayList<>();
    private ArrayList<HashMap> result = new ArrayList<>();

    public List<TempList> getDatas(){

        return  tempLists;
    }

    public void setDAtaFromFirebase() {
        DatabaseReference database = FirebaseDatabase.getInstance().getReference("ECC");
        DatabaseReference myRef = database.child("보조공학");
        myRef.child("OCR").addListenerForSingleValueEvent(new ValueEventListener() {
            TempList getDataFromFireBasetempList;

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    getDataFromFireBasetempList = snapshot.getValue(TempList.class);
                    tempLists.add(getDataFromFireBasetempList); //어레이리스트에 데이터 넣은 상태}

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }

        });
    }



}

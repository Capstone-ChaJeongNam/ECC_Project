package com.chajeongnam.ecc_project.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chajeongnam.ecc_project.model.TempList;

import java.util.ArrayList;

public class EvaPreTestAdapter extends RecyclerView.Adapter<EvaPreTestAdapter.ViewHolder> {

    private ArrayList<TempList> tempLists;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

public  class ViewHolder extends RecyclerView.ViewHolder{
        private TextView content;

    public ViewHolder(@NonNull View itemView, TextView content) {
        super(itemView);
        this.content = content;
    }
}
}

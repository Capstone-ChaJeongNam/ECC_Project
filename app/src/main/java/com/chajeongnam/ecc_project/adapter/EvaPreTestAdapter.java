package com.chajeongnam.ecc_project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chajeongnam.ecc_project.R;
import com.chajeongnam.ecc_project.model.TempList;

import java.util.ArrayList;

public class EvaPreTestAdapter extends RecyclerView.Adapter<EvaPreTestAdapter.ViewHolder> {

    private ArrayList<TempList> tempLists;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_category, parent, false);
        EvaPreTestAdapter.ViewHolder viewHolder = new EvaPreTestAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TempList tempList = tempLists.get(position);
        holder.bind(tempList);
    }



    @Override
    public int getItemCount() {
        return tempLists.size();
    }





    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.content);
        }

        private void bind(TempList tempList) {
            content.setText(tempList.getTitle());
        }
    }


}

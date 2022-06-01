package com.chajeongnam.ecc_project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.chajeongnam.ecc_project.R;
import com.chajeongnam.ecc_project.model.PreHistoryDateTempList;

import java.util.List;

public class PreHistoryAdapter extends RecyclerView.Adapter<PreHistoryAdapter.ViewHolder> {
    private List<PreHistoryDateTempList> tempLists;
    private int selectedPosition = -1;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_pre_period, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }


    public PreHistoryAdapter(List<PreHistoryDateTempList> tempLists) {
        this.tempLists = tempLists;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PreHistoryDateTempList tempList = tempLists.get(position);
        holder.bind(tempList);

    }


    @Override
    public int getItemCount() {
        return tempLists.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView textView;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.preHistoryRecyclerView);
            textView = itemView.findViewById(R.id.historyName);

        }

        @Override
        public String toString() {
            return super.toString();
        }

        private void bind(PreHistoryDateTempList tempList) {
            textView.setText(tempList.getTextView());
        }
    }
}

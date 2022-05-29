package com.chajeongnam.ecc_project.adapter;

import android.content.Context;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.chajeongnam.ecc_project.R;
import com.chajeongnam.ecc_project.model.PostHistoryDateTempList;
import com.chajeongnam.ecc_project.model.TempList;

import java.util.List;

public class PostHistoryAdapter extends RecyclerView.Adapter<PostHistoryAdapter.ViewHolder> {
    private List<PostHistoryDateTempList> tempLists;
    private int selectedPosition = -1;


    @NonNull
    @Override
    public PostHistoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_post_period, parent, false);
        PostHistoryAdapter.ViewHolder viewHolder = new PostHistoryAdapter.ViewHolder(view);
        return viewHolder;
    }


    public PostHistoryAdapter(List<PostHistoryDateTempList> tempLists) {
        this.tempLists = tempLists;
    }

    @Override
    public void onBindViewHolder(@NonNull PostHistoryAdapter.ViewHolder holder, int position) {
        PostHistoryDateTempList tempList = tempLists.get(position);
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
            cardView = itemView.findViewById(R.id.postHistoryRecyclerView);
            textView = itemView.findViewById(R.id.historyName);

        }

        @Override
        public String toString() {
            return super.toString();
        }

        private void bind(PostHistoryDateTempList tempList) {
            textView.setText(tempList.getTextView());
        }
    }
}

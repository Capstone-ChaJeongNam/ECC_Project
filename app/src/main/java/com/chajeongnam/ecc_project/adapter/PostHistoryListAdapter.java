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
import com.chajeongnam.ecc_project.model.PostHistoryDateTempList;

import java.util.List;

public class PostHistoryListAdapter extends RecyclerView.Adapter<PostHistoryListAdapter.ViewHolder> {
    private List<PostHistoryDateTempList> tempLists;
    private int selectedPosition = -1;


    @NonNull
    @Override
    public PostHistoryListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_post_period, parent, false);
        PostHistoryListAdapter.ViewHolder viewHolder = new PostHistoryListAdapter.ViewHolder(view);
        return viewHolder;
    }


    public PostHistoryListAdapter(List<PostHistoryDateTempList> tempLists) {
        this.tempLists = tempLists;
    }

    @Override
    public void onBindViewHolder(@NonNull PostHistoryListAdapter.ViewHolder holder, int position) {
        PostHistoryDateTempList tempList = tempLists.get(position);
        holder.bind(tempList);
//        파베에서 갖고 온 날짜를 textview의 text와 비교하면 됨
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });

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
            cardView = itemView.findViewById(R.id.historyList);
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

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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chajeongnam.ecc_project.R;
import com.chajeongnam.ecc_project.model.TempList;

import java.util.List;

public class PostHistoryAdapter extends RecyclerView.Adapter<PostHistoryAdapter.ViewHolder> {
    private List<TempList> tempLists;
    private int selectedPosition = -1;

    public PostHistoryAdapter(List<TempList> postHistoryTempLists) {
        this.tempLists = postHistoryTempLists;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.post_history_category, parent, false);
        PostHistoryAdapter.ViewHolder viewHolder = new PostHistoryAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PostHistoryAdapter.ViewHolder holder, int position) {
        TempList postHistoryTempList = tempLists.get(position);
        holder.bind(postHistoryTempList);

        holder.content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Transition transition = new Fade();
                transition.setDuration(600);
                transition.addTarget(holder.editText);
                TransitionManager.beginDelayedTransition(holder.radioGroup, transition);
                if (holder.editText.getVisibility() == View.GONE) {
                    holder.editText.setVisibility(View.VISIBLE);
                } else holder.editText.setVisibility(View.GONE);

            }
        });

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView content;
        private RadioGroup radioGroup;
        private EditText editText;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            content = itemView.findViewById(R.id.historyContent);
            radioGroup = itemView.findViewById(R.id.historyEvaGroup);
            editText = itemView.findViewById(R.id.historyDescriptionEditText);
        }

        private void bind(TempList tempLists) {
            content.setText(tempLists.getTitle());
        }
    }
}




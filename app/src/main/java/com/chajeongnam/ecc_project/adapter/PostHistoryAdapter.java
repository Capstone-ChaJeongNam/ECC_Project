package com.chajeongnam.ecc_project.adapter;

import android.content.Context;
import android.content.Intent;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chajeongnam.ecc_project.R;
import com.chajeongnam.ecc_project.activity.PostHistoryActivity;
import com.chajeongnam.ecc_project.decoration.SetItemDecoration;
import com.chajeongnam.ecc_project.model.PostHistoryResult;
import com.chajeongnam.ecc_project.model.TempList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.net.URISyntaxException;
import java.util.List;

public class PostHistoryAdapter extends RecyclerView.Adapter<PostHistoryAdapter.ViewHolder> {
    private List<PostHistoryResult> postHistoryResults;
    private Context context;
    private int selectedPosition = -1;
    private String date;

    public PostHistoryAdapter(List<PostHistoryResult> postHistoryResults,String date) {
        this.postHistoryResults = postHistoryResults;
        this.date=date;
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
        PostHistoryResult postHistoryTempList = postHistoryResults.get(position);
        holder.bind(postHistoryTempList);




    }

    @Override
    public int getItemCount() {
        return (null != postHistoryResults ? postHistoryResults.size() : 0);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView content;
        private RadioGroup radioGroup;
        private RadioButton score1,score2,score3,scoreC;
        private EditText editText;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            content = itemView.findViewById(R.id.historyContent);
            radioGroup = itemView.findViewById(R.id.historyEvaGroup);
            editText = itemView.findViewById(R.id.historyDescriptionEditText);
            score1=itemView.findViewById(R.id.historyOne);
            score2=itemView.findViewById(R.id.historyTwo);
            score3=itemView.findViewById(R.id.historyThree);
            scoreC=itemView.findViewById(R.id.historyC);

        }

        private void bind(PostHistoryResult postHistoryResults) {
            content.setText(postHistoryResults.getContent());
            switch (postHistoryResults.getScore()){
                case 1:
                    score1.setChecked(true);
                    break;
                case 2:
                    score2.setChecked(true);
                    break;
                case 3:
                    score3.setChecked(true);
                    break;
                case 4:
                    scoreC.setChecked(true);
                    break;
            }

        }
    }
}




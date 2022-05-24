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
import androidx.recyclerview.widget.RecyclerView;

import com.chajeongnam.ecc_project.R;
import com.chajeongnam.ecc_project.model.TempList;

import java.util.List;

public class PostChecklistAdapter extends RecyclerView.Adapter<PostChecklistAdapter.ViewHolder> {
    private List<TempList> tempLists;
    private int selectedPosition = -1;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.post_cheklist_category, parent, false);
        PostChecklistAdapter.ViewHolder viewHolder = new PostChecklistAdapter.ViewHolder(view);
        return viewHolder;
    }


    public PostChecklistAdapter(List<TempList> tempLists) {
        this.tempLists = tempLists;
    }

    @Override
    public void onBindViewHolder(@NonNull PostChecklistAdapter.ViewHolder holder, int position) {
        TempList tempList = tempLists.get(position);
        holder.bind(tempList);
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
        holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.one:
                        Toast.makeText(radioGroup.getContext(), "짧게 출력 Hello World!", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.two:
                        Toast.makeText(radioGroup.getContext(), "짧게 출력 Hello World!", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.three:
                        Toast.makeText(radioGroup.getContext(), "짧게 출력 Hello World!", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.C:
                        Toast.makeText(radioGroup.getContext(), "짧게 출력 Hello World!", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return tempLists.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView content;
        private RadioGroup radioGroup;
        private EditText editText;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.content);
            radioGroup = itemView.findViewById(R.id.evaGroup);
            editText = itemView.findViewById(R.id.descriptionEditText);

        }

        @Override
        public String toString() {
            return super.toString();
        }

        private void bind(TempList tempList) {
            content.setText(tempList.getTitle());
        }
    }


}

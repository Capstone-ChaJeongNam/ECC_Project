package com.chajeongnam.ecc_project.adapter;

import android.content.Context;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Log;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PostChecklistAdapter extends RecyclerView.Adapter<PostChecklistAdapter.ViewHolder> {
    private List<TempList> tempLists;
    private DatabaseReference mDatabase;


    private int selectedPosition = -1;

    private    HashMap<String,String> result = new HashMap<String,String>();


    public HashMap<String, String> getResult() {
        return result;
    }

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
                        result.put(String.valueOf(holder.getAdapterPosition()), "1");
                        break;
                    case R.id.two:
                        Toast.makeText(radioGroup.getContext(), "짧게 출력 Hello World!", Toast.LENGTH_SHORT).show();
                        result.put(String.valueOf(holder.getAdapterPosition()), "2");
                        break;
                    case R.id.three:
                        Toast.makeText(radioGroup.getContext(), "짧게 출력 Hello World!", Toast.LENGTH_SHORT).show();
                        result.put(String.valueOf(holder.getAdapterPosition()), "3");
                        break;
                    case R.id.C:
                        Toast.makeText(radioGroup.getContext(), "짧게 출력 Hello World!", Toast.LENGTH_SHORT).show();
                        result.put(String.valueOf(holder.getAdapterPosition()), "C");
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

package com.chajeongnam.ecc_project.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chajeongnam.ecc_project.R;
import com.chajeongnam.ecc_project.model.Result;
import com.chajeongnam.ecc_project.model.TempList;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
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

    private HashMap<Integer, Result> result = new HashMap<Integer, Result>();


    public HashMap<Integer, Result> getResult() {
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

                if (result.get(holder.getAdapterPosition()+1) == null) {
                    Toast.makeText(holder.content.getContext(), "먼저 객관식 평가를 진행하여 주세요!", Toast.LENGTH_LONG).show();

                } else {
                    Transition transition = new Fade();
                    transition.setDuration(600);
                    transition.addTarget(holder.editText);
                    TransitionManager.beginDelayedTransition(holder.radioGroup, transition);
                    if (holder.editText.getVisibility() == View.GONE) {
                        holder.editText.setVisibility(View.VISIBLE);
                    } else holder.editText.setVisibility(View.GONE);
                }


            }
        });

        holder.openScriptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (result.get(holder.getAdapterPosition()+1) == null) {
                    Toast.makeText(holder.content.getContext(), "먼저 객관식 평가를 진행하여 주세요!", Toast.LENGTH_LONG).show();

                } else {
                    Transition transition = new Fade();
                    transition.setDuration(600);
                    transition.addTarget(holder.editText);
                    TransitionManager.beginDelayedTransition(holder.radioGroup, transition);
                    if (holder.editText.getVisibility() == View.GONE) {
                        holder.editText.setVisibility(View.VISIBLE);
                    } else holder.editText.setVisibility(View.GONE);
                }
            }
        });
        holder.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            String firebaseEccKey = String.valueOf(holder.content.getText());

            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.one:
                        result.put(holder.getAdapterPosition() + 1, new Result(1, "", holder.content.getText().toString().trim()));
                        break;
                    case R.id.two:
                        result.put(holder.getAdapterPosition() + 1, new Result(2, "", holder.content.getText().toString().trim()));

                        break;
                    case R.id.three:
                        result.put(holder.getAdapterPosition() + 1, new Result(3, "", holder.content.getText().toString().trim()));

                        break;
                    case R.id.C:
                        result.put(holder.getAdapterPosition() + 1, new Result(4, "", holder.content.getText().toString().trim()));

                        break;
                }
            }
        });

        holder.descriptionInputText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                result.get(holder.getAdapterPosition() + 1).setDescription(holder.descriptionInputText.getText().toString().trim());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }


    @Override
    public int getItemCount() {
        return (null != tempLists ? tempLists.size() : 0);
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView content;
        private RadioGroup radioGroup;
        private TextInputLayout editText;
        private TextInputEditText descriptionInputText;
        private ImageButton openScriptButton;
        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.content);
            radioGroup = itemView.findViewById(R.id.evaGroup);
            editText = itemView.findViewById(R.id.descriptionEditText);
            descriptionInputText = itemView.findViewById(R.id.descriptionInputText);
            openScriptButton = itemView.findViewById(R.id.openScriptButton);

        }

        @Override
        public String toString() {
            return super.toString();
        }

        private void bind(TempList tempList) {
            int index = getAdapterPosition() + 1;
            content.setText(index + "." + tempList.getContent());
        }
    }


}

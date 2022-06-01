package com.chajeongnam.ecc_project.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chajeongnam.ecc_project.R;
import com.chajeongnam.ecc_project.activity.PostChecklistActivity;
import com.chajeongnam.ecc_project.activity.PreCheckListActivity;
import com.chajeongnam.ecc_project.activity.StartActivity;
import com.chajeongnam.ecc_project.model.Category;
import com.chajeongnam.ecc_project.model.History;
import com.chajeongnam.ecc_project.model.Student;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StudentInfoHistoryAdapter extends RecyclerView.Adapter<StudentInfoHistoryAdapter.ViewHolder> {

    private List<History> historyList;
    private Student student;
    private boolean isPost;
    public StudentInfoHistoryAdapter(List<History> historyList, Student student, boolean isPost) {
        this.historyList = historyList;
        this.student = student;
        this.isPost = isPost;
    }

    public void setIsPost(boolean isPost){
        this.isPost = isPost;
    }

    public boolean getIsPost(){
        return isPost;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        TextView historyCategoryTextView, historyAreaTextView, historyRecentTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            historyCategoryTextView = itemView.findViewById(R.id.historyCategoryTextView);
            historyAreaTextView = itemView.findViewById(R.id.historyAreaTextView);
            historyRecentTextView = itemView.findViewById(R.id.historyRecentTextView);
        }

        private void bind(History history){
            historyCategoryTextView.setText(history.getCategory().getTitle());
            String area = history.getCategory().getArea();

            historyAreaTextView.setText(setTextLine(area));
            historyRecentTextView.setText(history.getRecent());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent;
                    if (!getIsPost()) {
                        intent = new Intent(view.getContext(), PreCheckListActivity.class);
                    } else {
                        intent = new Intent(view.getContext(), PostChecklistActivity.class);
                    }
                    intent.putExtra("student", student);
                    intent.putExtra("category", historyCategoryTextView.getText());
                    intent.putExtra("area", historyAreaTextView.getText() );
                    view.getContext().startActivity(intent);
                }
            });
        }

        private String setTextLine(String area){

            if(area.length() >5){
                int count = 0;

                List<String> chArray = Arrays.asList(area.split("(?!^)"));
                List<Integer> locations = new ArrayList<>();
                for(int i = 0; i< chArray.size(); i++){
                    if(chArray.get(i).equals(" ")){
                        locations.add(i);
                        count += 1;
                    }
                }
                if(count != 0)
                    return area.substring(0, locations.get(count/2)) + "\n" + area.substring(locations.get(count/2) + 1);
                else
                    return area;

            }else{
                return area;
            }

        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_student_test_history, parent, false);
        StudentInfoHistoryAdapter.ViewHolder viewHolder = new StudentInfoHistoryAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        History history = historyList.get(position);
        holder.bind(history);
    }

    @Override
    public int getItemCount() {
        return historyList.size();
    }
}

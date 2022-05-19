package com.chajeongnam.ecc_project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chajeongnam.ecc_project.R;
import com.chajeongnam.ecc_project.model.Student;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.ViewHolder> {

    private List<Student> studentList;

    public StudentListAdapter(List<Student> studentList) {
        this.studentList = studentList;
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        TextView studentNameTextView, testDateTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            studentNameTextView = itemView.findViewById(R.id.studentNameTextView);
            testDateTextView = itemView.findViewById(R.id.testDateTextView);
        }

        private void bind(Student student){
            studentNameTextView.setText(student.getName());
            /**
             * ToDo: set Student recent test Date on Firebase
             */
            testDateTextView.setText(student.getRecent());
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_student_list, parent, false);
        StudentListAdapter.ViewHolder viewHolder = new StudentListAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StudentListAdapter.ViewHolder holder, int position) {
        Student student = studentList.get(position);
        holder.bind(student);
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }
}

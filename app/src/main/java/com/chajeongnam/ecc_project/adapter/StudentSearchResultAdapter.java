package com.chajeongnam.ecc_project.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chajeongnam.ecc_project.R;
import com.chajeongnam.ecc_project.activity.StudentInfoActivity;
import com.chajeongnam.ecc_project.model.Student;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class StudentSearchResultAdapter extends RecyclerView.Adapter<StudentSearchResultAdapter.ViewHolder> {

    private List<Student> studentList;

    public StudentSearchResultAdapter(List<Student> studentList) {
        this.studentList = studentList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_student_search_result, parent, false);
        StudentSearchResultAdapter.ViewHolder viewHolder = new StudentSearchResultAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Student student = studentList.get(position);
        holder.bind(student);
    }

    @Override
    public int getItemCount() {
        return studentList.size();
    }

    protected class ViewHolder extends RecyclerView.ViewHolder{
        TextView studentGradeTextView, studentClassTextView, studentNameTextView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            studentGradeTextView = itemView.findViewById(R.id.studentGradeTextView);
            studentClassTextView = itemView.findViewById(R.id.studentClassTextView);
            studentNameTextView = itemView.findViewById(R.id.studentNameTextView);
        }

        private void bind(Student student){
            studentGradeTextView.setText(student.getGrade());
            studentClassTextView.setText(student.getAttrClass());
            studentNameTextView.setText(student.getName());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context , StudentInfoActivity.class);
                    intent.putExtra("name", studentNameTextView.getText());
                    intent.putExtra("recent", student.getRecent());
                    intent.putExtra("grade", student.getGrade() + " " + student.getAttrClass());
                    context.startActivity(intent);
                }
            });
        }
    }
}

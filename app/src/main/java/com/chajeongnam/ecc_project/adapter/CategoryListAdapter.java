package com.chajeongnam.ecc_project.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chajeongnam.ecc_project.R;
import com.chajeongnam.ecc_project.activity.StudentListActivity;
import com.chajeongnam.ecc_project.model.Category;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {

    private List<Category> categoryList;
    protected class ViewHolder extends RecyclerView.ViewHolder{
        private TextView categoryTextView;
        private TextView areaTextView;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryTextView = itemView.findViewById(R.id.categoryTextView);
            areaTextView = itemView.findViewById(R.id.areaTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context , StudentListActivity.class);
                    intent.putExtra("category", categoryTextView.getText());
                    intent.putExtra("area", areaTextView.getText().toString().replace("\n", " "));

                    context.startActivity(intent);
                }
            });
        }

        @Override
        public String toString() {
            return super.toString();
        }

        private void bind(Category category){
            categoryTextView.setText(category.getTitle());
            areaTextView.setText(category.getArea());
        }
    }


    public CategoryListAdapter(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.item_category, parent, false);
        CategoryListAdapter.ViewHolder viewHolder = new CategoryListAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = categoryList.get(position);
        holder.bind(category);
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }


}

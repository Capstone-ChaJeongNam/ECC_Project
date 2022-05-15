package com.chajeongnam.ecc_project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chajeongnam.ecc_project.CategoryListActivity;
import com.chajeongnam.ecc_project.R;
import com.chajeongnam.ecc_project.model.Category;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryListAdapter extends RecyclerView.Adapter<CategoryListAdapter.ViewHolder> {

    private List<Category> categoryList;
    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView categoryTextView;
        private TextView areaTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryTextView = itemView.findViewById(R.id.categoryTextView);
            areaTextView = itemView.findViewById(R.id.areaTextView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

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

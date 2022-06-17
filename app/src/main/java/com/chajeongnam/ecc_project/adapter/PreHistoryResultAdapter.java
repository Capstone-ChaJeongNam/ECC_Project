package com.chajeongnam.ecc_project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.chajeongnam.ecc_project.R;
import com.chajeongnam.ecc_project.model.PreChecklist;

import java.util.List;

public class PreHistoryResultAdapter extends BaseAdapter {

    private  Context context;
    private List<PreChecklist> checklistItem;

    public PreHistoryResultAdapter(List<PreChecklist> checklistItem){
        this.checklistItem = checklistItem;
    }



    @Override
    public int getCount() {
        return checklistItem.size();
    }

    @Override
    public Object getItem(int location) {
        return checklistItem.get(location);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final Context context = viewGroup.getContext();

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.checkedklist_item, viewGroup, false);

        }
        PreChecklist preChecklist = checklistItem.get(i);


        TextView id = (TextView) view.findViewById(R.id.textView1);
        TextView content = (TextView) view.findViewById(R.id.textView2);

        ImageView cb =(ImageView) view.findViewById(R.id.checkedBox);

        id.setText(String.valueOf(preChecklist.getId()+1)) ;
        content.setText(preChecklist.getContent());

        if(preChecklist.isResult()) {
            cb.setImageResource(R.drawable.letter_c);
        }

        return view;
    }

    public void addItem(int id, String content, String title_id) {
        PreChecklist item = new PreChecklist(id, content, title_id);

        item.setId(id);
        item.setContent(content);

        checklistItem.add(item);
    }

    public void check(){

    }

}

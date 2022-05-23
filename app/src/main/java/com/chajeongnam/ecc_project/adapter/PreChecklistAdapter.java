package com.chajeongnam.ecc_project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.chajeongnam.ecc_project.R;
import com.chajeongnam.ecc_project.model.PreChecklist;

import java.util.ArrayList;

public class PreChecklistAdapter extends BaseAdapter {

    private ArrayList<PreChecklist> checklistItem = new ArrayList<PreChecklist>();

    @Override
    public int getCount() {
        return checklistItem.size();
    }

    @Override
    public Object getItem(int i) {
        return checklistItem.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final int pos = i;
        final Context context = viewGroup.getContext();

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.checklist_item, viewGroup, false);
        }
        TextView id = (TextView) view.findViewById(R.id.textView1);
        TextView content = (TextView) view.findViewById(R.id.textView2);

        PreChecklist preChecklist = checklistItem.get(i);

        id.setText(preChecklist.getId());
        content.setText(preChecklist.getContent());
        return view;
    }

    public void addItem(String id, String content) {
        PreChecklist item = new PreChecklist();

        item.setId(id);
        item.setContent(content);

        checklistItem.add(item);
    }
}

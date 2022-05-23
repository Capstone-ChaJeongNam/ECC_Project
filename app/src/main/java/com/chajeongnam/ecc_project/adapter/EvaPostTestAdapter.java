package com.chajeongnam.ecc_project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chajeongnam.ecc_project.R;
import com.chajeongnam.ecc_project.model.TempList;

import java.util.List;

public class EvaPostTestAdapter extends RecyclerView.Adapter<EvaPostTestAdapter.ViewHolder> {
    private List<TempList> tempLists;
    private int selectedPosition=-1;


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.evaluate_posttest_category, parent, false);
        EvaPostTestAdapter.ViewHolder viewHolder = new EvaPostTestAdapter.ViewHolder(view);
        return viewHolder;
    }


    public EvaPostTestAdapter(List<TempList> tempLists) {
        this.tempLists = tempLists;
    }

    @Override
    public void onBindViewHolder(@NonNull EvaPostTestAdapter.ViewHolder holder, int position) {
        TempList tempList = tempLists.get(position);
        holder.bind(tempList);
   holder.radioButton1.setChecked(position==selectedPosition);

   holder.radioButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
       @Override
       public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
           if(b){
               selectedPosition=holder.getAdapterPosition();
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
        private RadioButton radioButton1,radioButton2,radioButton3;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            content = itemView.findViewById(R.id.content);
            radioButton1=itemView.findViewById(R.id.one);
            radioButton2=itemView.findViewById(R.id.two);
            radioButton3=itemView.findViewById(R.id.three);

        }

        @Override
        public String toString() {
            return super.toString();
        }

        private void bind(TempList tempList) {
            content.setText(tempList.getTitle());
        }
    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked

    }

}

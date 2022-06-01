package com.chajeongnam.ecc_project.decoration;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SetItemDecoration extends RecyclerView.ItemDecoration {
    private final int mPadding;

    public SetItemDecoration(int a_padding) {
        this.mPadding = a_padding;
    }

    @Override
    public void getItemOffsets(@NonNull Rect a_outRect, @NonNull View a_view, @NonNull RecyclerView a_parent, @NonNull RecyclerView.State a_state) {
        super.getItemOffsets(a_outRect, a_view, a_parent, a_state);
        if (a_parent.getChildAdapterPosition(a_view) != a_parent.getAdapter().getItemCount() - 1)

        a_outRect.bottom = mPadding;


    }
}

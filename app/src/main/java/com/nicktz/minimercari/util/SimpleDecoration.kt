package com.nicktz.minimercari.util

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class SimpleDecoration(private val margin: Int, private val spanCount: Int) :
    RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount

        outRect.left =
            margin - column * margin / spanCount
        outRect.right =
            (column + 1) * margin / spanCount

        if (position < spanCount) {
            outRect.top = margin
        }
        outRect.bottom = margin
    }
}
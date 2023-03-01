package com.abhishek.todo

import android.content.res.Resources
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class Utils{

}
class MarginItemDecoration(
    private var leftRight: Int =8.toPx,
    private var topBottom: Int =8.toPx,
    private var between: Int =4.toPx,
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect, view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                top = topBottom
            }
            left = leftRight
            right = leftRight
            bottom = between
            //last child
            val position = parent.layoutManager!!.getPosition(view)
            val itemCount = parent.adapter!!.itemCount
            if (position == itemCount - 1) {
                // this is the last child view
                bottom=topBottom
            }
        }
    }
}
val Number.toPx get() = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this.toFloat(),
    Resources.getSystem().displayMetrics).toInt()
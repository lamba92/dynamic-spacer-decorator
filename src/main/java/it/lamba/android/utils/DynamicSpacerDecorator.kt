package it.lamba.android.utils

import android.content.Context
import android.graphics.Point
import android.graphics.Rect
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.recyclerview.widget.RecyclerView

class DynamicSpacerDecorator(
    private val context: Context,
    private val spaceBetweenItems: Int,
    private val orientation: Orientation = Orientation.HORIZONTAL
) : RecyclerView.ItemDecoration() {

    enum class Orientation {
        VERTICAL, HORIZONTAL
    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {

        when (orientation) {
            Orientation.VERTICAL -> {
                val offset = context.screenHeight / 2 - view.layoutParams.height / 2
                if (parent.getChildAdapterPosition(view) == 0) {
                    (view.layoutParams as ViewGroup.MarginLayoutParams).topMargin = 0
                    (view.layoutParams as ViewGroup.MarginLayoutParams).bottomMargin = spaceBetweenItems / 2
                    setupOutRect(outRect, offset, true)
                } else if (parent.getChildAdapterPosition(view) == state.itemCount - 1) {
                    (view.layoutParams as ViewGroup.MarginLayoutParams).topMargin = spaceBetweenItems / 2
                    (view.layoutParams as ViewGroup.MarginLayoutParams).bottomMargin = 0
                    setupOutRect(outRect, offset, false)
                }
            }
            Orientation.HORIZONTAL -> {
                val offset = context.screenWidth / 2 - view.layoutParams.width / 2
                if (parent.getChildAdapterPosition(view) == 0) {
                    (view.layoutParams as ViewGroup.MarginLayoutParams).leftMargin = 0
                    (view.layoutParams as ViewGroup.MarginLayoutParams).rightMargin = spaceBetweenItems / 2
                    setupOutRect(outRect, offset, true)
                } else if (parent.getChildAdapterPosition(view) == state.itemCount - 1) {
                    (view.layoutParams as ViewGroup.MarginLayoutParams).leftMargin = spaceBetweenItems / 2
                    (view.layoutParams as ViewGroup.MarginLayoutParams).rightMargin = 0
                    setupOutRect(outRect, offset, false)
                }
            }
        }

    }

    private fun setupOutRect(rect: Rect, offset: Int, start: Boolean) {
        when (orientation) {
            Orientation.VERTICAL -> {
                if (start)
                    rect.top = offset
                else
                    rect.bottom = offset
            }
            Orientation.HORIZONTAL -> {
                if (start)
                    rect.left = offset
                else
                    rect.right = offset
            }
        }
    }

    private val Context.screenHeight: Int
        get() {
            val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val display = wm.defaultDisplay
            val size = Point()
            display.getSize(size)
            return size.y
        }

    private val Context.screenWidth: Int
        get() {
            val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager
            val display = wm.defaultDisplay
            val size = Point()
            display.getSize(size)
            return size.x
        }
}
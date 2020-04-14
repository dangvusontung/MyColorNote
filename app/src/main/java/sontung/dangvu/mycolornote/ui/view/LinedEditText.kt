package sontung.dangvu.mycolornote.ui.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.widget.EditText
import sontung.dangvu.mycolornote.R

class LinedEditText : EditText {

    private val rect : Rect = Rect()
    private val paint : Paint = Paint()

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        paint.style = Paint.Style.STROKE
        paint.color = resources.getColor(R.color.colorPrimary, null)
    }

//    override fun onDraw(canvas: Canvas?) {
//        val count = lineCount
//        val r = rect
//
//        for (i in 0..count) {
//            val baseLine = getLineBounds(i, r)
//            canvas?.drawLine(r.left.toFloat(), (baseLine+1).toFloat(),
//                r.right.toFloat(), (baseLine+1).toFloat(),paint)
//        }
//        super.onDraw(canvas)
//    }
}
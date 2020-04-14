package sontung.dangvu.mycolornote.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.LinearLayout
import sontung.dangvu.mycolornote.R
import sontung.dangvu.mycolornote.ex.inflate

class EditNoteTopBar : LinearLayout {

    constructor(context : Context) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        inflate(R.layout.edit_note_top_bar)
    }


}
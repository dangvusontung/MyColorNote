package sontung.dangvu.mycolornote.ui.listener

import sontung.dangvu.mycolornote.data.db.model.Note

interface OnNoteClickListener {
    fun viewNote(note : Note)
}
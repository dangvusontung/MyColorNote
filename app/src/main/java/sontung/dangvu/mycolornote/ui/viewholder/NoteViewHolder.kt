package sontung.dangvu.mycolornote.ui.viewholder

import android.view.LayoutInflater
import android.view.View
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.note_item_xml.view.*
import sontung.dangvu.mycolornote.R
import sontung.dangvu.mycolornote.data.db.model.Note
import sontung.dangvu.mycolornote.databinding.NoteItemXmlBinding
import sontung.dangvu.mycolornote.ui.listener.OnNoteClickListener

class NoteViewHolder(private val noteItemXmlBinding: NoteItemXmlBinding) :
    RecyclerView.ViewHolder(noteItemXmlBinding.root)
{

    private lateinit var onNoteClickListener: OnNoteClickListener
    private lateinit var cardView : CardView

    fun bindData(bindingNote : Note, listener : OnNoteClickListener) {
        noteItemXmlBinding.note = bindingNote
        setBackgroundColor(bindingNote)
        onNoteClickListener = listener
        cardView = noteItemXmlBinding.cardView.apply {
            setOnClickListener {
                onNoteClickListener.viewNote(bindingNote)
            }
        }
    }

    private fun setBackgroundColor(note: Note) {
        when (note.color) {
            R.color.colorPastelBlue -> setBackground(R.color.colorPastelBlue)
            R.color.colorPastelGreen -> setBackground(R.color.colorPastelGreen)
            R.color.colorPastelYellow -> setBackground(R.color.colorPastelYellow)
            R.color.colorPastelRed -> setBackground(R.color.colorPastelRed)
            R.color.colorPastelPurple -> setBackground(R.color.colorPastelPurple)
        }
    }

    private fun setBackground(colorRes : Int) {
        val color = noteItemXmlBinding.root.resources.getColor(colorRes, null)
        noteItemXmlBinding.root.item_background.setBackgroundColor(color)
    }

}
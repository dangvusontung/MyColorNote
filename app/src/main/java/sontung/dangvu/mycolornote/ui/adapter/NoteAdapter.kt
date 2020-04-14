package sontung.dangvu.mycolornote.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import sontung.dangvu.mycolornote.R
import sontung.dangvu.mycolornote.data.db.model.Note
import sontung.dangvu.mycolornote.databinding.NoteItemXmlBinding
import sontung.dangvu.mycolornote.ui.listener.OnNoteClickListener
import sontung.dangvu.mycolornote.ui.viewholder.NoteViewHolder

class NoteAdapter(
    private var notes : List<Note>,
    private val onNoteClickListener: OnNoteClickListener
) : RecyclerView.Adapter<NoteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<NoteItemXmlBinding>(inflater,R.layout.note_item_xml, parent, false)
        return NoteViewHolder(view)
    }

    override fun getItemCount() = notes.size

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = notes[position]
        holder.bindData(note, onNoteClickListener)
    }

    fun setNotes(newNotes : List<Note>) {
        notes = newNotes
        notifyDataSetChanged()
    }


}
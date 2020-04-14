package sontung.dangvu.mycolornote

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import android.widget.EditText
import android.widget.PopupMenu
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import sontung.dangvu.mycolornote.data.db.model.Note
import sontung.dangvu.mycolornote.data.viewmodel.NoteViewModel
import java.lang.NullPointerException

class AddNoteFragment : Fragment() {

    private val TAG = "AddNoteFragment"

    private val noteViewModel: NoteViewModel by activityViewModels()
    private lateinit var toolBar: Toolbar
    private lateinit var noteContentEditText: EditText
    private lateinit var currentNote: Note

    private lateinit var imm: InputMethodManager

    private val args: AddNoteFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        toolBar = view.findViewById(R.id.detail_toolbar)
        noteContentEditText = view.findViewById(R.id.edit_note_content)

        noteContentEditText.apply {
            addTextChangedListener {
                //TODO : Disable button when no text edited
            }
        }

        currentNote = getOrCreateNote()

        setMainText()

        toolBar.apply {
            inflateMenu(R.menu.note_fragment_menu)
            setupWithNavController(findNavController(), null)
            setOnMenuItemClickListener {
                when (it.itemId) {
                    R.id.action_save -> {
                        currentNote.noteContent = noteContentEditText.text.toString()
                        createOrUpdateNote(currentNote)
                        imm.hideSoftInputFromWindow(noteContentEditText.windowToken, 0)
                    }

                    R.id.action_delete -> {
                        updateNoteDB(ACTION_DELETE, currentNote)
                    }

                    R.id.action_changeTheme -> {
                        showColorDialog(view);
                    }

                    else -> return@setOnMenuItemClickListener false
                }

                true
            }
        }

    }

    private fun showColorDialog(view: View) {
        //TODO : ADD SHOW COLOR DIALOG
    }

    private fun setMainText(){
        noteContentEditText.setText(currentNote.noteContent)
    }

    private fun addNote(note: Note) {
        noteViewModel.addNote(note)
    }

    private fun updateNote(note: Note) {
        noteViewModel.updateNote(note)
    }

    private fun deleteNote(note: Note) {

        val dialog = AlertDialog.Builder(requireActivity())

        dialog.apply {
            setTitle("Delete")
            setMessage("Do you want to delete this note ?")
            setPositiveButton("Yes") { _, _ ->
                deleteNoteFromDB(note)
            }
            setNegativeButton("No") { _,_ -> }

            dialog.create().show()
        }


    }

    private fun deleteNoteFromDB(note: Note) {
        Thread(Runnable {
            noteViewModel.deleteNote(note)
            currentNote = Note(System.currentTimeMillis(), "", R.color.colorPastelBlue)
            setMainText()
        }).start()
    }

    private fun getOrCreateNote(): Note {
        if (args.note == null) {
            val currentTime = System.currentTimeMillis()
            val content = ""
            return Note(currentTime, content, R.color.colorPastelBlue)
        }
        return args.note!!
    }

    private fun checkIfExistNote(timeCreated: Long): Note {
        return noteViewModel.checkIfExistNote(timeCreated)
    }

    companion object {
        const val ACTION_ADD = 1
        const val ACTION_UPDATE = 2
        const val ACTION_DELETE = 3
    }

    private fun updateNoteDB(action: Int, note: Note) {
        when (action) {
            ACTION_ADD -> addNote(note)
            ACTION_UPDATE -> updateNote(note)
            ACTION_DELETE -> deleteNote(note)
        }
    }

    private fun createOrUpdateNote(note: Note) {
        val currentNoteTime = note.timeCreated

        Thread(Runnable {
            Log.d(TAG, "from check duplicate : ${checkIfExistNote(currentNoteTime)}")
            if (note == checkIfExistNote(currentNoteTime)) {
                updateNoteDB(ACTION_UPDATE, note)
            } else {
                updateNoteDB(ACTION_ADD, note)
            }
        }).start()
    }
}

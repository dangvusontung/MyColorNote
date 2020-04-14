package sontung.dangvu.mycolornote

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import sontung.dangvu.mycolornote.data.db.model.Note
import sontung.dangvu.mycolornote.data.viewmodel.NoteViewModel
import sontung.dangvu.mycolornote.ui.adapter.NoteAdapter
import sontung.dangvu.mycolornote.ui.listener.OnNoteClickListener
import java.util.*
import kotlin.collections.ArrayList

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ListNoteFragment : Fragment(), OnNoteClickListener {

    private val noteViewModel : NoteViewModel by activityViewModels()
    private lateinit var noteAdapter : NoteAdapter
    private lateinit var noteRecyclerView: RecyclerView

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_first, container, false)
        noteAdapter = NoteAdapter(ArrayList(), this)
        noteRecyclerView = view.findViewById(R.id.noteRecyclerView)
        noteRecyclerView.apply {
//            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            layoutManager = GridLayoutManager(activity, 3)
            adapter = noteAdapter
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteViewModel.getAllNotes().observe(viewLifecycleOwner, Observer {
            Log.d("ListNoteFragment", "onObserver")
            noteAdapter.setNotes(it)
        })
        view.findViewById<FloatingActionButton>(R.id.floating).setOnClickListener {
            findNavController().navigate(R.id.createNote)
        }

        val navController = findNavController()

        val appBarConfiguration = AppBarConfiguration(navController.graph)

        view.findViewById<androidx.appcompat.widget.Toolbar>(R.id.list_toolbar)
            .setupWithNavController(navController, appBarConfiguration)

    }

    override fun viewNote(note: Note) {
        val action = ListNoteFragmentDirections.createNote(note)
        Navigation.findNavController(requireView()).navigate(action)
    }

}

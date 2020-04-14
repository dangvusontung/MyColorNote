package sontung.dangvu.mycolornote.data.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import sontung.dangvu.mycolornote.data.db.AppDatabase
import sontung.dangvu.mycolornote.data.db.model.Note
import sontung.dangvu.mycolornote.data.repo.NoteRepository

class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private val database = AppDatabase.getInstance(application)
    private val noteRepository = NoteRepository(database.noteDao())

    fun addNote(note: Note) {
        noteRepository.addNote(note)
    }

    fun deleteNote(note: Note) {
        noteRepository.deleteNote(note)
    }

    fun getAllNotes() : LiveData<List<Note>> {
        return noteRepository.getAllNotes()
    }

    fun updateNote(note: Note) {
        noteRepository.updateNote(note)
    }

    fun checkIfExistNote(timeCreated : Long) : Note = noteRepository.checkIfExistNote(timeCreated)

}
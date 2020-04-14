package sontung.dangvu.mycolornote.data.repo

import androidx.lifecycle.LiveData
import sontung.dangvu.mycolornote.data.db.dao.NoteDao
import sontung.dangvu.mycolornote.data.db.model.Note

class NoteRepository(private val noteDao: NoteDao) {

    fun addNote(note: Note) {
        noteDao.addNote(note)
    }

    fun deleteNote(note: Note) {
        noteDao.deleteNote(note)
    }

    fun getAllNotes() : LiveData<List<Note>> {
        return noteDao.getAllNotes()
    }

    fun updateNote(note: Note) {
        noteDao.updateNote(note)
    }

    fun checkIfExistNote(timeCreated : Long) : Note {
        return noteDao.checkIfExistNote(timeCreated)
    }


}
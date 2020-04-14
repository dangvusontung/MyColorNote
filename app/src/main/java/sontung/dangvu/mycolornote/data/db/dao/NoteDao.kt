package sontung.dangvu.mycolornote.data.db.dao

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.room.*
import sontung.dangvu.mycolornote.data.db.model.Note
import sontung.dangvu.mycolornote.data.db.model.TABLE_NAME

@Dao
interface NoteDao {

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun addNote(note : Note)

    @Delete
    fun deleteNote(note: Note)

    @Query ("SELECT * FROM note_table")
    fun getAllNotes() : LiveData<List<Note>>

    @Update
    fun updateNote(note: Note)

    @Query ("SELECT * FROM note_table WHERE timeCreated = :timeCreatedQuery")
    fun checkIfExistNote(timeCreatedQuery : Long) : Note

}
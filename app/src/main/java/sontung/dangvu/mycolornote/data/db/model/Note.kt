package sontung.dangvu.mycolornote.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

const val TABLE_NAME = "note_table"

@Entity (tableName = TABLE_NAME)
data class Note(
    @PrimaryKey
    val timeCreated : Long,
    var noteContent : String,
    val color : Int?
) : Serializable {
    override fun toString(): String {
        return "Note(timeCreated=$timeCreated, noteContent='$noteContent', color=$color)"
    }
}
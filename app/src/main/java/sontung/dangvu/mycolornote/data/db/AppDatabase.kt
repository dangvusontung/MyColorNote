package sontung.dangvu.mycolornote.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import sontung.dangvu.mycolornote.data.db.dao.NoteDao
import sontung.dangvu.mycolornote.data.db.model.Note

@Database (
    entities = [Note::class],
    exportSchema = false,
    version = 3
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao() : NoteDao

    companion object {
        private var INSTANCE : AppDatabase? = null

        fun getInstance(context: Context) : AppDatabase {
            var tempInstance = INSTANCE

            if (tempInstance != null) {
                return tempInstance
            }

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "app_db"
                ).build()

                INSTANCE = instance
                return INSTANCE as AppDatabase
            }
        }
    }
}
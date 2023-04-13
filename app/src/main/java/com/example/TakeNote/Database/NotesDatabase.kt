package com.example.TakeNote.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.TakeNote.Dao.NotesDao
import com.example.TakeNote.Model.Notes

@Database([Notes::class], version = 3)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao

    companion object {
        @Volatile
        private var INSTANCE: NotesDatabase? = null

        @Synchronized
        fun getInstance(context: Context): NotesDatabase {
            if (INSTANCE == null) {
                INSTANCE =
                    Room.databaseBuilder(context, NotesDatabase::class.java, "Notes Database")
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return INSTANCE as NotesDatabase
        }


    }
}
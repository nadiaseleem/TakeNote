package com.example.TakeNote.viewModels.Repository

import com.example.TakeNote.Database.NotesDatabase
import com.example.TakeNote.Model.Notes
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NotesRepository @Inject constructor(
    private val notesDatabase: NotesDatabase
) {


    suspend fun getAllNotes(): Flow<List<Notes>> {


        return notesDatabase.notesDao().getAllNotes()
    }

    suspend fun insertNote(notes: Notes) {

        notesDatabase.notesDao().insertNotes(notes)

    }

    suspend fun deleteNote(id: Int) {

        notesDatabase.notesDao().deleteNotes(id)

    }

    suspend fun updateNote(notes: Notes) {

        notesDatabase.notesDao().insertNotes(notes)

    }

    suspend fun getNotes(id: Int): Flow<Notes> {

        return notesDatabase.notesDao().getNotes(id)
    }

    suspend fun getFilteredNotes(priority: String): Flow<List<Notes>> {

        return notesDatabase.notesDao().getFilteredNotes(priority)

    }

}
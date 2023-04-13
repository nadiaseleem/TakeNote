package com.example.TakeNote.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.TakeNote.Model.Notes
import com.example.TakeNote.viewModels.Repository.NotesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(
    private val repository: NotesRepository
) : ViewModel() {

    private val _allNotesStateFlow = MutableStateFlow<List<Notes>>(emptyList())
    private val _filteredNotesStateFlow = MutableStateFlow<List<Notes>>(emptyList())
    private val _notesStateFlow = MutableStateFlow<Notes>(Notes(0, "title", "body", "12 sep", "1"))

    val allNotesStateFlow = _allNotesStateFlow
    val filteredNotesStateFlow = _filteredNotesStateFlow
    val notesStateFlow = _notesStateFlow


    private lateinit var toEditNote: Notes

    fun getAllNotes(): MutableStateFlow<List<Notes>> {
        viewModelScope.launch(Dispatchers.IO) {

            repository.getAllNotes().collect() {
                _allNotesStateFlow.value = it
            }
        }
        return allNotesStateFlow
    }

    fun addNote(notes: Notes) {

        viewModelScope.launch(Dispatchers.IO) {
            repository.insertNote(notes)
        }
    }

    fun deleteNote(id: Int) {

        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteNote(id)
        }

    }

    fun updateNotes(notes: Notes) {

        _notesStateFlow.value?.let { note ->

            toEditNote = Notes(
                id = note.id,
                title = notes.title,
                notes = notes.notes,
                date = notes.date,
                priority = notes.priority
            )

        }
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateNote(toEditNote)

        }
    }

    fun getNotes(id: Int): MutableStateFlow<Notes> {
        viewModelScope.launch(Dispatchers.IO) {

            repository.getNotes(id).collect() {
                _notesStateFlow.value = it
            }

        }
        return notesStateFlow
    }

    fun getFilteredNotes(priority: String): MutableStateFlow<List<Notes>> {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getFilteredNotes(priority).collect() {
                _filteredNotesStateFlow.value = it
            }

        }
        return filteredNotesStateFlow
    }


}
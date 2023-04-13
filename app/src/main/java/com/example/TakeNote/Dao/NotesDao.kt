package com.example.TakeNote.Dao

import androidx.room.*
import com.example.TakeNote.Model.Notes
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Query("SELECT * FROM Notes")
    fun getAllNotes(): Flow<List<Notes>>

    @Query("SELECT * FROM Notes WHERE priority=:priority")
    fun getFilteredNotes(priority: String): Flow<List<Notes>>


    @Query("SELECT * FROM Notes WHERE id=:id ")
    fun getNotes(id: Int): Flow<Notes>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNotes(notes: Notes)

    @Query("DELETE FROM Notes WHERE id=:id")
    suspend fun deleteNotes(id: Int)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateNotes(notes: Notes)


}
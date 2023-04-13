package com.example.TakeNote.di

import android.app.Application
import com.example.TakeNote.Database.NotesDatabase
import com.example.TakeNote.viewModels.Repository.NotesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesNotesDatabase(context: Application): NotesDatabase {
        return NotesDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun providesNotesRepository(notesDatabase: NotesDatabase): NotesRepository {
        return NotesRepository(notesDatabase)
    }

}
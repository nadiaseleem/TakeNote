package com.example.TakeNote.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("Notes")
class Notes(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    var title: String,
    var notes: String,
    var date: String,
    var priority: String,
)

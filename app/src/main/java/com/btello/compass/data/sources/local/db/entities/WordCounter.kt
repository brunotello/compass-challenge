package com.btello.compass.data.sources.local.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class WordCounter(
    @PrimaryKey val word: String,
    val counter: Int
)
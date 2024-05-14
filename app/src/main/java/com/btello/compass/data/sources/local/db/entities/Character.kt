package com.btello.compass.data.sources.local.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Character(
    @PrimaryKey val id: Int,
    val character: Char
)
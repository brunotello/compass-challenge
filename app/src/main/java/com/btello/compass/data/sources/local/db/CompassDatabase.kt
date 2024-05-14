package com.btello.compass.data.sources.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.btello.compass.data.sources.local.db.daos.CharactersDao
import com.btello.compass.data.sources.local.db.daos.WordCounterDao
import com.btello.compass.data.sources.local.db.entities.Character
import com.btello.compass.data.sources.local.db.entities.WordCounter

@Database(entities = [Character::class, WordCounter::class], version = 1, exportSchema = false)
abstract class CompassDatabase : RoomDatabase() {

    abstract fun characterDao(): CharactersDao

    abstract fun wordCounterDao(): WordCounterDao
}
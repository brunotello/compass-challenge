package com.btello.compass.data.sources.local

import com.btello.compass.CompassApplication.Companion.db
import com.btello.compass.data.sources.local.db.entities.Character
import com.btello.compass.data.sources.local.db.entities.WordCounter

class CompassDbLocalDataSource {

    fun getCharacters() = db.characterDao().getAll()

    suspend fun insertCharacters(characters: List<Char>) {
        db.characterDao().insertAll(characters.mapIndexed { index, char ->
            Character(index, char)
        })
    }

    suspend fun deleteCharacters() {
        db.characterDao().deleteAll()
    }

    fun getWordCounters() = db.wordCounterDao().getAll()

    suspend fun insertWordCounters(wordCounter: List<WordCounter>) {
        db.wordCounterDao().insertAll(wordCounter)
    }

    suspend fun deleteWordCounters() {
        db.wordCounterDao().deleteAll()
    }
}
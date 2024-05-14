package com.btello.compass.data.repositories

import com.btello.compass.data.sources.local.CompassDbLocalDataSource
import com.btello.compass.data.sources.remote.CompassRemoteDataSource
import com.btello.compass.data.sources.local.db.entities.WordCounter

class CompassRepository(
    private val remoteDataSource: CompassRemoteDataSource = CompassRemoteDataSource(),
    private val localDataSource: CompassDbLocalDataSource = CompassDbLocalDataSource()
) {

    suspend fun getHTML(): String {
        return remoteDataSource.getHTML()
    }

    fun get10thCharacters() = localDataSource.getCharacters()


    suspend fun insert10thCharacters(characters: List<Char>) {
        localDataSource.insertCharacters(characters)
    }

    fun getWordCounter() = localDataSource.getWordCounters()

    suspend fun insertWordCounter(wordCounter: List<WordCounter>) {
        localDataSource.insertWordCounters(wordCounter)
    }

    suspend fun deleteDb() {
        with(localDataSource) {
            deleteCharacters()
            deleteWordCounters()
        }
    }
}
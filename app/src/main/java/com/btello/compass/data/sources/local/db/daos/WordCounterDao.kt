package com.btello.compass.data.sources.local.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.btello.compass.data.sources.local.db.entities.WordCounter
import kotlinx.coroutines.flow.Flow

@Dao
interface WordCounterDao {

    @Query("SELECT * FROM WordCounter")
    fun getAll(): Flow<List<WordCounter>>

    @Insert
    suspend fun insertAll(wordCounter: List<WordCounter>)

    @Query("DELETE FROM WordCounter")
    suspend fun deleteAll()
}
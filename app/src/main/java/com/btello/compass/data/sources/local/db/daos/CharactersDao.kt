package com.btello.compass.data.sources.local.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.btello.compass.data.sources.local.db.entities.Character
import kotlinx.coroutines.flow.Flow

@Dao
interface CharactersDao {

    @Query("SELECT character FROM Character")
    fun getAll(): Flow<List<Char>>

    @Insert
    suspend fun insertAll(characters: List<Character>)

    @Query("DELETE FROM Character")
    suspend fun deleteAll()
}
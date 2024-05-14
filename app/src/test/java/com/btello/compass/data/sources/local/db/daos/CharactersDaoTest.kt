package com.btello.compass.data.sources.local.db.daos

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.btello.compass.MainCoroutineRule
import com.btello.compass.data.sources.local.db.CompassDatabase
import com.btello.compass.data.sources.local.db.entities.Character
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class CharactersDaoTest {

   /* @JvmField
    @get:Rule
    var rule = MainCoroutineRule()

    private lateinit var db: CompassDatabase
    private lateinit var dao: CharactersDao

    @Before
    fun before() {
        val context = getApplicationContext<Context>()

        db = Room.inMemoryDatabaseBuilder(context, CompassDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        dao = db.characterDao()
    }

    @After
    fun after() {
        db.close()
    }

    @Test
    fun insertWord_returnsTrue() {
        runTest {
            val characters = listOf(Character(id = 1, character = 'a'))
            dao.insertAll(characters)

            dao.getAll().collect {
                assertEquals(characters.first(), it)
            }
        }
    }*/
}
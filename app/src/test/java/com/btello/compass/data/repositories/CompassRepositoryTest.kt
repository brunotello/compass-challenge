package com.btello.compass.data.repositories

import com.btello.compass.data.sources.local.CompassDbLocalDataSource
import com.btello.compass.data.sources.local.db.entities.WordCounter
import com.btello.compass.data.sources.remote.CompassRemoteDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class CompassRepositoryTest {

    private val remoteDataSource = mockk<CompassRemoteDataSource>()
    private val localDataSource = mockk<CompassDbLocalDataSource>()

    private val repository = CompassRepository(remoteDataSource, localDataSource)

    @Test
    fun `getHTML should return a String`() {
        runTest {
            coEvery { remoteDataSource.getHTML() } returns "html"

            val result = repository.getHTML()

            coVerify(exactly = 1) { remoteDataSource.getHTML() }
            assertEquals("html", result)
        }
    }

    @Test
    fun `get10thCharacters should return list of Char`() {
        runTest {
            coEvery { localDataSource.getCharacters() } returns flowOf(listOf('a'))

            val result = repository.get10thCharacters().first()

            coVerify(exactly = 1) { localDataSource.getCharacters() }
            assertEquals(1, result.size)
            assertEquals('a', result.first())
        }
    }

    @Test
    fun `getWordCounter should return list of WordCounter`() {
        runTest {
            coEvery { localDataSource.getWordCounters() } returns flowOf(
                listOf(
                    WordCounter(
                        word = "word",
                        counter = 1
                    )
                )
            )

            val result = repository.getWordCounter().first()

            coVerify(exactly = 1) { localDataSource.getWordCounters() }
            assertEquals(1, result.size)
            assertEquals("word", result.first().word)
            assertEquals(1, result.first().counter)
        }
    }
}
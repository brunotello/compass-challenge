package com.btello.compass.domain.usecases

import com.btello.compass.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class GetEvery10thCharacterUseCaseTest {

    @get:Rule
    var rule = MainCoroutineRule()

    private lateinit var useCase: GetEvery10thCharacterUseCase

    @Before
    fun before() {
        useCase = GetEvery10thCharacterUseCase(dispatcher = UnconfinedTestDispatcher())
    }

    @Test
    fun `invoke when string is length 0 should return empty character`() {
        runTest {
            val result = useCase.invoke("")

            assertEquals(0, result.size)
        }
    }

    @Test
    fun `invoke when string is length 9 should return empty character`() {
        runTest {
            val result = useCase.invoke("abcdefghi")

            assertEquals(0, result.size)
        }
    }

    @Test
    fun `invoke when string is length 10 should return 1 character`() {
        runTest {
            val result = useCase.invoke("abcdefghij")

            assertEquals(1, result.size)
            assertEquals('j', result[0])
        }
    }

    @Test
    fun `invoke when string is length 20 should return 2 characters`() {
        runTest {
            val result = useCase.invoke("abcdefghijklmnopqrst")


            assertEquals(2, result.size)
            assertEquals('j', result[0])
            assertEquals('t', result[1])
        }
    }
}
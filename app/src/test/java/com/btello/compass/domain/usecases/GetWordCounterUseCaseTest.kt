package com.btello.compass.domain.usecases

import com.btello.compass.MainCoroutineRule
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetWordCounterUseCaseTest {

    @get:Rule
    var rule = MainCoroutineRule()

    private lateinit var useCase: GetWordCounterUseCase

    @Before
    fun before() {
        useCase = GetWordCounterUseCase(dispatcher = UnconfinedTestDispatcher())
    }

    @Test
    fun `invoke when string is empty should return empty map`() {
        runTest {
            val result = useCase.invoke("")

            assertEquals(0, result.size)
        }
    }

    @Test
    fun `invoke when string does not have repeated words should return 1 for every value`() {
        runTest {
            val result = useCase.invoke("This is a string without repeated words")

            assertEquals(7, result.size)
            result.forEach { word -> assertEquals(1, word.counter) }
        }
    }

    @Test
    fun `invoke when string have 1 repeated word should return 2 for value word`() {
        runTest {
            val result = useCase.invoke("This is a string without repeated words words")

            assertEquals(7, result.size)
            assertEquals(2, result.first().counter)
        }
    }
}
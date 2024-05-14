package com.btello.compass.ui.main

import com.btello.compass.MainCoroutineRule
import com.btello.compass.data.repositories.CompassRepository
import com.btello.compass.data.sources.local.db.entities.WordCounter
import com.btello.compass.domain.usecases.GetEvery10thCharacterUseCase
import com.btello.compass.domain.usecases.GetWordCounterUseCase
import com.btello.compass.ui.main.MainUIContract.Event.OnButtonClick
import com.btello.compass.ui.main.MainUIContract.State.ShowButton
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class MainViewModelTest {

    @get:Rule
    var rule = MainCoroutineRule()

    private val repository = mockk<CompassRepository>()
    private val getEvery10thCharacterUseCase = mockk<GetEvery10thCharacterUseCase>()
    private val getWordCounterUseCase = mockk<GetWordCounterUseCase>()
    private lateinit var viewModel: MainViewModel

    @Before
    fun before() {
        coEvery { repository.getWordCounter() } returns flowOf(
            listOf(
                WordCounter(
                    word = "word",
                    counter = 1
                )
            )
        )
        coEvery { repository.get10thCharacters() } returns flowOf(listOf('a'))
        coEvery { repository.deleteDb() } returns Unit

        viewModel = MainViewModel(
            repository,
            getEvery10thCharacterUseCase,
            getWordCounterUseCase,
            UnconfinedTestDispatcher()
        )
    }

    @Test
    fun `init should emit ShowButton state`() {
        assertEquals(ShowButton, viewModel.state.value)
    }


    @Test
    fun `onEvent OnButtonClick should delete DB, getHTML and emit values`() {
        runTest {
            viewModel.onEvent(OnButtonClick)

            coVerify(exactly = 1) { repository.deleteDb() }
            coVerify(exactly = 1) { repository.getHTML() }
            coVerify(exactly = 1) { repository.getWordCounter() }
            coVerify(exactly = 1) { repository.get10thCharacters() }
        }
    }
}
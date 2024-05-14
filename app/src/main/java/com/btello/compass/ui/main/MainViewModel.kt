package com.btello.compass.ui.main

import androidx.lifecycle.viewModelScope
import com.btello.compass.data.repositories.CompassRepository
import com.btello.compass.domain.usecases.GetEvery10thCharacterUseCase
import com.btello.compass.domain.usecases.GetWordCounterUseCase
import com.btello.compass.ui.base.BaseViewModel
import com.btello.compass.ui.main.MainUIContract.Event
import com.btello.compass.ui.main.MainUIContract.Event.OnButtonClick
import com.btello.compass.ui.main.MainUIContract.Event.OnInit
import com.btello.compass.ui.main.MainUIContract.SideEffect
import com.btello.compass.ui.main.MainUIContract.SideEffect.Error
import com.btello.compass.ui.main.MainUIContract.State
import com.btello.compass.ui.main.MainUIContract.State.ShowButton
import com.btello.compass.ui.main.MainUIContract.State.Loading
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: CompassRepository = CompassRepository(),
    private val getEvery10thCharacterUseCase: GetEvery10thCharacterUseCase = GetEvery10thCharacterUseCase(),
    private val getWordCounterUseCase: GetWordCounterUseCase = GetWordCounterUseCase(),
    private val dispatcher: CoroutineDispatcher = IO
) : BaseViewModel<State, Event, SideEffect>() {

    val wordCounter = repository.getWordCounter()
    val characters = repository.get10thCharacters()

    init {
        onEvent(OnInit)
    }

    override fun setInitialState() = ShowButton

    override fun onEvent(event: Event) {
        when (event) {
            OnButtonClick -> onButtonClick()
            OnInit -> setState { ShowButton }
        }
    }

    private fun onButtonClick() {
        viewModelScope.launch(dispatcher) {
            setState { Loading }

            repository.deleteDb()

            getRemoteContent()
        }
    }

    private fun getRemoteContent() {
        viewModelScope.launch(dispatcher + CoroutineExceptionHandler { _, exception ->
            setEffect { Error(exception.message) }
            setState { ShowButton }
        }) {
            val html = repository.getHTML()

            getWordCounter(html)
            get10thCharacters(html)

            setState { ShowButton }
        }
    }

    private suspend fun getWordCounter(html: String) {
        val wordCounter = getWordCounterUseCase(html)
        viewModelScope.launch(dispatcher) {
            repository.insertWordCounter(wordCounter)
        }
    }

    private suspend fun get10thCharacters(html: String) {
        val characters = getEvery10thCharacterUseCase(html)

        viewModelScope.launch(dispatcher) {
            repository.insert10thCharacters(characters)
        }
    }
}
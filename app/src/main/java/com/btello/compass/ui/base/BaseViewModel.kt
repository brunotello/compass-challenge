package com.btello.compass.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<S : UIState, E : UIEvent, SE : UISideEffect> : ViewModel() {

    private val initialState: S by lazy { setInitialState() }
    abstract fun setInitialState(): S

    private val currentState: S
        get() = state.value

    private val _state: MutableStateFlow<S> = MutableStateFlow(initialState)
    val state = _state.asStateFlow()

    private val _event: MutableSharedFlow<E> = MutableSharedFlow()
    val event = _event.asSharedFlow()

    private val _effect: Channel<UISideEffect> = Channel()
    val effect = _effect.receiveAsFlow()

    init {
        subscribeEvents()
    }

    private fun subscribeEvents() {
        viewModelScope.launch { event.collect { onEvent(it) } }
    }

    abstract fun onEvent(event: E)

    protected fun setState(reduce: S.() -> S) {
        val newState = state.value.reduce()
        _state.value = newState
    }

    protected fun setEffect(builder: () -> SE) {
        val effectValue = builder()
        viewModelScope.launch { _effect.send(effectValue) }
    }
}
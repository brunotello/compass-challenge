package com.btello.compass.ui.main

import com.btello.compass.ui.base.UIEvent
import com.btello.compass.ui.base.UISideEffect
import com.btello.compass.ui.base.UIState

class MainUIContract {
    sealed class Event : UIEvent {
        data object OnInit : Event()
        data object OnButtonClick : Event()
    }

    sealed class State : UIState {
        data object Loading : State()
        data object ShowButton : State()
    }

    sealed class SideEffect : UISideEffect {
        data class Error(val message: String? = "") : SideEffect()
    }
}
package com.btello.compass.ui.main.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.btello.compass.ui.base.composables.ProgressBar
import com.btello.compass.ui.main.MainUIContract.Event.OnButtonClick
import com.btello.compass.ui.main.MainUIContract.SideEffect.Error
import com.btello.compass.ui.main.MainUIContract.State.Loading
import com.btello.compass.ui.main.MainUIContract.State.ShowButton
import com.btello.compass.ui.main.MainViewModel

@Composable
fun MainRoute(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel = viewModel()
) {
    val snackbarHostState = remember { SnackbarHostState() }

    val state = viewModel.state.collectAsState()
    val wordCounter = viewModel.wordCounter.collectAsState(initial = listOf())
    val characters = viewModel.characters.collectAsState(initial = listOf())

    MainScreen(
        modifier,
        wordCounter = wordCounter.value,
        characters = characters.value,
        snackbarHostState = snackbarHostState
    ) {
        when (state.value) {
            Loading -> ProgressBar()
            ShowButton -> {
                Button(
                    onClick = { viewModel.onEvent(OnButtonClick) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Text("Download")
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.effect.collect {
            when (it) {
                is Error -> snackbarHostState.showSnackbar(it.message.toString())
            }
        }
    }
}
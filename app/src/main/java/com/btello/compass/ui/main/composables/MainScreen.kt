package com.btello.compass.ui.main.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.btello.compass.data.sources.local.db.entities.WordCounter
import com.btello.compass.ui.base.composables.PageIndicator

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    wordCounter: List<WordCounter>,
    characters: List<Char>,
    snackbarHostState: SnackbarHostState,
    footer: @Composable () -> Unit = {}
) {
    val pagerState = rememberPagerState(pageCount = { 2 })

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        content = {
            Column(
                modifier = modifier
                    .padding(all = 16.dp)
            ) {
                Text(
                    modifier = Modifier.padding(top = 32.dp),
                    text = "Compass",
                    style = MaterialTheme.typography.headlineLarge
                )

                Column(
                    modifier = Modifier.weight(1f),
                ) {
                    if (characters.isNotEmpty() && wordCounter.isNotEmpty()) {
                        PageIndicator(
                            modifier = Modifier.padding(vertical = 8.dp),
                            pagerState = pagerState
                        )

                        HorizontalPager(state = pagerState)
                        { page ->
                            if (page == 0) {
                                Every10thCharacterList(
                                    title = "Every 10th Character",
                                    characters = characters
                                )
                            } else {
                                WordCounterList(
                                    title = "Word Counter",
                                    wordCounter = wordCounter
                                )
                            }
                        }
                    }
                }

                footer()
            }
        })
}
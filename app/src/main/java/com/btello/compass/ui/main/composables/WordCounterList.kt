package com.btello.compass.ui.main.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.btello.compass.data.sources.local.db.entities.WordCounter
import com.btello.compass.ui.theme.CompassTheme

@Composable
fun WordCounterList(modifier: Modifier = Modifier, title: String, wordCounter: List<WordCounter>) {
    Column(
        modifier = modifier.padding(vertical = 16.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.headlineMedium
        )

        LazyColumn(
            modifier = modifier
                .padding(top = 16.dp)
        ) {
            items(wordCounter.toList()) { item ->
                ListItem(
                    headlineContent = {
                        Text(
                            text = item.word,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    },
                    trailingContent = {
                        Text(
                            text = item.counter.toString(),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    })
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun WordCounterListPreview() {
    CompassTheme {
        WordCounterList(title = "title", wordCounter = listOf(WordCounter(word = "Word", counter = 1)))
    }
}
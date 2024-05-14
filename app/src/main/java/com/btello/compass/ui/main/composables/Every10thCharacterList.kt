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
import com.btello.compass.ui.theme.CompassTheme

@Composable
fun Every10thCharacterList(modifier: Modifier = Modifier, title: String, characters: List<Char>) {

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
            items(characters) { char ->
                ListItem(
                    headlineContent = {
                        Text(
                            text = char.toString(),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    })
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun Every10thCharacterListPreview() {
    CompassTheme {
        Every10thCharacterList(title = "title", characters = listOf('a', 'b', 'c'))
    }
}
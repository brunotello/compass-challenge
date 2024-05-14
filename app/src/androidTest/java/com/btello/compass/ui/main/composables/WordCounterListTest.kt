package com.btello.compass.ui.main.composables

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.btello.compass.data.sources.local.db.entities.WordCounter
import org.junit.Rule
import org.junit.Test

class WordCounterListTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun expandableItemNodesAreDisplayed() {
        composeTestRule.setContent {
            WordCounterList(title = "title", wordCounter = listOf(WordCounter(word = "word", counter = 1)))
        }

        composeTestRule.onNodeWithText("title").assertIsDisplayed()
        composeTestRule.onNodeWithText("word").assertIsDisplayed()
        composeTestRule.onNodeWithText("1").assertIsDisplayed()
    }
}
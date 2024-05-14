package com.btello.compass.domain.usecases

import com.btello.compass.data.sources.local.db.entities.WordCounter
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.withContext
import java.util.Locale.getDefault

class GetWordCounterUseCase(
    private val dispatcher: CoroutineDispatcher = Default
) {

    suspend operator fun invoke(html: String): List<WordCounter> {
        return withContext(dispatcher) {
            val wordCounter = mutableMapOf<String, Int>()

            for (word in html.splitByWhiteSpace()) {
                val normalizedWord = word.lowercase(getDefault())
                val count = wordCounter.getOrDefault(normalizedWord, 0)

                wordCounter[normalizedWord] = count + 1
            }

            return@withContext wordCounter
                .map { WordCounter(it.key, it.value) }
                .sortedByDescending { it.counter }
        }
    }

    private fun String.splitByWhiteSpace(): List<String> {
        return this.split("\\s+".toRegex()).filter { it.isNotEmpty() }
    }
}
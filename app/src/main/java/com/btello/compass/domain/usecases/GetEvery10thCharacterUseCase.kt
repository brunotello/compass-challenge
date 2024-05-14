package com.btello.compass.domain.usecases

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers.Default
import kotlinx.coroutines.withContext

class GetEvery10thCharacterUseCase(
    private val dispatcher: CoroutineDispatcher = Default
) {

    suspend operator fun invoke(html: String): List<Char> {
        return withContext(dispatcher) {
            val characters = mutableListOf<Char>()
            val lastIndex = html.length - 1

            if (html.length >= 10) {
                for (i in 9..lastIndex step 10) {
                    if (!html[i].isWhitespace())
                        characters.add(html[i])
                }
            }

            return@withContext characters
        }
    }
}
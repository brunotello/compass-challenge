package com.btello.compass.data.sources.remote

import com.btello.compass.BuildConfig.COMPASS_ABOUT
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.withContext
import java.net.HttpURLConnection
import java.net.URL

class CompassRemoteDataSource(
    private val url: URL = URL(COMPASS_ABOUT),
    private val dispatcher: CoroutineDispatcher = IO
) {

    suspend fun getHTML(): String {
        return withContext(dispatcher) {
            val urlConnection = url.openConnection() as HttpURLConnection

            try {
                return@withContext urlConnection.inputStream.bufferedReader().readText()
            } finally {
                urlConnection.disconnect()
            }
        }

    }
}
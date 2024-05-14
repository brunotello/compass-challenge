package com.btello.compass.data.sources.remote

import com.btello.compass.MainCoroutineRule
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.ByteArrayInputStream
import java.net.HttpURLConnection
import java.net.URL

@OptIn(ExperimentalCoroutinesApi::class)
class CompassRemoteDataSourceTest {

    @get:Rule
    var rule = MainCoroutineRule()

    private lateinit var url: URL
    private lateinit var urlConnection: HttpURLConnection

    private lateinit var dataSource: CompassRemoteDataSource

    @Before
    fun before() {
        url = mockk(relaxed = true)
        urlConnection = mockk(relaxed = true)
        dataSource = CompassRemoteDataSource(url, UnconfinedTestDispatcher())

        every { url.openConnection() } returns urlConnection
    }

    @Test
    fun getHTML() {
        runTest {
            every { urlConnection.inputStream } returns ByteArrayInputStream("html".toByteArray())

            val html = dataSource.getHTML()

            assertEquals("html", html)
        }
    }
}
package com.btello.compass

import android.app.Application
import androidx.room.Room.databaseBuilder
import com.btello.compass.data.sources.local.db.CompassDatabase

class CompassApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        createDatabase()
    }

    private fun createDatabase() {
        db = databaseBuilder(
            applicationContext,
            CompassDatabase::class.java,
            "compass-db"
        )
            .build()
    }

    companion object {
        lateinit var db: CompassDatabase
    }
}
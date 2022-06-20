package com.example.soccerapp.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.soccerapp.data.datamodels.Soccer

@Database(entities = [Soccer::class], version = 1)
abstract class SoccerDatabase : RoomDatabase() {

    abstract val soccerDatabaseDao: SoccerDatabaseDao
}

private lateinit var INSTANCE: SoccerDatabase

// wenn es keine Database gibt, wird eine neue gebaut
fun getDatabase(context: Context): SoccerDatabase {
    synchronized(SoccerDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                SoccerDatabase::class.java,
                "soccer_database"
            )
                .build()
        }
    }
    return INSTANCE
}

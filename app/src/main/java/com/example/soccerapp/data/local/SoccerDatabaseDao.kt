package com.example.soccerapp.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.soccerapp.data.datamodels.Soccer

@Dao
interface SoccerDatabaseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(drinks: List<Soccer>)

    @Query("SELECT * from Soccer")
    fun getAll(): List<Soccer>

    @Query("DELETE from Soccer")
    fun deleteAll()


}

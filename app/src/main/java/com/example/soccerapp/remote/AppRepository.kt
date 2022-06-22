package com.example.soccerapp.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.soccerapp.data.datamodels.Soccer
import com.example.soccerapp.data.local.SoccerDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppRepository(private val api: SoccerApi, private val database: SoccerDatabase) {

    private val _soccerList = MutableLiveData<List<Soccer>>()
    val soccerList: LiveData<List<Soccer>>
        get() = _soccerList

    suspend fun getSoccers() {
        withContext(Dispatchers.IO) {
            val newClubs = api.retrofitService.getClubs()
            Log.e("---", newClubs.toString())
            database.soccerDatabaseDao.insertAll(newClubs)
            _soccerList.postValue(newClubs)
        }
    }

    suspend fun getSoccersfromDatabase() {
        withContext(Dispatchers.IO) {
            val newClubs = database.soccerDatabaseDao.getAll()
            _soccerList.postValue(newClubs)
        }
    }

    fun sortResultsByAbc() {
        if (_soccerList.value != null) {
            _soccerList.value = _soccerList.value?.sortedBy { it.name }
        }
    }

    fun sortResultsByValue() {
        if (_soccerList.value != null) {
            _soccerList.value = _soccerList.value?.sortedByDescending { it.value }
        }
    }
}

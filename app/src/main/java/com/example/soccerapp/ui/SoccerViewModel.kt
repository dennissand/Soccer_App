package com.example.soccerapp.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.soccerapp.data.local.getDatabase
import com.example.soccerapp.remote.AppRepository
import com.example.soccerapp.remote.SoccerApi
import kotlinx.coroutines.launch

const val TAG = "Soccerviewmodel"

enum class ApiStatus { LOADING, ERROR, DONE }

class SoccerViewModel(application: Application) : AndroidViewModel(application) {

    private val database = getDatabase(application)
    private val repository = AppRepository(SoccerApi, database)

    private val _loading = MutableLiveData<ApiStatus>()

    val clubs = repository.soccerList

    private val _isSortByAbc = MutableLiveData(true)
    val isSortByAbc: LiveData<Boolean>
        get() = _isSortByAbc

    init {
        loadClubs()
    }

    fun loadClubs() {
        viewModelScope.launch {
            _loading.value = ApiStatus.LOADING
            try {
                repository.getSoccers()
                _loading.value = ApiStatus.DONE
            } catch (e: Exception) {
                Log.e(TAG, "Error loading Clubs: $e")
                repository.getSoccersfromDatabase()
                if (clubs.value.isNullOrEmpty()) {
                    _loading.value = ApiStatus.ERROR
                } else {
                    _loading.value = ApiStatus.DONE
                }
            }
        }
    }

    fun toggleSort() {
        _isSortByAbc.run { value = value != true }
    }

    fun sortByAbc() {
        repository.sortResultsByAbc()
    }

    fun sortByValue() {
        repository.sortResultsByValue()
    }
}

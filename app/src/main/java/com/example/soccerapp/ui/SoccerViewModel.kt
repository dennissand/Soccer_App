package com.example.soccerapp.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soccerapp.data.AppRepository
import com.example.soccerapp.data.TAG
import com.example.soccerapp.remote.SoccerApi
import kotlinx.coroutines.launch

class SoccerViewModel : ViewModel() {

    private val repository = AppRepository(SoccerApi)

    val clubs = repository.results

    private val _isSortByAbc = MutableLiveData(true)
    val isSortByAbc: LiveData<Boolean>
        get() = _isSortByAbc

    init {
        loadClubs()
    }

    fun loadClubs() {
        viewModelScope.launch {
            try {
                repository.getResults()
            } catch (e: Exception) {
                Log.e(TAG, "Error loading Clubs: $e")
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

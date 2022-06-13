package com.example.soccerapp.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.soccerapp.data.AppRepository
import com.example.soccerapp.remote.SoccerApi
import kotlinx.coroutines.launch

class SoccerViewModel : ViewModel() {

    private val repository = AppRepository(SoccerApi)

    val clubs = repository.results

    fun loadClubs() {
        viewModelScope.launch {
            Log.e("---", "viewmodel")
            repository.getResults()
        }
    }
}

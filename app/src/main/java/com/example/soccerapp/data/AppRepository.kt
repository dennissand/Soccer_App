package com.example.soccerapp.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.soccerapp.data.datamodels.Result
import com.example.soccerapp.remote.SoccerApi

const val TAG = "AppRepository TAG"

class AppRepository(private val api: SoccerApi) {

    private val _results = MutableLiveData<List<Result>>()
    val results: LiveData<List<Result>>
        get() = _results

    suspend fun getResults() {
        Log.e("---", "Repo get Results")
        try {
            val soccerList = api.retrofitService.getClubs()
            _results.value = soccerList

            Log.d("---", soccerList.toString())
        } catch (e: Exception) {
            Log.e(TAG, "Error loading Soccer Daten from API: $e")
        }
    }
}

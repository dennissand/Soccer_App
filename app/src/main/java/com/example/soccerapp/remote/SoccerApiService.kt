package com.example.soccerapp.remote

import com.example.soccerapp.data.datamodels.Soccer
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://public.allaboutapps.at/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface SoccerApiService {

    @GET("hiring/clubs.json")
    suspend fun getClubs(): List<Soccer>
}

object SoccerApi {
    val retrofitService: SoccerApiService by lazy { retrofit.create(SoccerApiService::class.java) }
}

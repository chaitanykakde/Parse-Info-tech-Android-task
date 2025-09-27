package com.chaitany.chaitanystask.network

import com.chaitany.chaitanystask.data.Course
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("68d7bd1bae596e708ffd9f01/latest")
    suspend fun getCourses(): Response<JsonBinResponse>
}

data class JsonBinResponse(
    val record: List<Course>
)

package com.example.anthenaguard

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("api/sos")
    fun sendSOSAlert(@Body requestBody: SOSRequestBody): Call<Void>
}
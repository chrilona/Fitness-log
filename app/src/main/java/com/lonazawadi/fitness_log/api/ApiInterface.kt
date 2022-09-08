package com.lonazawadi.fitness_log.api

import com.lonazawadi.fitness_log.models.RegisterRequest
import com.lonazawadi.fitness_log.models.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiInterface {
    @POST("/register")
    fun registerUser(@Body registerRequest: RegisterRequest):Call<RegisterResponse>
}
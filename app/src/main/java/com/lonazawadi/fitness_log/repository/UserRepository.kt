package com.lonazawadi.fitness_log.repository

import com.lonazawadi.fitness_log.api.ApiClient
import com.lonazawadi.fitness_log.api.ApiInterface
import com.lonazawadi.fitness_log.models.LoginRequest
import com.lonazawadi.fitness_log.models.LoginResponse
import com.lonazawadi.fitness_log.models.RegisterRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class UserRepository {
    val apiClient=ApiClient.buildApiClient(ApiInterface::class.java)
    suspend fun loginUser(loginRequest: LoginRequest):Response<LoginResponse>
    = withContext(Dispatchers.IO){
      val response =apiClient.loginUser(loginRequest)
      return@withContext response
    }
    suspend fun registerUser(RegisterRequest:RegisterRequest)
    = withContext(Dispatchers.IO){
        val response=apiClient.registerUser(RegisterRequest)
        return@withContext
    }

}
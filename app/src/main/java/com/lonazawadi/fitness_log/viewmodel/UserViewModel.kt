package com.lonazawadi.fitness_log.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.lonazawadi.fitness_log.models.LoginRequest
import com.lonazawadi.fitness_log.models.LoginResponse
import com.lonazawadi.fitness_log.models.RegisterRequest
import com.lonazawadi.fitness_log.models.RegisterResponse
import com.lonazawadi.fitness_log.repository.UserRepository
import kotlinx.coroutines.launch

class UserViewModel:ViewModel() {
    val userRepository= UserRepository()
    val loginResponseLiveData = MutableLiveData<LoginResponse>()
    val errorLiveData = MutableLiveData<String>()
    val registerResponseLiveData=MutableLiveData<RegisterResponse>()
    val registerErrorLiveData=MutableLiveData<String>()

    fun loginUser(loginRequest: LoginRequest){
        viewModelScope.launch {
            val response = userRepository.loginUser(loginRequest)
            if (response.isSuccessful){
                loginResponseLiveData.postValue(response.body())
            }
            else{
                errorLiveData.postValue(response.errorBody()?.string())
            }
        }
    }

 fun registeruser(registerRequest: RegisterRequest){
     viewModelScope.launch {
         val response= userRepository.registerUser(registerRequest)
         if (response.isSucceesful()){
             registerResponseLiveData.postValue(response.body())
             }
         else{
             val error =response.errorbody()?.string()
             registerErrorLiveData.postValue(error)
         }
     }

 }

}
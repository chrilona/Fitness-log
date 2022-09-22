package com.lonazawadi.fitness_log.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.lonazawadi.fitness_log.R
import com.lonazawadi.fitness_log.api.ApiClient
import com.lonazawadi.fitness_log.api.ApiInterface
import com.lonazawadi.fitness_log.databinding.ActivityLoginBinding
import com.lonazawadi.fitness_log.models.LoginRequest
import com.lonazawadi.fitness_log.models.LoginResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class loginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var sharedPrefs:SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        castViews()
        sharedPrefs=getSharedPreferences("FITNESS-LOG_PREFS", MODE_PRIVATE)
     }
    fun castViews(){
        binding.btnLogin.setOnClickListener { validateLogin()
        }
        binding.tvsignup.setOnClickListener {
            val intent = Intent(this, signUpActivity::class.java)
            startActivity(intent)
        }
      }
    fun validateLogin(){
        var error=false
        binding.tilEmail.error=null
        binding.tilPassword.error=null
        var email=binding.etEmailSign.text.toString()

        if (email.isBlank()){
            binding.tilEmail.error="Email is required"
            error=true
        }
        var password=binding.etPassword.text.toString()
        if (password.isBlank()){
            binding.tilPassword.error="Password is required"
            error =true
        }
        if (!error){
          val loginRequest=LoginRequest(email, password)
            binding.pbLogin.visibility=View.VISIBLE
            makeLoginRequest(loginRequest)
        }
    }
    fun makeLoginRequest(loginRequest: LoginRequest){
        val apiClient=ApiClient.buildApiClient(ApiInterface::class.java)
        val request = apiClient.loginUser(loginRequest)

        request.enqueue(object :Callback<LoginResponse>{
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>)
                 {
                     binding.pbLogin.visibility=View.GONE
                if (response.isSuccessful){
                    val loginResponse=response.body()
                    Toast.makeText(baseContext,loginResponse?.message,Toast.LENGTH_LONG).show()
                    saveLoginDetails(loginResponse!!)
                    startActivity(Intent(baseContext,HomeActivity::class.java))
                }
                else{
                    val error=response.errorBody()?.string()
                    Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
                }
            }
            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                binding.pbLogin.visibility=View.GONE
                Toast.makeText(baseContext,t.message,Toast.LENGTH_LONG).show()
            }
        }
        )
    }
    fun saveLoginDetails(loginResponse: LoginResponse){
        val editor=sharedPrefs.edit()
        editor.putString("ACCESS_TOKEN",loginResponse.accessToken)
        editor.putString("USER_ID",loginResponse.userId)
        editor.putString("PROFILE_ID",loginResponse.profileId)
        editor.apply()

    }
}
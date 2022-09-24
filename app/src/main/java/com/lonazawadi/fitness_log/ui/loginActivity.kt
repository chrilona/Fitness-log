package com.lonazawadi.fitness_log.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.lonazawadi.fitness_log.R
import com.lonazawadi.fitness_log.api.ApiClient
import com.lonazawadi.fitness_log.api.ApiInterface
import com.lonazawadi.fitness_log.databinding.ActivityLoginBinding
import com.lonazawadi.fitness_log.models.LoginRequest
import com.lonazawadi.fitness_log.models.LoginResponse
import com.lonazawadi.fitness_log.viewmodel.UserViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class loginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var sharedPrefs:SharedPreferences
    val userViewModel:UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_login)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        castViews()
        sharedPrefs=getSharedPreferences("FITNESS-LOG_PREFS", MODE_PRIVATE)
     }

    override fun onResume() {
        super.onResume()
        userViewModel.loginResponseLiveData.observe(this, Observer { loginResponse->
            Toast.makeText(baseContext,loginResponse?.message,Toast.LENGTH_LONG).show()
            saveLoginDetails(loginResponse!!)
            startActivity(Intent(baseContext,HomeActivity::class.java))
        })
        userViewModel.errorLiveData.observe(this, Observer { errorMessage->
            Toast.makeText(this,errorMessage,Toast.LENGTH_LONG).show()
        })
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
            userViewModel.loginUser(loginRequest)
        }
    }

    fun saveLoginDetails(loginResponse: LoginResponse){
        val editor=sharedPrefs.edit()
        editor.putString("ACCESS_TOKEN",loginResponse.accessToken)
        editor.putString("USER_ID",loginResponse.userId)
        editor.putString("PROFILE_ID",loginResponse.profileId)
        editor.apply()

    }
}
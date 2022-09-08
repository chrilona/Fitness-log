package com.lonazawadi.fitness_log.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.lonazawadi.fitness_log.R
import com.lonazawadi.fitness_log.databinding.ActivityLoginBinding

class loginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding=ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        castViews()
     }
    fun castViews(){
        binding.btnLogin.setOnClickListener { validateLogin()
        startActivity(Intent(this, HomeActivity::class.java))
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
          startActivity(Intent(this, HomeActivity::class.java))
        }
    }
}
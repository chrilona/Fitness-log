package com.lonazawadi.fitness_log

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class loginActivity : AppCompatActivity() {

    lateinit var btnlogin:Button
    lateinit var tilEmail:TextInputLayout
    lateinit var etEmail:TextInputEditText
    lateinit var tilPassword:TextInputLayout
    lateinit var etpassword:TextInputEditText
    lateinit var tvsignup:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        btnlogin=findViewById(R.id.btnLogin)
        tilEmail=findViewById(R.id.tilEmail)
        etEmail=findViewById(R.id.etEmailSign)
        tilPassword=findViewById(R.id.tilPassword)
        btnlogin=findViewById(R.id.btnLogin)
        tvsignup = findViewById(R.id.tvsignup)
        tvsignup =findViewById(R.id.tvsignup)
        etpassword=findViewById(R.id.etPassword)

        btnlogin.setOnClickListener { validateLogin()
        startActivity(Intent(this,HomeActivity::class.java))
        }
        tvsignup.setOnClickListener {
            val intent = Intent(this,signUpActivity::class.java)
            startActivity(intent)
        }
    }


    fun validateLogin(){
        var error=false
        tilEmail.error=null
        tilPassword.error=null
        var email=etEmail.text.toString()
        if (email.isBlank()){
            tilEmail.error="Email is required"
            error=true
        }
        var password=etpassword.text.toString()
        if (password.isBlank()){
            tilPassword.error="Password is required"
            error =true
        }
        if (!error){

        }
    }
}
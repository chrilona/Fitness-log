package com.lonazawadi.fitness_log

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.lonazawadi.fitness_log.databinding.ActivityLoginBinding
import com.lonazawadi.fitness_log.databinding.ActivitySignUpBinding

class signUpActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        binding= ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        castView()
    }
    fun castView(){
        binding.btnSignUp.setOnClickListener { validateSignup() }
        binding.tvSignUp.setOnClickListener {
            val intent = Intent(this,loginActivity::class.java)
            startActivity(intent)
        }
    }
    fun validateSignup(){
        var error=false
        binding.tilFirstname.error=null
        binding.tilSecondName.error=null
        binding.tilEmailSignUp.error=null
        binding.tilPasswordSignup.error=null
        binding.tilConfirmSignup.error=null
        var firstNameS=binding.etFirstname.text.toString()
        if (firstNameS.isBlank()){
            binding.tilFirstname.error="Password is required"
            error =true
        }
        var secondNameS=binding.etSecondName.text.toString()
        if (secondNameS.isBlank()){
            binding.tilSecondName.error="Password is required"
            error =true
        }
        var emailS=binding.etEmailSign.text.toString()
        if (emailS.isBlank()){
            binding.tilEmailSignUp.error="Email is required"
            error=true
        }
        //validating input in the email field matches the pattern Regular expressions
        if (Patterns.EMAIL_ADDRESS.matcher(emailS).matches()){
            binding.tilEmailSignUp.error = "Invalid"
            error=true
        }
        var passwordS=binding.etSignPassword.text.toString()
        if (passwordS.isBlank()){
            binding.tilPasswordSignup.error="Password is required"
            error =true
        }
        var confirmS=binding.etConfirm.text.toString()
        if (confirmS.isBlank()){
            binding.tilConfirmSignup.error="Password is required"
            error =true
        }
        //comparing if passwords passed in both edit texts match.
        if (passwordS!=confirmS){
            binding.tilConfirmSignup.error="Passwords do not match"

        }
        if (!error){
        }
    }
}
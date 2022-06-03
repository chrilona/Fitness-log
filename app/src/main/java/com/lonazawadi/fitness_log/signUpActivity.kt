package com.lonazawadi.fitness_log

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class signUpActivity : AppCompatActivity() {
    lateinit var etFirstname: TextInputEditText
    lateinit var etSecondName: TextInputEditText
    lateinit var etEmailSign:TextInputEditText
    lateinit var etSignPassword:TextInputEditText
    lateinit var etConfirm: TextInputEditText
    lateinit var btnSignUp:Button
    lateinit var tvSignUp:TextView
    lateinit var tilEmail: TextInputLayout
    lateinit var tilFirstname: TextInputLayout
    lateinit var tilSecondName: TextInputLayout
    lateinit var tilEmailSignup:TextInputLayout
    lateinit var tilPasswordSignup:TextInputLayout
    lateinit var tilConfirmSignup:TextInputLayout


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        etFirstname=findViewById(R.id.etFirstname)
        etSecondName=findViewById(R.id.etSecondName)
        etEmailSign=findViewById(R.id.etEmailSign)
        etSignPassword=findViewById(R.id.etSignPassword)
        etConfirm=findViewById(R.id.etConfirm)
        btnSignUp=findViewById(R.id.btnSignUp)
        tvSignUp=findViewById(R.id.tvSignUp)
        tilFirstname=findViewById(R.id.tilFirstname)
        tilSecondName=findViewById(R.id.tilSecondName)
        tilEmailSignup=findViewById(R.id.tilEmailSignUp)
        tilPasswordSignup=findViewById(R.id.tilPasswordSignup)
        tilConfirmSignup=findViewById(R.id.tilConfirmSignup)

        btnSignUp.setOnClickListener { validateSignup() }
        tvSignUp.setOnClickListener {
            val intent = Intent(this,loginActivity::class.java)
            startActivity(intent)
        }
    }

    fun validateSignup(){
        var error=false
        tilFirstname.error=null
        tilSecondName.error=null
        tilEmailSignup.error=null
        tilPasswordSignup.error=null
        tilConfirmSignup.error=null


        var firstNameS=etFirstname.text.toString()
        if (firstNameS.isBlank()){
            tilFirstname.error="Password is required"
            error =true
        }
        var secondNameS=etSecondName.text.toString()
        if (secondNameS.isBlank()){
            tilSecondName.error="Password is required"
            error =true
        }
        var emailS=etEmailSign.text.toString()
        if (emailS.isBlank()){
            tilEmail.error="Email is required"
            error=true
        }
        var passwordS=etSignPassword.text.toString()
        if (passwordS.isBlank()){
            tilPasswordSignup.error="Password is required"
            error =true
        }
        var confirmS=etConfirm.text.toString()
        if (confirmS.isBlank()){
            tilConfirmSignup.error="Password is required"
            error =true
        }

        if (!error){

        }
    }


}
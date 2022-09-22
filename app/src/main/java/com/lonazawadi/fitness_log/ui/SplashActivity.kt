package com.lonazawadi.fitness_log.ui
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashActivity : AppCompatActivity() {
    lateinit var sharedPrefs: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPrefs = getSharedPreferences("FITNESS-LOG_PREFS", MODE_PRIVATE)
        val accessToken =sharedPrefs.getString("ACCESS_TOKEN","")
        if (accessToken!!.isBlank()){
            startActivity(Intent(this, loginActivity::class.java))
        }
        else{
            startActivity(Intent(this, HomeActivity::class.java))
        }

        
    }
}
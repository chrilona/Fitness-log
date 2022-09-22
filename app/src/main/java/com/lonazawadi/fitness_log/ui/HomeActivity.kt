package com.lonazawadi.fitness_log.ui

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.lonazawadi.fitness_log.R
import com.lonazawadi.fitness_log.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
     lateinit var binding:ActivityHomeBinding
     lateinit var sharedPrefs: SharedPreferences
     lateinit var tvLogsOut:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        binding=ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tvLogsOut=findViewById(R.id.tvLogOutHome)
        tvLogsOut.setOnClickListener{
         val editor=sharedPrefs.edit()
            editor.putString("ACCESS_TOKEN","")
            editor.putString("USER_ID","")
            editor.putString("PROFILE_ID","")
            editor.apply()
            startActivity(Intent(this,loginActivity::class.java))
            logOutRequest()

        }
        setupBottomNavs()
        castViews()
    }
    fun castViews(){
        binding.fgcPlan
        binding.bottomNavigation
    }
    fun setupBottomNavs(){
        binding.bottomNavigation.setOnItemSelectedListener{ item ->
            when (item.itemId) {
                R.id.Plan -> {
                    supportFragmentManager.beginTransaction() .replace(R.id.fgcPlan, PlanFragment()).commit()
                    true

                }
                R.id.Track -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fgcPlan, TrackFragment()).commit()
                    true
                }
                R.id.Profile -> {
                    supportFragmentManager.beginTransaction().replace(R.id.fgcPlan, ProfileFragment()).commit()
                    true
                }
                else->false
            }
        }
    }
    fun logOutRequest(){
        sharedPrefs.edit().clear().commit()
    }
}
//fragments a reusable section/portion in the user interface.It is completely independent of the activity
//example is a bottom navigation view on you tube and the lowest part of the screen
//A fragment container view is the container that holds content
// of different fragments has an attribute that can show fragment to display replace is the function call to
//destroy and replace fragments
// A fragments have their activities
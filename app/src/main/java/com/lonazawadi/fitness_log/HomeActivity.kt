package com.lonazawadi.fitness_log

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentContainerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lonazawadi.fitness_log.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    binding= ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupBottomNavigation()
    }
    fun setupBottomNavigation(){
       binding.bottomNavigation.setOnItemSelectedListener { item->
           when(item.itemId){
               R.id.Plan->{
                   supportFragmentManager.beginTransaction().replace(R.id.fgcPlan,PlanFragment()).commit()
                   true
               }
               R.id.Track->{
                   val transaction=supportFragmentManager.beginTransaction()
                   transaction.replace(R.id.fgcPlan,TrackFragment())
                   transaction.commit()
                   true
               }
               R.id.Profile->{
                   val transaction=supportFragmentManager.beginTransaction()
                   transaction.replace(R.id.fgcPlan,ProfileFragment())
                   transaction.commit()
                   true
               }
               else->false
           }
       }
    }
}

//fragments a reusable section/portion in the user interface.It is completely independent of the activity
//example is a bottom navigation view on you tube and the lowest part of the screen
//A fragment container view is the container that holds content
// of different fragments has an attribute that can show fragment to display replace is the function call to
//destroy and replace fragments
// A fragments have their activities
//fragment managers enables switch through transactions as is in activities with intents
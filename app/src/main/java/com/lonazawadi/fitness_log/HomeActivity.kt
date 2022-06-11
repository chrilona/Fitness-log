package com.lonazawadi.fitness_log

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}
//fragments a reusable section/portion in the user interface.It is completely independent of the activity
//example is a bottom navigation view on you tube and the lowest part of the screen
//A fragment container view is the container that holds content
// of different fragments has an attribute that can show fragment to display replace is the function call to
//destroy and replace fragments
// A fragments have their activities
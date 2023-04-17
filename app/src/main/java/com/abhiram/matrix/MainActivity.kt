package com.abhiram.matrix

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        this.window.statusBarColor = resources.getColor(R.color.dark)
        this.window.navigationBarColor = resources.getColor(R.color.dark)
    }
}
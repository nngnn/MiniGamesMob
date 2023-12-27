package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ProgressBar

class LoadActivity : AppCompatActivity() {
    private val SPLASH_TIME_OUT: Long = 4000
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Base_Theme_MyApplication)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        Handler().postDelayed({
            val intent: Intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
            finish()
        }, SPLASH_TIME_OUT)
    }
}
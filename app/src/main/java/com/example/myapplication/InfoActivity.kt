package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

private lateinit var button: Button

class InfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        button = findViewById(R.id.button4)
        button.setOnClickListener {
            val intent: Intent = Intent(this@InfoActivity,MenuActivity::class.java)
            startActivity(intent)
        }
    }
}
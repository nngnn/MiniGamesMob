package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val click:Button = findViewById(R.id.button)
        val  setings:Button = findViewById(R.id.button2)
        val  push:Button = findViewById(R.id.button3)
        val  findAMatch:Button = findViewById(R.id.button6)
        val  number:Button = findViewById(R.id.button7)
        setings.setOnClickListener {
            val intent: Intent = Intent(this@MenuActivity,SetingsActivity::class.java)
            startActivity(intent)
        }
        click.setOnClickListener {
            val intent: Intent = Intent(this@MenuActivity,InfoActivity::class.java)
            startActivity(intent)
        }
        push.setOnClickListener {
            val intent: Intent = Intent(this@MenuActivity,MainActivity5::class.java)
            startActivity(intent)
        }
        findAMatch.setOnClickListener {
            val intent: Intent = Intent(this@MenuActivity,CupActivity::class.java)
            startActivity(intent)
        }
        number.setOnClickListener {
            val intent: Intent = Intent(this@MenuActivity,NumActivity::class.java)
            startActivity(intent)
        }
    }
}
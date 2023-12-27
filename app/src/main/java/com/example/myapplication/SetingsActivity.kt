package com.example.myapplication


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
private lateinit var button: Button


class SetingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)
        button = findViewById(R.id.button5)
        button.setOnClickListener{
            val intent: Intent = Intent(this@SetingsActivity,MenuActivity::class.java)
            startActivity(intent)

        }
    }
    fun Theme(view: View) {

        val sw: Switch = findViewById(R.id.switch1)
        if (sw.isChecked == false) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        }

        else if (sw.isChecked == true){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}

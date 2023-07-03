package com.hello.world

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView

class LaunchActivity : AppCompatActivity() {

    var counter = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launch)

        Log.d("MasterLogs", "onCreate Called")

        val clickMeTxt = findViewById<TextView>(R.id.launch_sample_text)
        clickMeTxt.setOnClickListener {
            startActivity(Intent(this, LayoutExampleActivity::class.java))
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d("MasterLogs", "onStart Called ")
    }

    override fun onResume() {
        super.onResume()
        Log.d("MasterLogs", "onResume Called")
    }

    override fun onStop() {
        super.onStop()
        Log.d("MasterLogs", "onStop Called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("MasterLogs", "onDestroy Called")
    }

    override fun onPause() {
        super.onPause()
        Log.d("MasterLogs", "onPause Called")
    }

    override fun onRestart() {
        super.onRestart()
        getCount()
    }

    fun getCount() {
        val counterTxt = findViewById<TextView>(R.id.launch_counter_text)
        counter++
        counterTxt.text = "Count: $counter"

        Log.d("MasterLogs", "onRestart Called")
    }
}
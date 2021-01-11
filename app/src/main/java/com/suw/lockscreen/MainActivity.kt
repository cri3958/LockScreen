package com.suw.lockscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_start.setOnClickListener {
            val intent: Intent = Intent(applicationContext,ScreenService::class.java)
            startService(intent)
        }
        btn_stop.setOnClickListener {
            val intent: Intent = Intent(applicationContext,ScreenService::class.java)
            stopService(intent)
        }
    }
}
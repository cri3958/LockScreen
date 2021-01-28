package com.suw.lockscreen.activity

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.suw.lockscreen.R

class LockScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lock_screen)

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O_MR1){
            setShowWhenLocked(true)
            setTurnScreenOn(true)
        }
    }
}
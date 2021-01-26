package com.suw.lockscreen

import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val intentCode = 1111
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q && !Settings.canDrawOverlays(this)){
            val builder = AlertDialog.Builder(this).apply {
                title = "권한요청"
                setMessage("다른 화면 위에 그리기라는 권한이 필요합니다.\n수락해주실거죠?")
                setCancelable(false)
                setNegativeButton("취소"){
                    dialogInterface, i ->
                    dialogInterface.dismiss()
                }
                    .setPositiveButton("수락"){dialogInterface, i ->
                        val intent = Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:$packageName"))
                        startActivityForResult(intent,intentCode)
                        dialogInterface.dismiss()
                    }
            }
            builder.show()
        }else{

        }





        startForegroundService(Intent(applicationContext,ScreenService::class.java))

        btn_start.setOnClickListener {
            val intent: Intent = Intent(applicationContext,ScreenService::class.java)
            startForegroundService(intent)
        }
        btn_stop.setOnClickListener {
            val intent: Intent = Intent(applicationContext,ScreenService::class.java)
            stopService(intent)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(resultCode == this.intentCode){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                if(!Settings.canDrawOverlays(this)){
                    Toast.makeText(this,"권한을 수락해주세요.",Toast.LENGTH_SHORT).show()
                }else{

                }
            }
        }
    }
}
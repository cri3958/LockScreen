package com.suw.lockscreen.Login

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Process
import android.provider.Settings
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.Volley
import com.suw.lockscreen.R
import com.suw.lockscreen.activity.MainActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_btn_login.setOnClickListener {
            if(CheckInternetState()) {
                val id = login_text_id.text.toString().trim()
                val pw = login_text_pw.text.toString().trim()

                val responseListener = Response.Listener<String>(){response->
                        fun onResponse(response: String) {
                            try {
                                val jsonObject = JSONObject(response)
                                val success = jsonObject.getBoolean("success")
                                if (success) {
                                    val userID = jsonObject.getString("userID")
                                    val userPW = jsonObject.getString("userPW")
                                    Log.d("@@@@@", "로그인 성공$userID/$userPW")
                                    addPhoneid(userID)
                                } else {
                                    Toast.makeText(applicationContext, "로그인 실패", Toast.LENGTH_SHORT).show()
                                }
                            } catch (e: JSONException) {
                                e.printStackTrace()
                            }
                        }
                    onResponse(response)
                }
                val loginRequest: LoginRequest = LoginRequest(id, pw, responseListener)
                val queue: RequestQueue = Volley.newRequestQueue(this)
                queue.add(loginRequest)
            }
        }

        login_btn_main.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
    }
    private fun CheckInternetState(): Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        var check = true
        if (!(networkInfo != null && networkInfo.isConnectedOrConnecting)) {
            check = false
            val dialog = AlertDialog.Builder(this@LoginActivity)
            dialog.setTitle("인터넷을 연결해주세요.")
                .setPositiveButton("확인") { dialogInterface, i ->
                    try {
                        Thread.sleep(5)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
                .setNegativeButton("종료") { dialogInterface, i ->
                    moveTaskToBack(true)
                    finishAndRemoveTask()
                    Process.killProcess(Process.myPid())
                }
                .show()
        }
        return check
    }
    fun addPhoneid(userID:String){
        val Phoneid = Settings.Secure.getString(this.contentResolver,Settings.Secure.ANDROID_ID)
        val responseListener = Response.Listener<String>(){response->
            fun onResponse(response: String) {
                try {
                    val jsonObject = JSONObject(response)
                    val success = jsonObject.getBoolean("success")
                    if (success) {
                        Toast.makeText(applicationContext,"Phoneid 등록 성공 : "+Phoneid,Toast.LENGTH_SHORT).show()
                        Log.d("Phoneid","Phoneid 등록 성공 : "+Phoneid)
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(applicationContext, "Phoneid 등록 실패", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                }
            }
            onResponse(response)
        }
        val AddphoneidRequest = AddPhoneidRequest(userID, Phoneid, responseListener)
        val queue: RequestQueue = Volley.newRequestQueue(this)
        queue.add(AddphoneidRequest)
    }
}
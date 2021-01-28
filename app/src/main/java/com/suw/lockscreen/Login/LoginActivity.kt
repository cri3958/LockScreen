package com.suw.lockscreen.Login

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Process
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

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        login_btn_login.setOnClickListener {
            if(CheckInternetState()) {
                val id = login_text_id.text.toString().trim()
                val pw = login_text_pw.text.toString().trim()

                val responseListener = Response.Listener<String> {response ->
                    Log.d("response","really?")


                        try {//이새끼가 문제에요!!!!!
                            Log.d("@@@@@@","?????????????????")
                            val jsonObject = JSONObject(response)
                            val success = jsonObject.getBoolean("success")
                            if (success) {
                                val userID = jsonObject.getString("userID")
                                val userPW = jsonObject.getString("userPW")
                                Log.d("@@@@@", "로그인 성공$userID/$userPW")
                                val intent = Intent(this, MainActivity::class.java)
                                startActivity(intent)
                                //핸드폰에 저장해서 다음에 실행하면 바로 메인으로 가도록하기
                                finish()
                            } else {
                                Toast.makeText(applicationContext, "로그인 실패", Toast.LENGTH_SHORT).show()
                            }
                        } catch (e: JSONException) {
                            Log.d("@@@","??")
                            e.printStackTrace()
                        }

                }

                val loginRequest: LoginRequest = LoginRequest(id, pw, responseListener)
                val queue: RequestQueue = Volley.newRequestQueue(this)
                queue.add(loginRequest)
            }
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
}
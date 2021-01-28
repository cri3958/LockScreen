package com.suw.lockscreen.Login

import android.util.Log
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import java.util.*

class LoginRequest(userID: String, userPW: String, listener: Response.Listener<String>) : StringRequest(Method.POST, URL, listener, null)
{
    private val map: MutableMap<String, String>

    @Throws(AuthFailureError::class)
    override fun getParams(): Map<String, String> {
        return map
    }

    companion object {
        private const val URL = "http://http://leehojin0207.dothome.co.kr/LockScreen_Login.php"
    }

    init {
        map = HashMap()
        map["userID"] = userID
        map["userPW"] = userPW
    }
}
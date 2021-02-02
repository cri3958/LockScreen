package com.suw.lockscreen.Login

import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import java.util.HashMap

class AddPhoneidRequest(userID:String, userPhoneid:String, listener: Response.Listener<String>) : StringRequest(
    Method.POST, URL, listener, null)
{
    private val map: MutableMap<String, String>

    @Throws(AuthFailureError::class)
    override fun getParams(): Map<String, String> {
        return map
    }

    companion object {
        private const val URL = "http://leehojin0207.dothome.co.kr/LockScreen_AddPhoneid.php"
    }

    init {
        map = HashMap()
        map["userID"] = userID
        map["userPhoneid"] = userPhoneid
    }
}
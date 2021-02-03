package com.suw.lockscreen.memo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import com.suw.lockscreen.R
import com.suw.lockscreen.R.color.memo_background_btn_addmemo
import com.suw.lockscreen.R.color.memo_background_itemadd2
import kotlinx.android.synthetic.main.activity_memo_item_add.*

class MemoItemAddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memo_item_add)

        addmemo_text.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                if(addmemo_text.text.isEmpty()){
                    addmemo_btn_save.setBackgroundColor(resources.getColor(memo_background_itemadd2))
                }else{
                    addmemo_btn_save.setBackgroundColor(resources.getColor(memo_background_btn_addmemo))
                }
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                addmemo_btn_save.setBackgroundColor(resources.getColor(memo_background_itemadd2))
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(addmemo_text.text.isEmpty()){
                    addmemo_btn_save.setBackgroundColor(resources.getColor(memo_background_itemadd2))
                }else{
                    addmemo_btn_save.setBackgroundColor(resources.getColor(memo_background_btn_addmemo))
                }
            }
        })

        addmemo_background.setOnTouchListener{ _: View, event: MotionEvent ->true}

        addmemo_btn_save.setOnClickListener {
            val text = addmemo_text.text.trim()
            //잘 저장하기
        }

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }


    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(applicationContext,"저장을 취소합니다.",Toast.LENGTH_SHORT).show()
        finish()
    }
}
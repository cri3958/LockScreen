package com.suw.lockscreen.memo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.suw.lockscreen.R
import kotlinx.android.synthetic.main.activity_memo.*


class MemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memo)


        val db = DBHelper(this)
        val data:ArrayList<memo> = db.getMEMOLIST()

        var adapter = MemoAdapter()
        adapter.listData = data

        memo_recyclerView.adapter = adapter
        memo_recyclerView.layoutManager = LinearLayoutManager(this)

        memo_btn_addmemo.setOnClickListener{
            startActivity(Intent(this,MemoItemAddActivity::class.java))
            finish()
        }
    }

}
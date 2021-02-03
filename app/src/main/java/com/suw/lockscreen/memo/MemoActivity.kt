package com.suw.lockscreen.memo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.suw.lockscreen.R
import kotlinx.android.synthetic.main.activity_memo.*

class MemoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memo)

        val data:MutableList<Memo> = loadData()

        var adapter = MemoAdapter()
        adapter.listData = data

        memo_recyclerView.adapter = adapter
        memo_recyclerView.layoutManager = LinearLayoutManager(this)

        memo_btn_addmemo.setOnClickListener{startActivity(Intent(this,MemoItemAddActivity::class.java))}
    }

    fun loadData():MutableList<Memo>{
        val data:MutableList<Memo> = mutableListOf()
        //여기에다가 메모 내용들 불러서 data에 집어넣어야함
        for(no in 1..10){
            var memo = Memo(no.toString())
            data.add(memo)
        }
        return data;
    }
}
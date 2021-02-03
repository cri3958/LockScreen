package com.suw.lockscreen.memo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.suw.lockscreen.R
import kotlinx.android.synthetic.main.item_recycle.view.*

class MemoAdapter : RecyclerView.Adapter<Holder>() {
    var listData = mutableListOf<Memo>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycle, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val memo = listData.get(position)
        holder.setMemo(memo)
    }
}

class Holder(itemView: View) : RecyclerView.ViewHolder(itemView){
    fun setMemo(memo:Memo){
        itemView.memo_item_text.text = memo.text
    }
}
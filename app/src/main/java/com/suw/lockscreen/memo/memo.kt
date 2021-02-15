package com.suw.lockscreen.memo

class memo {

    private var text:String? = null
    private var id = 0

    fun memo(){}

    fun memo(text:String?){
        this.text = text
    }

    fun getId():Int { return id}
    fun setId(id:Int){this.id =id}

    fun getText(): String? { return text}
    fun setText(text:String?){this.text=text}
}
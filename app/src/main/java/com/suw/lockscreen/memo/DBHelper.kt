package com.suw.lockscreen.memo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context : Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object{
        private val DB_VERSION = 1
        private val DB_NAME = "LOCKSCREEN.db"

        private val MEMOLIST = "memo"

        private val MEMO_ID = "id"
        private val MEMO_TEXT = "text"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY =
            ("CREATE TABLE " + MEMOLIST + "("
                    + MEMO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + MEMO_TEXT + " TEXT" + ")")
        db!!.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newViersion: Int) {
        when (oldVersion) {
            1 -> {
                db!!.execSQL("DROP TABLE IF EXISTS $MEMOLIST")
            }
        }
        onCreate(db)
    }

    fun insertMEMOLIST(memo: memo) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(MEMO_TEXT, memo.getText())
        db.insert(MEMOLIST, null, contentValues)
        db.close()
    }

    fun updateMEMOLIST(memo: memo) { //지운후에 업데이트하기
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(MEMO_TEXT, memo.getText())
        db.update(MEMOLIST, contentValues, MEMO_ID + " = " + memo.getId(), null)
        db.close()
    }

    fun deleteMEMOLISTById(memo: memo) { //sql지우기
        val db = this.writableDatabase
        db.delete(MEMOLIST, MEMO_ID + " = ? ", arrayOf(java.lang.String.valueOf(memo.getId())))
        db.close()
    }

    fun getMEMOLIST():ArrayList<memo>{
        val db = this.writableDatabase //readable로 바꾸어도 되는가???
        val memolist : ArrayList<memo> = ArrayList<memo>()
        var memo:memo
        val cursor = db.rawQuery("SELECT * FROM $MEMOLIST ORDER BY $MEMO_ID",null)
        while (cursor.moveToNext()){
            memo = memo()
            memo.setId(cursor.getInt(cursor.getColumnIndex(MEMO_ID)))
            memo.setText(cursor.getString(cursor.getColumnIndex(MEMO_TEXT)))
            memolist.add(memo)
        }
        cursor.close()
        db.close()

        return memolist
    }
}
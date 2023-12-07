package com.example.coffeeshop.config

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.coffeeshop.entity.CartItem
import com.example.coffeeshop.entity.Size
import java.security.MessageDigest


class DBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "CoffeeShopDBTest"
        private const val TABLE_USERS = "users"
        private const val KEY_ID = "id"
        private const val KEY_USERID = "userId"
        private const val KEY_PASSWORD = "password"
        private const val KEY_USERNAME = "userName"

        private const val KEY_PURCHASE_USERNAME = "userName"
        private const val KEY_PURCHASE_ITEMNAME = "itemName"
        private const val KEY_PURCHASE_SIZE = "size"
        private const val KEY_PURCHASE_QUANTITY = "quantity"
        private const val KEY_PURCHASE_PRICE = "price"
        private const val TABLE_PURCHASES = "purchases"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createUserTable = ("CREATE TABLE $TABLE_USERS (" +
                "$KEY_ID INTEGER PRIMARY KEY," +
                "$KEY_USERNAME TEXT," +
                "$KEY_USERID TEXT," +
                "$KEY_PASSWORD TEXT)")
        db.execSQL(createUserTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    fun addUser(userId: String, password: String, userName: String) {
        val hashedPassword = hashPassword(password)
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(KEY_USERID, userId)
        values.put(KEY_PASSWORD, hashedPassword)
        values.put(KEY_USERNAME, userName)

        db.insert(TABLE_USERS, null, values)
        db.close()
    }

    private fun hashPassword(password: String): String {
        val bytes = password.toByteArray()
        val md = MessageDigest.getInstance("SHA-256")
        val digest = md.digest(bytes)
        return digest.fold("") { str, it -> str + "%02x".format(it) }
    }

    fun userExists(userId: String): Boolean {
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_USERS WHERE $KEY_USERID = ?"
        val cursor = db.rawQuery(query, arrayOf(userId))

        val exists = cursor.count > 0
        cursor.close()

        return exists
    }

    fun authenticateUser(userId: String, password: String): Boolean {
        val hashedPassword = hashPassword(password)
        val db = this.readableDatabase
        val query = "SELECT * FROM $TABLE_USERS WHERE $KEY_USERID = ? AND $KEY_PASSWORD = ?"
        val cursor = db.rawQuery(query, arrayOf(userId, hashedPassword))

        val isAuthenticated = cursor.count > 0
        cursor.close()

        return isAuthenticated
    }

    @SuppressLint("Range")
    fun getUsernameByUserId(userId: String): String? {
        val db = this.readableDatabase
        val query = "SELECT $KEY_USERNAME FROM $TABLE_USERS WHERE $KEY_USERID = ?"
        val cursor = db.rawQuery(query, arrayOf(userId))

        var username: String? = null
        if (cursor.moveToFirst()) {
            username = cursor.getString(cursor.getColumnIndex(KEY_USERNAME))
        }
        cursor.close()
        return username
    }

    fun addPurchase(userName: String, cartItem: CartItem) {
        val db = this.writableDatabase

        val values = ContentValues().apply {
            put(KEY_PURCHASE_USERNAME, userName)
            put(KEY_PURCHASE_ITEMNAME, cartItem.name)
            put(KEY_PURCHASE_SIZE, cartItem.size.name)
            put(KEY_PURCHASE_QUANTITY, cartItem.quantity)
            put(KEY_PURCHASE_PRICE, (cartItem.price + getSizeInt(cartItem.size)) * cartItem.quantity)
        }

        db.insert(TABLE_PURCHASES, null, values)
        db.close()
    }

    private fun getSizeInt(size: Size): Int {
        return when (size) {
            Size.S -> 0
            Size.M -> 500
            Size.L -> 1000
        }
    }
}
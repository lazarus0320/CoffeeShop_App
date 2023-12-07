package com.example.coffeeshop.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.coffeeshop.MainActivity
import com.example.coffeeshop.R
import com.example.coffeeshop.config.DBHelper
import com.example.coffeeshop.entity.Order

class AdminOrderActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.admin_order_layout)

        val tableOrders: TableLayout = findViewById(R.id.tableOrders)
        val orders = getOrders()

        orders.forEach { order ->
            val row = TableRow(this)
            row.addView(TextView(this).apply { text = order.userName })
            row.addView(TextView(this).apply { text = order.itemName })
            row.addView(TextView(this).apply { text = order.size })
            row.addView(TextView(this).apply { text = order.quantity.toString() })
            row.addView(TextView(this).apply { text = "${order.price}원" })
            tableOrders.addView(row)
        }
    }
    private fun getOrders(): List<Order> {
        val dbHelper = DBHelper(this)
        return dbHelper.getAllOrders()
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
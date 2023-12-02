package com.example.coffeeshop.fragment

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.coffeeshop.R
import com.example.coffeeshop.entity.CoffeeTeaItem

class ItemDialog(context: Context, private val item: CoffeeTeaItem) : Dialog(context) {
    private lateinit var imageView: ImageView
    private lateinit var textViewName: TextView
    private lateinit var textViewPrice: TextView
    private lateinit var shutdownClick: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_item_details)

        imageView = findViewById(R.id.imageViewItemPopup)
        textViewName = findViewById(R.id.itemNamePopup)
        textViewPrice = findViewById(R.id.itemPricePopup)

        imageView.setImageResource(item.imageResource)
        textViewName.text = item.name
        textViewPrice.text = item.price.toString()

        shutdownClick = findViewById(R.id.btn_shutdown)
        shutdownClick.setOnClickListener {
            shutDownClick()
        }


    }
    private fun shutDownClick() {
        dismiss()
    }
}
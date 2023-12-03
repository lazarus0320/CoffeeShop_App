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
    private lateinit var textViewQuantity: TextView
    private lateinit var btnIncrease: Button
    private lateinit var btnDecrease: Button
    private lateinit var shutdownClick: Button

    private var quantity = 1

    companion object {
        const val DEFAULT_QUANTITY = 1
    }

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_item_details)

        initializeViews()
        setupListeners()

        updateViews()
    }

    private fun initializeViews() {
        imageView = findViewById(R.id.imageViewItemPopup)
        textViewName = findViewById(R.id.itemNamePopup)
        textViewPrice = findViewById(R.id.itemPricePopup)
        textViewQuantity = findViewById(R.id.quantityValue)
        btnIncrease = findViewById(R.id.btnIncrease)
        btnDecrease = findViewById(R.id.btnDecrease)
        shutdownClick = findViewById(R.id.btn_shutdown)
    }

    private fun setupListeners() {
        btnIncrease.setOnClickListener {
            increaseQuantity()
            updateQuantityAndPrice()
        }

        btnDecrease.setOnClickListener {
            decreaseQuantity()
            updateQuantityAndPrice()
        }

        shutdownClick.setOnClickListener {
            dismiss()
        }
    }

    private fun increaseQuantity() {
        quantity++
    }

    private fun decreaseQuantity() {
        if (quantity > DEFAULT_QUANTITY) {
            quantity--
        }
    }

    private fun updateQuantityAndPrice() {
        updateQuantityText()
        updatePriceText()
    }

    private fun updateViews() {
        imageView.setImageResource(item.imageResource)
        textViewName.text = item.name
        updateQuantityAndPrice()
    }

    private fun updateQuantityText() {
        textViewQuantity.text = quantity.toString()
    }

    @SuppressLint("SetTextI18n")
    private fun updatePriceText() {
        textViewPrice.text = "${item.price * quantity}원"
    }
}

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
import com.example.coffeeshop.entity.Size

class ItemDialog(context: Context, private val item: CoffeeTeaItem) : Dialog(context) {
    private lateinit var imageView: ImageView
    private lateinit var textViewName: TextView
    private lateinit var textViewPrice: TextView
    private lateinit var textViewQuantity: TextView
    private lateinit var btnIncrease: Button
    private lateinit var btnDecrease: Button
    private lateinit var confirmClick: Button
    private lateinit var shutdownClick: Button
    private lateinit var sizeButtonS: Button
    private lateinit var sizeButtonM: Button
    private lateinit var sizeButtonL: Button

    private var quantity = 1
    private var size = 0

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
        confirmClick = findViewById(R.id.btn_confirm)
        shutdownClick = findViewById(R.id.btn_shutdown)

        sizeButtonS = findViewById(R.id.sizeButtonS)
        sizeButtonM = findViewById(R.id.sizeButtonM)
        sizeButtonL = findViewById(R.id.sizeButtonL)
    }

    @SuppressLint("SetTextI18n")
    private fun setupListeners() {
        btnIncrease.setOnClickListener {
            increaseQuantity()
            updateQuantityAndPrice()
        }

        btnDecrease.setOnClickListener {
            decreaseQuantity()
            updateQuantityAndPrice()
        }

        sizeButtonS.setOnClickListener {
            size = 0
            item.size = Size.S
            updateQuantityAndPrice()
            updatePriceText()
            textViewName.text = item.name + item.size
        }

        sizeButtonM.setOnClickListener {
            size = 500
            item.size = Size.M
            updateQuantityAndPrice()
            updatePriceText()
            textViewName.text = item.name + item.size
        }

        sizeButtonL.setOnClickListener {
            size = 1000
            item.size = Size.L
            updateQuantityAndPrice()
            updatePriceText()
            textViewName.text = item.name + item.size
        }

        confirmClick.setOnClickListener {
            val selectedSize = when (item.size) {
                Size.S -> "S"
                Size.M -> "M"
                Size.L -> "L"
            }
            OrderManager.addOrder(item, quantity)

            dismiss()
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

    @SuppressLint("SetTextI18n")
    private fun updateViews() {
        imageView.setImageResource(item.imageResource)
        textViewName.text = item.name + item.size
        updateQuantityAndPrice()
    }

    private fun updateQuantityText() {
        textViewQuantity.text = quantity.toString()
    }

    @SuppressLint("SetTextI18n")
    private fun updatePriceText() {
        textViewPrice.text = "${(item.price + size) * quantity}원"
    }
}

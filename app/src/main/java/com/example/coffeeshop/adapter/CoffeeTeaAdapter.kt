package com.example.coffeeshop.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.coffeeshop.R
import com.example.coffeeshop.entity.CoffeeTeaItem
import com.example.coffeeshop.fragment.ItemDialog

class CoffeeTeaAdapter(private val coffeeTeaData: List<CoffeeTeaItem>) :
    RecyclerView.Adapter<CoffeeTeaAdapter.CoffeeTeaViewHolder>() {

    class CoffeeTeaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.imageViewCoffeeTea)
        val name: TextView = itemView.findViewById(R.id.textViewName)
        val price: TextView = itemView.findViewById(R.id.textViewPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoffeeTeaViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_coffee_tea, parent, false)
        return CoffeeTeaViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CoffeeTeaViewHolder, position: Int) {
        val currentItem = coffeeTeaData[position]

        holder.image.setImageResource(currentItem.imageResource)
        holder.name.text = currentItem.name
        holder.price.text = "${currentItem.price}원"

        holder.itemView.setOnClickListener {
            val context = holder.itemView.context
            val itemDialog = ItemDialog(context, currentItem)
            itemDialog.show()
        }
    }

    override fun getItemCount(): Int {
        return coffeeTeaData.size
    }
}
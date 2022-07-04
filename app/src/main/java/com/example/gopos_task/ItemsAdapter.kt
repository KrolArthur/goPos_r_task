package com.example.gopos_task

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.gopos_task.model.ob_mobel.Item
import com.squareup.picasso.Picasso


class ItemsAdapter(obItems: List<Item>) : RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {
    private val items = obItems
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsAdapter.ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_product_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemsAdapter.ViewHolder, position: Int) {
        Picasso.get().load(items[position].default_link).into(holder.image)
        holder.itemName.text = items[position].name.toString()
        holder.itemPrice.text = items[position].amount.toString() + items[position].currency
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.itemImage)
        val itemName: TextView = itemView.findViewById(R.id.itemText)
        val itemPrice: TextView = itemView.findViewById(R.id.itemPrice)
    }
}
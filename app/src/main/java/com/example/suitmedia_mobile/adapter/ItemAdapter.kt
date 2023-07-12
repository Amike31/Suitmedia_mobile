package com.example.suitmedia_mobile.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.suitmedia_mobile.R
import com.example.suitmedia_mobile.model.Item

class ItemAdapter(private val items: ArrayList<Item>) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    private lateinit var listener: onItemClickListener

    interface onItemClickListener {
        fun onItemClick(pos: Int)
    }

    class ItemViewHolder(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tv_item_name)
        val avatar: ImageView = itemView.findViewById(R.id.img_item_avatar)

        init {
            itemView.setOnClickListener {
                listener
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        return ItemViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = items[position]
        holder.name.text = currentItem.first_name + " " + currentItem.last_name
        holder.avatar.setImageResource(currentItem.id)

    }

    override fun getItemCount(): Int {
        return items.size
    }
}
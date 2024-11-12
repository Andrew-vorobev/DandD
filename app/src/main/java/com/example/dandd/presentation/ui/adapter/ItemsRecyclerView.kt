package com.example.dandd.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dandd.presentation.ui.model.ClassView
import com.example.dandd.presentation.ui.util.ItemDiffUtilCallback
import com.example.dungeonanddragonsapp.R

/**
 * @author Andrew
 */

class ItemsRecyclerView :
    ListAdapter<ClassView, ItemsRecyclerView.Itemsholder>(ItemDiffUtilCallback()) {
    private lateinit var onButtonClickListener: OnButtonClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Itemsholder {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return Itemsholder(item)
    }

    override fun onBindViewHolder(holder: Itemsholder, position: Int) {
        holder.classView.text = "Полное название: ${getItem(position).name}"

        holder.itemView.setOnClickListener {
            if (::onButtonClickListener.isInitialized) {
                onButtonClickListener.onClick(getItem(position))
            }
        }
    }

    interface OnButtonClickListener {
        fun onClick(classView: ClassView)
    }

    fun setOnButtonClickListener(listener: OnButtonClickListener) {
        onButtonClickListener = listener
    }

    class Itemsholder(classView: View) : RecyclerView.ViewHolder(classView) {
        val classView: TextView = itemView.findViewById(R.id.item_list_full_name)
    }
}
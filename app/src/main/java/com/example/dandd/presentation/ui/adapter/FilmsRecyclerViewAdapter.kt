package com.example.dandd.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.dungeonanddragonsapp.R
import com.example.dungeonanddragonsapp.presentation.ui.model.item.ItemView
import com.example.dungeonanddragonsapp.presentation.ui.model.skill.toStringBySeparator
import com.example.dungeonanddragonsapp.presentation.ui.util.ItemDiffUtilCallback

/**
 * @author Andrew
 */

class FilmsRecyclerViewAdapter :
    ListAdapter<ItemView, FilmsRecyclerViewAdapter.FilmsHolder>(ItemDiffUtilCallback()) {
    private lateinit var onButtonClickListener: OnButtonClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmsHolder {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return FilmsHolder(item)
    }

    override fun onBindViewHolder(holder: FilmsHolder, position: Int) {
        holder.itemName.text = "Полное название: ${getItem(position).fullName}"
        holder.itemSkills.text =
            "Используемые скиллы: ${getItem(position).skills.toStringBySeparator({it.name})}"

        holder.itemView.setOnClickListener {
            if (::onButtonClickListener.isInitialized) {
                onButtonClickListener.onClick(getItem(position))
            }
        }
    }

    interface OnButtonClickListener{
        fun onClick(itemView: ItemView)
    }

    fun setOnButtonClickListener(listener: OnButtonClickListener){
        onButtonClickListener = listener
    }

    class FilmsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemName: TextView = itemView.findViewById(R.id.item_list_full_name)
        val itemSkills: TextView = itemView.findViewById(R.id.item_list_skills)
    }
}
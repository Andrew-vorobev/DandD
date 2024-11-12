package com.example.dandd.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
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
    ListAdapter<ClassView, ItemsRecyclerView.ItemsHolder>(ItemDiffUtilCallback()) {
    private lateinit var onClickToDetail: OnClickToDetail
    private lateinit var onClickSave: OnClickSave

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemsHolder {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return ItemsHolder(item)
    }

    override fun onBindViewHolder(holder: ItemsHolder, position: Int) {
        holder.classView.text = "Полное название: ${getItem(position).name}"

        holder.buttonSave.setOnClickListener{
            if (::onClickSave.isInitialized){
                onClickSave.onClick(getItem(position))
            }
        }

        holder.itemView.setOnClickListener {
            if (::onClickToDetail.isInitialized) {
                onClickToDetail.onClick(getItem(position))
            }
        }
    }

    interface OnClickToDetail {
        fun onClick(classView: ClassView)
    }

    fun setOnClickToDetail(listener: OnClickToDetail) {
        onClickToDetail = listener
    }

    interface OnClickSave {
        fun onClick(classView: ClassView)
    }

    fun setOnClickSave(listener: OnClickSave) {
        onClickSave = listener
    }

    class ItemsHolder(classView: View) : RecyclerView.ViewHolder(classView) {
        val classView: TextView = itemView.findViewById(R.id.item_list_full_name)

        val buttonSave: ImageButton = itemView.findViewById(R.id.item_list_favourite_button)
    }
}
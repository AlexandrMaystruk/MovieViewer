package com.gmail.maystruks08.filmviewer.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.maystruks08.filmviewer.R
import com.gmail.maystruks08.filmviewer.ui.viewmodels.DefaultView
import kotlin.properties.Delegates

class DefaultAdapter(private val clickListener: (DefaultView) -> Unit) :
    RecyclerView.Adapter<DefaultAdapter.ViewHolder>() {

    var viewList: MutableList<DefaultView> by Delegates.observable(mutableListOf()) { _, _, _ ->
        notifyDataSetChanged()
    }

    fun insertItemOrUpdateIfExist(item: DefaultView) {
        val index = viewList.indexOfFirst { item.id == it.id }
        if (index == -1) {
            viewList.add(item)
            notifyItemInserted(viewList.lastIndex)
        } else {
            viewList.removeAt(index)
            viewList.add(index, item)
            notifyItemChanged(index)
        }
    }

    fun updateItem(item: DefaultView) {
        val index = viewList.indexOfFirst { item.id == it.id }
        if (index == -1) return
        viewList.removeAt(index)
        viewList.add(index, item)
        notifyItemChanged(index)
    }

    fun removeItem(item: DefaultView) {
        val index = viewList.indexOfFirst { item.id == it.id }
        if (index == -1) return
        viewList.removeAt(index)
        notifyItemRemoved(index)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_default, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindHolder(viewList[position])
    }

    override fun getItemCount(): Int = viewList.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener { if (isAdapterPositionCorrect()) clickListener(viewList[adapterPosition]) }
        }

        fun bindHolder(item: DefaultView) {


        }

        private fun isAdapterPositionCorrect(): Boolean = adapterPosition in 0..viewList.lastIndex
    }
}
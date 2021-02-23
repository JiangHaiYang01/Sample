package com.allens.sample_recyclerview

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


import android.view.LayoutInflater
import android.view.View
import android.widget.TextView


class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemTv: TextView by lazy {
            itemView.findViewById(R.id.item_tv)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_content, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemTv.text = "hello world-$position"
    }

    override fun getItemCount(): Int {
        return 10
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }
}


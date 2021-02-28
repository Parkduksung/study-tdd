package com.example.study_tdd.espressotest.recyclerview.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.study_tdd.espressotest.recyclerview.adapter.viewholder.EspressoRecyclerViewHolder
import com.example.study_tdd.espressotest.recyclerview.model.EspressoRecyclerModel

class EspressoRecyclerAdapter : RecyclerView.Adapter<EspressoRecyclerViewHolder>() {

    private val espressoRecyclerList = mutableListOf<EspressoRecyclerModel>()

    private lateinit var espressoItemListener: EspressoItemListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EspressoRecyclerViewHolder =
        EspressoRecyclerViewHolder(parent = parent)

    override fun onBindViewHolder(holder: EspressoRecyclerViewHolder, position: Int) {
        holder.item(espressoRecyclerList[position], espressoItemListener)
    }

    override fun getItemCount(): Int =
        espressoRecyclerList.size

    fun addAll(list: List<EspressoRecyclerModel>) {
        espressoRecyclerList.addAll(list)
        notifyDataSetChanged()
    }

    fun setEspressoItemListener(listener: EspressoItemListener) {
        espressoItemListener = listener
    }
}


interface EspressoItemListener {
    fun onItemClick(item: EspressoRecyclerModel)
}
package com.example.study_tdd.espressotest.recyclerview.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.study_tdd.espressotest.recyclerview.adapter.viewholder.EspressoRecyclerViewHolder
import com.example.study_tdd.espressotest.recyclerview.model.EspressoRecyclerModel

class EspressoRecyclerAdapter : RecyclerView.Adapter<EspressoRecyclerViewHolder>() {

    private val espressoRecyclerList = mutableListOf<EspressoRecyclerModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EspressoRecyclerViewHolder =
        EspressoRecyclerViewHolder(parent = parent)

    override fun onBindViewHolder(holder: EspressoRecyclerViewHolder, position: Int) {
        holder.item(espressoRecyclerList[position])
    }

    override fun getItemCount(): Int =
        espressoRecyclerList.size

    fun addAll(list: List<EspressoRecyclerModel>) {
        espressoRecyclerList.addAll(list)
        notifyDataSetChanged()
    }
}
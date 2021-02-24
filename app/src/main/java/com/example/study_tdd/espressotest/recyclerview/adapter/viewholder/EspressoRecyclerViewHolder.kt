package com.example.study_tdd.espressotest.recyclerview.adapter.viewholder

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.study_tdd.R
import com.example.study_tdd.espressotest.recyclerview.model.EspressoRecyclerModel

class EspressoRecyclerViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(
        R.layout.item_espresso_recycler, parent, false
    )
) {
    private val name = itemView.findViewById<TextView>(R.id.name)
    private val age = itemView.findViewById<TextView>(R.id.age)
    private val job = itemView.findViewById<TextView>(R.id.job)
    private val career = itemView.findViewById<TextView>(R.id.career)

    fun item(espressoRecyclerModel: EspressoRecyclerModel) {
        name.text = espressoRecyclerModel.name
        age.text = espressoRecyclerModel.age.toString()
        job.text = espressoRecyclerModel.job
        career.text = espressoRecyclerModel.career.toString()
    }

}
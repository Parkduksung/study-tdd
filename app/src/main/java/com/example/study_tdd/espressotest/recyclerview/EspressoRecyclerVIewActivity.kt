package com.example.study_tdd.espressotest.recyclerview

import android.os.Bundle
import android.widget.Toast
import com.example.study_tdd.R
import com.example.study_tdd.base.BaseActivity
import com.example.study_tdd.databinding.ActivityEspressoRecyclerBinding
import com.example.study_tdd.espressotest.recyclerview.adapter.EspressoItemListener
import com.example.study_tdd.espressotest.recyclerview.adapter.EspressoRecyclerAdapter
import com.example.study_tdd.espressotest.recyclerview.model.EspressoRecyclerModel

class EspressoRecyclerVIewActivity :
    BaseActivity<ActivityEspressoRecyclerBinding>(R.layout.activity_espresso_recycler),
    EspressoItemListener {

    private val espressoRecyclerAdapter by lazy { EspressoRecyclerAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.espressoRecycler.run {
            adapter = espressoRecyclerAdapter.apply {
                setEspressoItemListener(this@EspressoRecyclerVIewActivity)
                addAll(mockEspressoObject)
            }
        }
    }

    override fun onItemClick(item: EspressoRecyclerModel) {
        Toast.makeText(this, "결과 ${item.name} ${item.career} 눌림.", Toast.LENGTH_SHORT).show()
    }

    companion object {
        private val mockEspressoObject = mutableListOf<EspressoRecyclerModel>().apply {
            for (i in 1 until 50) {
                add(EspressoRecyclerModel(name = "박덕성", age = 30, job = "programmer", career = i))
            }
        }
    }
}
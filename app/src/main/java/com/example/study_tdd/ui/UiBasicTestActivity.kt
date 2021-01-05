package com.example.study_tdd.ui

import android.annotation.SuppressLint
import android.os.Bundle
import com.example.study_tdd.R
import com.example.study_tdd.base.BaseActivity
import com.example.study_tdd.databinding.ActivityUiBasicBinding

class UiBasicTestActivity : BaseActivity<ActivityUiBasicBinding>(R.layout.activity_ui_basic) {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btn.setOnClickListener {
            binding.tv.text = "Click Check"
        }
    }
}
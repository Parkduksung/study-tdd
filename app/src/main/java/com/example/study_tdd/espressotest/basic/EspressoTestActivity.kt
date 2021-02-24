package com.example.study_tdd.espressotest.basic

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.study_tdd.R
import com.example.study_tdd.base.BaseActivity
import com.example.study_tdd.databinding.ActivityEspressoTestBinding
import com.example.study_tdd.ui.UiBasicTestActivity

class EspressoTestActivity :
    BaseActivity<ActivityEspressoTestBinding>(R.layout.activity_espresso_test) {

    private lateinit var espressoViewModel: EspressoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        espressoViewModel = ViewModelProvider(this).get(EspressoViewModel::class.java)

        binding.run {
            viewModel = espressoViewModel
        }

        clickButton()


    }

    private fun clickButton() {

        binding.button1.setOnClickListener {
            binding.clickOutput1.text = "button1"
        }

        binding.button2.setOnClickListener {
            binding.clickOutput2.text = "button2"
        }

        binding.button3.setOnClickListener {
            binding.clickOutput3.text = "button3"
        }


        binding.startUiTestButton.setOnClickListener {
            startActivity(Intent(this, UiBasicTestActivity::class.java))
        }

        binding.startDialogButton.setOnClickListener {

            val alertDialog = AlertDialog.Builder(this)
                .setMessage("startDialogButton눌림")
                .setNeutralButton(
                    "OK"
                ) { _, _ -> }
                .create()

            alertDialog.show()
        }

        binding.startDialogAndNextActivity.setOnClickListener {
            AlertDialog.Builder(this).apply {
                setMessage("startDialogAndNextActivity눌림")
                setPositiveButton("OK") { _, _ ->
                    startActivity(
                        Intent(
                            this@EspressoTestActivity,
                            UiBasicTestActivity::class.java
                        )
                    )
                }
                setNegativeButton("No") { _, _ -> }
                create()
                show()
            }
        }

        binding.notEnableButton.setOnClickListener {
            binding.notEnableButton.isEnabled = false
        }


    }


}
package com.example.study_tdd.espressotest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EspressoViewModel : ViewModel() {

    val input1 = MutableLiveData<String>()
    val input2 = MutableLiveData<String>()
    val input3 = MutableLiveData<String>()
    val input4 = MutableLiveData<String>()
}
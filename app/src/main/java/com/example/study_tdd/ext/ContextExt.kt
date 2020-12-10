package com.example.study_tdd.ext

import android.content.Context
import android.widget.Toast


fun Context.showToast(message:String , kind : Int = Toast.LENGTH_SHORT){
    Toast.makeText(this, message, kind).show()
}
package com.example.study_tdd.file.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.study_tdd.permission.domain.PermissionDomain
import kotlinx.coroutines.launch

class PermissionViewModel(private val permissionCallback: PermissionCallback, private val permissionDomain : PermissionDomain) : ViewModel() {

    var permissionStatus : Boolean = false

    fun request() {

        viewModelScope.launch {
            permissionStatus = permissionDomain.request()

            if (permissionStatus) {
                permissionCallback.onSuccess()
            } else {
                permissionCallback.onFail()
            }
        }
    }

}

interface PermissionCallback {

    fun onSuccess()

    fun onFail()
}
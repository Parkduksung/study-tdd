package com.example.study_tdd.conpany.permission.domain

import com.example.study_tdd.conpany.permission.AndroidPermission
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PermissionDomain constructor(private val permission: AndroidPermission) {
    suspend fun request(): Boolean = withContext(Dispatchers.IO) {
        return@withContext permission.request()
    }

    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        permission.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
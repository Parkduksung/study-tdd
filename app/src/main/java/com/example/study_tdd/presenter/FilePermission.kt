package com.example.study_tdd.presenter

import android.Manifest
import androidx.core.content.PermissionChecker

class FilePermission(
    private val checkSelfPermission: CheckSelfPermission,
    private val activityHandler: ActivityHandler
) : AndroidPermission {

    companion object {
        private val FILE_PERMISSION = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
        private const val REQUEST_CODE: Int = 10
    }

    override suspend fun request(): Boolean {
        val permissionResult = checkSelfPermission.check()
        if (permissionResult == PermissionChecker.PERMISSION_GRANTED) {
            return true
        }

        val deferred = activityHandler.requestPermission(FILE_PERMISSION, REQUEST_CODE)
        return deferred.await()
    }
}
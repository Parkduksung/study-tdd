package com.rsupport.tdd.permission

import android.Manifest
import androidx.core.content.PermissionChecker
import com.rsupport.tdd.presenter.ActivityHandler

class FilePermission(
    private val checkSelfPermission: CheckSelfPermission,
    private val activityHandler: ActivityHandler
) : AndroidPermission {

    companion object{
        private const val FILE_PERMISSION = Manifest.permission.WRITE_EXTERNAL_STORAGE
        private const val REQUEST_CODE: Int = 0
    }

    override suspend fun request(): Boolean {
        val permissionResult = checkSelfPermission.check(FILE_PERMISSION)
        if(permissionResult == PermissionChecker.PERMISSION_GRANTED){
            return true
        }

        val deferred = activityHandler.requestPermission(FILE_PERMISSION, REQUEST_CODE)
        return deferred.await()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        activityHandler.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
package com.example.study_tdd.presenter

import androidx.core.content.PermissionChecker

class FilePermission(
    private val checkSelfPermission: CheckSelfPermission,
    private val activityHandler: ActivityHandler
) : AndroidPermission {

    companion object{
        private const val FILE_PERMISSION = ""
        private const val REQUEST_CODE: Int = 0
    }

    override suspend fun request(): Boolean {
        val permissionResult = checkSelfPermission.check()
        if(permissionResult == PermissionChecker.PERMISSION_GRANTED){
            return true
        }

        val deferred = activityHandler.requestPermission(FILE_PERMISSION, REQUEST_CODE)
        return deferred.await()
    }
}
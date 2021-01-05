package com.example.study_tdd.conpany.presenter

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CompletableDeferred

class ActivityHandlerImpl(private val activity: AppCompatActivity) : ActivityHandler {

    private var requestCode: Int = -1
    private val completableDeferred = CompletableDeferred<Boolean>()

    override fun requestPermission(
        permissionName: String,
        requestCode: Int
    ): CompletableDeferred<Boolean> {

        this.requestCode = requestCode

        activity.requestPermissions(arrayOf(permissionName), requestCode)
        return completableDeferred
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        val result = requestCode == this.requestCode &&
                               grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
        completableDeferred.complete(result)
    }
}
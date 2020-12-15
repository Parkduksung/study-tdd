package com.rsupport.tdd.presenter

import kotlinx.coroutines.CompletableDeferred

interface ActivityHandler {
    fun requestPermission(permissionName: String, requestCode: Int): CompletableDeferred<Boolean>
    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray)
}
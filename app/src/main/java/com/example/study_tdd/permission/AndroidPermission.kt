package com.rsupport.tdd.permission

interface AndroidPermission {
    suspend fun request(): Boolean
    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray)
}
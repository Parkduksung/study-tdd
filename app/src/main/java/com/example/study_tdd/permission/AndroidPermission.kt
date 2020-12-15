package com.example.study_tdd.permission

interface AndroidPermission {
    suspend fun request(): Boolean
    fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray)
}
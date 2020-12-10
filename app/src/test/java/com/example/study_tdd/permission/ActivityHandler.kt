package com.rsupport.tdd.permission

import kotlinx.coroutines.CompletableDeferred

interface ActivityHandler {
    fun requestPermission(permissionName: String, requestCode: Int): CompletableDeferred<Boolean>
}
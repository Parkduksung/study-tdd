package com.rsupport.tdd.permission

interface AndroidPermission {
    suspend fun request(): Boolean
}
package com.example.study_tdd.presenter

interface AndroidPermission {
    suspend fun request(): Boolean
}
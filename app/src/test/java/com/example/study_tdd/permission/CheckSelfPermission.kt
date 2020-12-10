package com.rsupport.tdd.permission

interface CheckSelfPermission{
    /**
     * @return [PermissionChecker.PERMISSION_DENIED], [PermissionChecker.PERMISSION_GRANTED], [PermissionChecker.PERMISSION_DENIED_APP_OP]
     */
    fun check(): Int
}
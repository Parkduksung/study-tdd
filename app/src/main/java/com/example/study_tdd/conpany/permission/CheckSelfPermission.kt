package com.example.study_tdd.conpany.permission

interface CheckSelfPermission{
    /**
     * @return [PermissionChecker.PERMISSION_DENIED], [PermissionChecker.PERMISSION_GRANTED], [PermissionChecker.PERMISSION_DENIED_APP_OP]
     */
    fun check(permissionName: String): Int
}
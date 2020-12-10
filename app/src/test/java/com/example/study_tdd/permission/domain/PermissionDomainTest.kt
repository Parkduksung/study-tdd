package com.rsupport.tdd.permission.domain

import com.rsupport.tdd.permission.AndroidPermission
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Test

class PermissionDomainTest {

    @Test
    fun `권한을 수락했을때 다음 화면으로 이동 이벤트 발생을 확인한다`() = runBlocking{
        val permissionInteractor = PermissionDomain(PermissionSuccessStub())
        val permissionResult = permissionInteractor.request()
        assertTrue(permissionResult)
    }

    @Test
    fun `권한을 수락하지 않았을때 실패 이벤트 발생을 확인한다`() = runBlocking{
        val permissionInteractor = PermissionDomain(PermissionFailStub())
        val permissionResult = permissionInteractor.request()
        assertFalse(permissionResult)
    }

}


class PermissionSuccessStub: AndroidPermission {
    override suspend fun request(): Boolean {
        return true
    }
}


class PermissionFailStub: AndroidPermission {
    override suspend fun request(): Boolean {
        return false
    }
}


class PermissionDomain constructor(private val permission: AndroidPermission) {
    suspend fun request(): Boolean {
        return permission.request()
    }
}

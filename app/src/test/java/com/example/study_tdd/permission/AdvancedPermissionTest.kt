package com.rsupport.tdd.permission

import androidx.core.content.PermissionChecker
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.runBlocking
import org.junit.Test

class AdvancedPermissionTest {

    @Test
    fun `사용자가 파일 권한을 거절했을때 false 를 확인한다`() = runBlocking{
        val selfPermission = mockk<CheckSelfPermission>()
        val activityHandler = mockk<ActivityHandler>()
        every { selfPermission.check() }.returns(PermissionChecker.PERMISSION_DENIED)
        every { activityHandler.requestPermission(any(), any()) }.returns(CompletableDeferred(false))

        val filePermission = FilePermission(
            checkSelfPermission = selfPermission,
            activityHandler = activityHandler
        )
        assertFalse(filePermission.request())

    }

    @Test
    fun `사용자가 파일 권한을 수락했을때 true 를 확인한다`() = runBlocking{
        val selfPermission = mockk<CheckSelfPermission>()
        val activityHandler = mockk<ActivityHandler>()
        every { selfPermission.check() }.returns(PermissionChecker.PERMISSION_DENIED)
        every { activityHandler.requestPermission(any(), any()) }.returns(CompletableDeferred(true))

        val filePermission = FilePermission(
            checkSelfPermission = selfPermission,
            activityHandler = activityHandler
        )
        assertTrue(filePermission.request())
    }
}


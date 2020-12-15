package com.example.study_tdd.file.viewmodel

import com.example.study_tdd.permission.domain.PermissionDomain
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import java.lang.Boolean.FALSE
import java.lang.Boolean.TRUE

class PermissionModelTest {

    @Before
    fun setup() {
        Dispatchers.setMain(TestCoroutineDispatcher())
    }

    @Test
    fun `승인이 됬을때의 호출`() {

        var domain = mockk<PermissionDomain>()
        var callback = mockk<PermissionCallback>(relaxed = true)

        var model = PermissionViewModel(callback, domain)

        coEvery{ domain.request() }.returns(TRUE)

        model.request()

        verify { callback.onSuccess() }
    }

    @Test
    fun `거절이 됬을때의 호출`() {

        var domain = mockk<PermissionDomain>()
        var callback = mockk<PermissionCallback>(relaxed = true)

        var model = PermissionViewModel(callback, domain)

        coEvery{ domain.request() }.returns(FALSE)

        model.request()

        verify { callback.onFail() }
    }

}


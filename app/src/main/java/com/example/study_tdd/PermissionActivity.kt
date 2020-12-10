package com.example.study_tdd

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker
import com.example.study_tdd.base.BaseActivity
import com.example.study_tdd.databinding.ActivityPermissionBinding
import com.example.study_tdd.ext.showToast
import com.example.study_tdd.presenter.ActivityHandler
import com.example.study_tdd.presenter.CheckSelfPermission
import com.example.study_tdd.presenter.FilePermission
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.runBlocking

class PermissionActivity : BaseActivity<ActivityPermissionBinding>(R.layout.activity_permission) {

    private val filePermission: FilePermission by lazy {
        FilePermission(
            checkSelfPermission,
            activityHandler
        )
    }
    private lateinit var checkSelfPermission: CheckSelfPermission
    private lateinit var activityHandler: ActivityHandler

    private var resultRequestPermission: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnPermissionCheck.setOnClickListener {
            runBlocking {
                if (filePermission.request()) {
                    showToast("권환 체크 이미 ok")
                } else {
                    ActivityCompat.requestPermissions(
                        this@PermissionActivity, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS
                    )
                }
            }
        }

        checkSelfPermission = object : CheckSelfPermission {
            override fun check(): Int {
                return if (checkPermissionGranted()) {
                    PermissionChecker.PERMISSION_GRANTED
                } else {
                    PermissionChecker.PERMISSION_DENIED
                }
            }
        }

        activityHandler = object : ActivityHandler {
            override fun requestPermission(
                permissionName: Array<String>,
                requestCode: Int
            ): CompletableDeferred<Boolean> {
                return CompletableDeferred(resultRequestPermission)
            }
        }


    }

    private fun checkPermissionGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            baseContext, it
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            resultRequestPermission = checkPermissionGranted()
            if (checkPermissionGranted()) {
                showToast("권환 체크 ok")
            } else {
                showToast("권환 체크 no")
            }
        }
    }

    companion object {
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS = arrayOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
    }
}
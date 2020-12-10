package com.example.study_tdd

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.study_tdd.base.BaseActivity
import com.example.study_tdd.databinding.ActivityPermissionBinding
import com.example.study_tdd.ext.showToast

class PermissionActivity : BaseActivity<ActivityPermissionBinding>(R.layout.activity_permission) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnPermissionCheck.setOnClickListener {
            showToast("눌림")

            if (checkPermissionGranted()) {

            } else {
                ActivityCompat.requestPermissions(
                    this, REQUIRED_PERMISSIONS, REQUEST_CODE_PERMISSIONS
                )
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
            if (checkPermissionGranted()) {

            } else {
                showToast("실패")
                finish()
            }
        }
    }


    companion object {
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS = arrayOf(
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )
    }
}
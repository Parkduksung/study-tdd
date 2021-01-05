package com.example.study_tdd.conpany.file

import android.app.Activity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.study_tdd.conpany.permission.CheckSelfPermission
import com.example.study_tdd.conpany.permission.FilePermission
import com.example.study_tdd.conpany.permission.domain.PermissionDomain
import com.example.study_tdd.conpany.presenter.ActivityHandlerImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FilePermissionActivity : AppCompatActivity() {

    private lateinit var domain: PermissionDomain
    private lateinit var job: Job

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.buttonGetFilePermission).setOnClickListener {
            val filePermission = FilePermission(
                checkSelfPermission = CheckSelfPermissionImpl(this),
                activityHandler = ActivityHandlerImpl(this)
            )

            this.domain = PermissionDomain(filePermission)

            this.job = CoroutineScope(Dispatchers.Main).launch {
                val result = domain.request();
                Toast.makeText(this@FilePermissionActivity, "RESULT: $result", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        domain.onRequestPermissionsResult(requestCode, permissions, grantResults)
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }

    class CheckSelfPermissionImpl(private val activity: Activity) : CheckSelfPermission {
        override fun check(permissionName: String): Int {
            return activity.checkSelfPermission(permissionName)
        }
    }

}
package com.example.permission

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import java.security.Permission
import java.security.Permissions
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    private val M_RequestCode: Int = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        checkPermission()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == M_RequestCode && grantResults.isNotEmpty()) {
            for (i in grantResults.indices) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED)
                    Toast.makeText(this, permissions[i] + " is granted", Toast.LENGTH_LONG).show()
                else
                    checkPermission()
            }
        }
    }

    private fun checkPermission() {
        var listPermissionsNeeded: ArrayList<String> = ArrayList()
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.INTERNET
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            listPermissionsNeeded.add(android.Manifest.permission.INTERNET)
        }
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            listPermissionsNeeded.add(android.Manifest.permission.READ_EXTERNAL_STORAGE)
        }
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            listPermissionsNeeded.add(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }

        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            listPermissionsNeeded.add(android.Manifest.permission.ACCESS_COARSE_LOCATION)
        }

        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_BACKGROUND_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            listPermissionsNeeded.add(android.Manifest.permission.ACCESS_BACKGROUND_LOCATION)
        }

        // chưa đủ quyền
        if (listPermissionsNeeded.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                listPermissionsNeeded.toTypedArray(),
                M_RequestCode
            )
        } else {
            Toast.makeText(this,"All permission is granted", Toast.LENGTH_LONG).show()
        }

    }
}
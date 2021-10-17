package com.example.recipefinder

import android.Manifest
import android.app.Application
import com.example.data.repository.PermissionCheck
import com.example.data.repository.PermissionCheck.Permission
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class PermissionChecker(private val application: Application): PermissionCheck {

    override suspend fun request(permission: List<Permission>): Pair<Boolean,Boolean> =
        suspendCancellableCoroutine { continuation ->
            Dexter
                .withContext(application)
                .withPermissions(permission.map { it.toAndroidId() })
                .withListener(object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(p0: MultiplePermissionsReport?) {
                        if(p0?.areAllPermissionsGranted() == true){
                            continuation.resume(Pair(true, second = false))
                        }else{
                            continuation.resume(Pair(false,!p0!!.isAnyPermissionPermanentlyDenied))
                        }
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        p0: MutableList<PermissionRequest>?,
                        p1: PermissionToken?
                    ) {
                        p1?.continuePermissionRequest()
                    }
                }
                ).check()
        }
}
private fun Permission.toAndroidId() = when (this) {
    Permission.COARSE_LOCATION -> Manifest.permission.ACCESS_COARSE_LOCATION
    Permission.FINE_LOCATION -> Manifest.permission.ACCESS_FINE_LOCATION
}
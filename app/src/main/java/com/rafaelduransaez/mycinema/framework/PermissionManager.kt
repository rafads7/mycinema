package com.rafaelduransaez.mycinema.framework

import androidx.activity.ComponentActivity
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

class PermissionManager(activity: ComponentActivity, private val permission: String) {

    private var onRequest: (Boolean) -> Unit = {}
    private val launcher = activity.registerForActivityResult(
        //if requesting multiple permissions, use RequestMultiplePermissions
        ActivityResultContracts.RequestPermission()) { isGranted ->
        onRequest(isGranted)
    }

    /*
    suspend fun main() {
        val permission = enableOnRequest()
        doRequest()
    }

    suspend fun enableOnRequest(): Boolean = suspendCancellableCoroutine { continuation ->
        onRequest = {
            continuation.resume(it)
        }
    }

    fun doRequest() {
        launcher.launch(permission)
    }

     */

    suspend fun request(): Boolean =
        suspendCancellableCoroutine { continuation ->
            onRequest = {
                continuation.resume(it)
            }
            launcher.launch(permission)
        }

}
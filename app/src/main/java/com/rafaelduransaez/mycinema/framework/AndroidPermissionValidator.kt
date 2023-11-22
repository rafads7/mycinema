package com.rafaelduransaez.mycinema.framework

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.rafaelduransaez.data.PermissionsValidator
import com.rafaelduransaez.data.PermissionsValidator.MoviesPermission
import com.rafaelduransaez.data.PermissionsValidator.MoviesPermission.FINE_LOCATION
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class AndroidPermissionValidator @Inject constructor(
    @ApplicationContext private val context: Context): PermissionsValidator {

    override fun validate(permission: MoviesPermission): Boolean =
        ContextCompat.checkSelfPermission(
            context,
            permission.toAndroidId()
        ) == PackageManager.PERMISSION_GRANTED
}

private fun MoviesPermission.toAndroidId() = when (this) {
    FINE_LOCATION -> ACCESS_FINE_LOCATION
}
package com.rafaelduransaez.mycinema.data

import android.annotation.SuppressLint
import android.app.Application
import android.location.Geocoder
import android.location.Location
import android.os.Build
import com.google.android.gms.location.LocationServices
import com.rafaelduransaez.data.datasources.LocationDataSource
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import com.rafaelduransaez.domain.entities.Location as MoviesLocation

class AndroidLocationDataSource @Inject constructor(
    app: Application
) : LocationDataSource {

    private var fusedLocationClient = LocationServices.getFusedLocationProviderClient(app)
    private val geocoder = Geocoder(app)


    @SuppressLint("MissingPermission")
    override suspend fun getCurrentLocation(): MoviesLocation? {

        val lastLocation = suspendCancellableCoroutine { continuation ->
            fusedLocationClient.lastLocation.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    continuation.resume(task.result.toMoviesLocation())
                } else {
                    continuation.resume(null)
                }
            }

        }
        return lastLocation
    }

    override suspend fun getCurrentRegion() = getCurrentLocation()?.let {

        val addresses = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            suspendCancellableCoroutine { continuation ->
                geocoder.getFromLocation(it.latitude, it.longitude, 1) { addressesList ->
                    continuation.resume(addressesList)
                }
            }
        } else {
            geocoder.getFromLocation(it.latitude, it.longitude, 1)
        }

        addresses?.firstOrNull()?.countryCode
    }

}

fun Location.toMoviesLocation(): MoviesLocation = MoviesLocation(latitude, longitude)
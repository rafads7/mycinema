package com.rafaelduransaez.mycinema.data

import com.rafaelduransaez.data.PermissionsValidator
import com.rafaelduransaez.data.PermissionsValidator.MoviesPermission.FINE_LOCATION
import com.rafaelduransaez.data.datasources.LocationDataSource
import com.rafaelduransaez.data.repositories.LocationRepository
import com.rafaelduransaez.mycinema.framework.SharedPrefsManager
import javax.inject.Inject

class MoviesLocationRepository @Inject constructor(
    private val locationDataSource: LocationDataSource,
    private val sharedPrefsManager: SharedPrefsManager,
    private val permissionsValidator: PermissionsValidator
) : LocationRepository {
    override suspend fun getCurrentRegion(): String {
        if (permissionsValidator.validate(FINE_LOCATION))
            locationDataSource.getCurrentRegion()?.let { sharedPrefsManager.regionCode = it }
        return sharedPrefsManager.regionCode
    }

}

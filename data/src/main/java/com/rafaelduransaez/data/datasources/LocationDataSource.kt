package com.rafaelduransaez.data.datasources

import com.rafaelduransaez.domain.entities.Location

interface LocationDataSource {

    suspend fun getCurrentLocation(): Location?
    suspend fun getCurrentRegion(): String?

}

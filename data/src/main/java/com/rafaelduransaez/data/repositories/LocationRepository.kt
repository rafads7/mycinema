package com.rafaelduransaez.data.repositories

interface LocationRepository {
    suspend fun getCurrentRegion(): String
}
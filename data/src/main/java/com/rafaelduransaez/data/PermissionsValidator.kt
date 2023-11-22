package com.rafaelduransaez.data

interface PermissionsValidator {

    enum class MoviesPermission {
        FINE_LOCATION
    }
    fun validate(permission: MoviesPermission): Boolean

}
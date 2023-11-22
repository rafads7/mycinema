package com.rafaelduransaez.mycinema.di

import com.rafaelduransaez.data.PermissionsValidator
import com.rafaelduransaez.data.datasources.LocationDataSource
import com.rafaelduransaez.data.datasources.MoviesRemoteDataSource
import com.rafaelduransaez.data.repositories.LocationRepository
import com.rafaelduransaez.mycinema.data.AndroidLocationDataSource
import com.rafaelduransaez.mycinema.data.MoviesLocationRepository
import com.rafaelduransaez.mycinema.data.TMDBRemoteDataSource
import com.rafaelduransaez.mycinema.framework.AndroidPermissionValidator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindLocationRepository(localDataSource: MoviesLocationRepository): LocationRepository

    @Binds
    abstract fun bindLocationDataSource(locationDataSource: AndroidLocationDataSource): LocationDataSource

    @Binds
    abstract fun bindTMDBRemoteDataSource(moviesRemoteDataSource: TMDBRemoteDataSource): MoviesRemoteDataSource

    @Binds
    abstract fun bindAndroidPermissionValidator(validator: AndroidPermissionValidator): PermissionsValidator
}
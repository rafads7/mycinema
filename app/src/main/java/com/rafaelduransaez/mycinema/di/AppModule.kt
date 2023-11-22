package com.rafaelduransaez.mycinema.di

import android.app.Application
import com.rafaelduransaez.mycinema.MoviesApplication
import com.rafaelduransaez.mycinema.framework.api.MoviesService
import com.rafaelduransaez.mycinema.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    @ApiKey
    fun provideApiKey(): String = Constants.api_key

    @Provides
    @Singleton
    @ApiUrl
    fun provideApiUrl(): String = Constants.api_url

    @Provides
    @Singleton
    fun provideMoviesApplication(app: Application) = app as MoviesApplication
    //try to use

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient = HttpLoggingInterceptor().run {
        level = HttpLoggingInterceptor.Level.BODY
        OkHttpClient.Builder().addInterceptor(this).build()
    }

    @Provides
    @Singleton
    fun provideMoviesService(@ApiUrl apiUrl: String, okHttpClient: OkHttpClient): MoviesService {

        return Retrofit.Builder()
            .baseUrl(apiUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }
}
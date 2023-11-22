package com.rafaelduransaez.mycinema.framework

import android.content.Context

import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SharedPrefsManager @Inject constructor(@ApplicationContext context: Context) {

    private val sharedPreferences: SharedPreferences
    private val editor: SharedPreferences.Editor

    init {
        sharedPreferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    var regionCode: String
        get() = sharedPreferences.getString(KEY_REGION_CODE, DEFAULT_REGION) ?: DEFAULT_REGION
        set(value) {
            editor.putString(KEY_REGION_CODE, value)
            editor.apply()
        }

    fun clearPreferences() {
        editor.clear()
        editor.apply()
    }

    companion object {
        private const val PREFERENCES_NAME = "MyCinemaPreferences"
        private const val KEY_REGION_CODE = "regionCode"
        private const val DEFAULT_REGION = "US"
    }
}
package com.vechkanov.drive64.data.local.sharedPref

import android.content.Context
import android.content.SharedPreferences
import com.vechkanov.drive64.SHARED_PREFS_NAME
import java.io.Serializable
import javax.inject.Inject

const val CURRENT_USER_PREF_KEY = "current_user"
class SharedPreferenceProvider
@Inject constructor(appContext: Context) {
    private val sharedPref: SharedPreferences = appContext.getSharedPreferences(SHARED_PREFS_NAME, Context.MODE_PRIVATE)

    fun saveToSharedPref(key: String, value: Long) {
        with (sharedPref.edit()) {
            putLong(key, value)
            apply()
        }
    }

    fun saveToSharedPref(key: String, value: Boolean) {
        with (sharedPref.edit()) {
            putBoolean(key, value)
            apply()
        }
    }

    fun saveToSharedPref(key: String, value: Float) {
        with (sharedPref.edit()) {
            putFloat(key, value)
            apply()
        }
    }

    fun saveToSharedPref(key: String, value: String) {
        with (sharedPref.edit()) {
            putString(key, value)
            apply()
        }
    }

    fun getSharedPref(key: String, defValue: String?): String? {
        return sharedPref.getString(key, defValue)
    }

    fun getSharedPref(key: String, defValue: Float): Float {
        return sharedPref.getFloat(key, defValue)
    }

    fun getSharedPref(key: String, defValue: Boolean): Boolean {
        return sharedPref.getBoolean(key, defValue)
    }

    fun getSharedPref(key: String, defValue: Long): Long {
        return sharedPref.getLong(key, defValue)
    }

}
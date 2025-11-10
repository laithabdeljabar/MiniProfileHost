package com.laithabdeljabar.miniprofilehost

import android.content.Context
import android.content.SharedPreferences
import java.util.concurrent.atomic.AtomicBoolean

class ProfileManager private constructor(
    private val preferences: SharedPreferences
) {

    companion object {
        private const val PREFS_NAME = "mini_profile_host_prefs"
        private const val KEY_INITIALIZED = "initialized"
        private const val KEY_USER_NAME = "user_name"
        private const val KEY_USER_EMAIL = "user_email"
        private const val KEY_PROFILE_IMAGE = "profile_image"
        private const val KEY_USER_PHONE = "user_phone"
        private const val KEY_LAST_UPDATED = "last_updated"

        @Volatile
        private var instance: ProfileManager? = null

        fun getInstance(context: Context): ProfileManager {
            return instance ?: synchronized(this) {
                instance ?: ProfileManager(
                    context.applicationContext.getSharedPreferences(
                        PREFS_NAME,
                        Context.MODE_PRIVATE
                    )
                ).also { instance = it }
            }
        }
    }

    private val initialized = AtomicBoolean(
        preferences.getBoolean(KEY_INITIALIZED, false)
    )

    fun initialize() {
        if (initialized.compareAndSet(false, true)) {
            preferences.edit()
                .putBoolean(KEY_INITIALIZED, true)
                .putLong(KEY_LAST_UPDATED, System.currentTimeMillis())
                .apply()
        }
    }

    fun isInitialized(): Boolean = initialized.get()

    fun getUserName(): String? = preferences.getString(KEY_USER_NAME, null)

    fun getUserEmail(): String? = preferences.getString(KEY_USER_EMAIL, null)

    fun getProfileImage(): String? = preferences.getString(KEY_PROFILE_IMAGE, null)

    fun getUserPhone(): String? = preferences.getString(KEY_USER_PHONE, null)

    fun getLastUpdatedTime(): Long = preferences.getLong(KEY_LAST_UPDATED, 0L)

    fun setUserName(value: String): Boolean = updatePreference(KEY_USER_NAME, value)

    fun setUserEmail(value: String): Boolean = updatePreference(KEY_USER_EMAIL, value)

    fun setProfileImage(value: String): Boolean = updatePreference(KEY_PROFILE_IMAGE, value)

    fun setUserPhone(value: String): Boolean = updatePreference(KEY_USER_PHONE, value)

    private fun updatePreference(key: String, value: String): Boolean {
        return preferences.edit()
            .putString(key, value)
            .putLong(KEY_LAST_UPDATED, System.currentTimeMillis())
            .commit()
    }
}


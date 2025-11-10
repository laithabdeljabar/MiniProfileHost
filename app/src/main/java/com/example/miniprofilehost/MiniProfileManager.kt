package com.example.miniprofilehost

import android.content.Context
import android.content.Intent

class MiniProfileManager private constructor() {

    companion object {
        private var instance: MiniProfileManager? = null

        fun getInstance(): MiniProfileManager {
            return instance ?: synchronized(this) {
                instance ?: MiniProfileManager().also { instance = it }
            }
        }
    }

    fun startMiniProfile(context: Context) {
        val intent = Intent(context, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(intent)
    }

    fun startMiniProfileWithData(context: Context, userId: String, userName: String) {
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            putExtra("USER_ID", userId)
            putExtra("USER_NAME", userName)
        }
        context.startActivity(intent)
    }

    // Add more functions as needed
}


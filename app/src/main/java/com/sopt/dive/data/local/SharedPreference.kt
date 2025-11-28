package com.sopt.dive.data.local

import android.content.Context
import android.content.SharedPreferences
import com.sopt.dive.data.dto.login.ResponseLoginDto

class SharedPreference(context: Context) {

    private val prefsName = "UserInfo"
    private val prefs: SharedPreferences = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE)

    fun saveUserId(id: Long) {
        prefs.edit().apply {
            putLong(prefKeys.KEY_USER_ID, id)
            putString(prefKeys.KEY_MESSAGE,"")
            apply()
        }
    }

    fun getUserId(): ResponseLoginDto {
        return ResponseLoginDto(
            userId = prefs.getLong(prefKeys.KEY_USER_ID, 0),
            message = prefs.getString(prefKeys.KEY_MESSAGE,"") ?: ""
        )
    }

    object prefKeys {
        const val KEY_USER_ID = "userId"
        const val KEY_MESSAGE = "message"
    }
}



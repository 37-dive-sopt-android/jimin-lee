package com.sopt.dive.data.local

import android.content.Context
import android.content.SharedPreferences
import com.sopt.dive.data.model.UserInfo

class SharedPreference(context: Context) {

    private val prefsName = "UserInfo"
    private val prefs: SharedPreferences = context.getSharedPreferences(prefsName, Context.MODE_PRIVATE)

    fun saveUserInfo(id: String, pw: String, nickname: String, mbti: String) {
        prefs.edit().apply {
            putString(prefKeys.KEY_USER_ID, id)
            putString(prefKeys.KEY_USER_PW, pw)
            putString(prefKeys.KEY_USER_NICKNAME, nickname)
            putString(prefKeys.KEY_USER_MBTI, mbti)
            apply()
        }
    }

    fun getUserInfo(): UserInfo {
        return UserInfo(
            id = prefs.getString(prefKeys.KEY_USER_ID, "") ?: "",
            pw = prefs.getString(prefKeys.KEY_USER_PW, "") ?: "",
            nickname = prefs.getString(prefKeys.KEY_USER_NICKNAME, "") ?: "",
            mbti = prefs.getString(prefKeys.KEY_USER_MBTI, "") ?: ""
        )
    }

    object prefKeys {
        const val KEY_USER_ID = "userId"
        const val KEY_USER_PW = "userPw"
        const val KEY_USER_NICKNAME = "userNickname"
        const val KEY_USER_MBTI = "userMbti"
    }
}



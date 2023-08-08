package ru.mokita.together.data.source.local

import android.content.SharedPreferences

class SharedPreferencesHelper(private val sharedPreferences: SharedPreferences) {

    fun token(): String = sharedPreferences.getString("token", "") ?: ""


    fun setToken(token: String) {
        sharedPreferences.edit().putString("token", token).apply()
    }
}

package com.example.lend.utils

import android.content.Context
import android.content.SharedPreferences

object SharedPrefUtility {


    const val SP_FILE_NAME = "app_prefs"

    fun getSharedPref(context: Context): SharedPreferences? {
        return context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE)
    }
}
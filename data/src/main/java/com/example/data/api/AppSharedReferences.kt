package com.example.data.api

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

object AppSharedReferences {

    private lateinit var prefs: SharedPreferences

    private const val PREFS_NAME = "SharedPrefs"

    const val ID_USER = "id_user"
    const val TOKEN = "token"

    fun init(context: Context) {
        prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun remove(key: String){
        val prefsEditor : SharedPreferences.Editor = prefs.edit()
        with(prefsEditor){
            remove(key)
            commit()
        }
    }



    fun read(key: String, defValue: String): String? {
        return prefs.getString(key, defValue)
    }
    fun read(key: String, defValue: Boolean): Boolean {
        return prefs.getBoolean(key, defValue)
    }

    fun read(key: String, defValue: Long): Long {
        return prefs.getLong(key, defValue)
    }

    fun write(key: String, value: String) {
        val prefsEditor: SharedPreferences.Editor = prefs.edit()
        with(prefsEditor) {
            putString(key, value)
            commit()
        }
    }

    fun write(key: String, value: Long) {
        val prefsEditor: SharedPreferences.Editor = prefs.edit()
        with(prefsEditor) {
            putLong(key, value)
            commit()
        }
    }

    fun write(key: String, value: Boolean) {
        val prefsEditor: SharedPreferences.Editor = prefs.edit()
        with(prefsEditor) {
            putBoolean(key, value)
            commit()
        }
    }
}
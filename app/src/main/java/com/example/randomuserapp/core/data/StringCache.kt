package com.example.randomuserapp.core.data

import android.content.SharedPreferences

interface StringCache {

    fun save(value: String)

    fun read(): String

    class Base(
        private val key: String,
        private val stringPermanentStorage: StringPermanentStorage,
        private val default: String,
    ) : StringCache {

        override fun save(value: String) {
            stringPermanentStorage.save(key, value)
        }

        override fun read(): String {
            return stringPermanentStorage.read(key, default)
        }
    }
}

interface StringPermanentStorage {

    fun save(key: String, value: String)

    fun read(key: String, default: String): String

    class Base(
        private val sharedPreferences: SharedPreferences,
    ) : StringPermanentStorage {

        override fun save(key: String, value: String) {
            sharedPreferences.edit().putString(key, value).apply()
        }

        override fun read(key: String, default: String): String {
            return sharedPreferences.getString(key, default) ?: default
        }
    }
}
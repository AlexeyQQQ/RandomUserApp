package com.example.randomuserapp.load.data

import com.example.randomuserapp.core.data.CacheDataSource
import com.example.randomuserapp.core.data.CloudDataSource
import com.example.randomuserapp.core.data.CloudResponse
import com.example.randomuserapp.core.data.StringCache
import com.example.randomuserapp.load.presentation.LoadScreen

interface LoadRepository {

    fun load(): LoadResult

    fun saveLastScreen()

    class Base(
        private val lastSavedScreen: StringCache,
        private val cacheDataSource: CacheDataSource,
        private val cloudDataSource: CloudDataSource,
    ) : LoadRepository {

        override fun load(): LoadResult {
            return try {
                val data: CloudResponse = cloudDataSource.data()
                cacheDataSource.save(data)
                LoadResult.Success
            } catch (e: Exception) {
                LoadResult.Error(e.message ?: "Unknown error in LoadRepository")
            }
        }

        override fun saveLastScreen() {
            LoadScreen::class.java.canonicalName?.let { lastSavedScreen.save(it) }
        }
    }
}

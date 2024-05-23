package com.example.randomuserapp.load.data

import com.example.randomuserapp.core.CacheDataSource
import com.example.randomuserapp.core.CloudDataSource
import com.example.randomuserapp.core.CloudResponse
import com.example.randomuserapp.core.StringCache
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
package com.example.randomuserapp.user.data

import com.example.randomuserapp.core.data.CacheDataSource
import com.example.randomuserapp.core.data.Result
import com.example.randomuserapp.core.data.StringCache
import com.example.randomuserapp.user.presentation.UserScreen

interface UserRepository {

    fun userInfo(): UserInfo

    fun saveLastScreen()

    class Base(
        private val lastSavedScreen: StringCache,
        private val cacheDataSource: CacheDataSource,
    ) : UserRepository {

        override fun userInfo(): UserInfo {
            val cloudResponse: Result = cacheDataSource.read().results[0]
            with(cloudResponse) {
                return UserInfo(
                    imageUrl = picture.large,
                    firstName = name.first,
                    lastName = name.last,
                    gender = gender,
                    phone = phone,
                )
            }
        }

        override fun saveLastScreen() {
            UserScreen::class.java.canonicalName?.let { lastSavedScreen.save(it) }
        }
    }
}

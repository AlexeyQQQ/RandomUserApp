package com.example.randomuserapp.main.data

import com.example.randomuserapp.core.data.StringCache
import com.example.randomuserapp.main.presentation.Screen

interface MainRepository {

    fun lastSavedScreen(): Screen

    class Base(
        private val lastSavedScreen: StringCache,
    ) : MainRepository {

        override fun lastSavedScreen(): Screen {
            return Class.forName(lastSavedScreen.read())
                .getDeclaredConstructor()
                .newInstance() as Screen
        }
    }
}

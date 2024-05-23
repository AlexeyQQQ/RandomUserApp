package com.example.randomuserapp.core.di

import android.content.Context
import com.example.randomuserapp.BuildConfig
import com.example.randomuserapp.R
import com.example.randomuserapp.core.data.CloudResponse
import com.example.randomuserapp.core.data.IntCache
import com.example.randomuserapp.core.data.IntPermanentStorage
import com.example.randomuserapp.core.data.StringCache
import com.example.randomuserapp.core.data.StringPermanentStorage
import com.example.randomuserapp.load.presentation.LoadScreen
import com.google.gson.Gson

class Core(
    context: Context
) {

    var runUiTest: Boolean = false
    private val gson = Gson()

    val lastSavedScreen: StringCache
    val saveUserInfo: StringCache
    val mockCurrentIndex: IntCache

    init {
        runUiTest = BuildConfig.DEBUG

        val sharedPreferencesFileName =
            if (runUiTest) "ui_test"
            else context.getString(R.string.app_name)

        val sharedPreferences = context.getSharedPreferences(
            sharedPreferencesFileName,
            Context.MODE_PRIVATE,
        )

        val stringPermanentStorage = StringPermanentStorage.Base(sharedPreferences)
        val intPermanentStorage = IntPermanentStorage.Base(sharedPreferences)

        lastSavedScreen = StringCache.Base(
            LAST_SAVED_SCREEN_KEY,
            stringPermanentStorage,
            LoadScreen::class.java.canonicalName
        )

        saveUserInfo = StringCache.Base(
            SAVE_USER_INFO_KEY,
            stringPermanentStorage,
            gson.toJson(CloudResponse(emptyList())),
        )

        mockCurrentIndex = IntCache.Base(
            MOCK_CURRENT_INDEX_KEY,
            intPermanentStorage,
            0
        )
    }

    companion object {
        private const val LAST_SAVED_SCREEN_KEY = "LAST_SAVED_SCREEN_KEY"
        private const val SAVE_USER_INFO_KEY = "SAVE_USER_INFO_KEY"
        private const val MOCK_CURRENT_INDEX_KEY = "MOCK_CURRENT_INDEX_KEY"
    }
}

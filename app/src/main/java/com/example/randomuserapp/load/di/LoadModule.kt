package com.example.randomuserapp.load.di

import androidx.lifecycle.ViewModel
import com.example.randomuserapp.core.data.CacheDataSource
import com.example.randomuserapp.core.data.CloudDataSource
import com.example.randomuserapp.core.data.MockService
import com.example.randomuserapp.core.data.UserInfoService
import com.example.randomuserapp.core.di.Core
import com.example.randomuserapp.core.di.Module
import com.example.randomuserapp.core.di.ProvideAbstract
import com.example.randomuserapp.core.di.ProvideViewModel
import com.example.randomuserapp.load.data.LoadRepository
import com.example.randomuserapp.load.presentation.LoadViewModel
import com.example.randomuserapp.load.presentation.RunAsync
import com.example.randomuserapp.load.presentation.UiObservable
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LoadModule(private val core: Core) : Module<LoadViewModel> {

    override fun viewModel(): LoadViewModel {
        val cacheDataSource = CacheDataSource.Base(core.saveUserInfo, Gson())

        val cloudDataSource = CloudDataSource.Base(
            if (core.runUiTest)
                MockService(core.mockCurrentIndex)
            else
                Retrofit.Builder().baseUrl("https://randomuser.me/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(
                        OkHttpClient.Builder()
                            .retryOnConnectionFailure(true)
                            .addInterceptor(HttpLoggingInterceptor().apply {
                                setLevel(HttpLoggingInterceptor.Level.BODY)
                            })
                            .build()
                    )
                    .build()
                    .create(UserInfoService::class.java)
        )

        return LoadViewModel(
            UiObservable.Base(),
            LoadRepository.Base(core.lastSavedScreen, cacheDataSource, cloudDataSource),
            RunAsync.Base(),
        )
    }
}

class ProvideLoadViewModel(
    core: Core,
    provideOther: ProvideViewModel
) : ProvideAbstract(core, provideOther, LoadViewModel::class.java) {

    override fun module(): Module<out ViewModel> = LoadModule(core)
}
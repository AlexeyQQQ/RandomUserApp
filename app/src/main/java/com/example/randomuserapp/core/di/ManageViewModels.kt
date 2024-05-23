package com.example.randomuserapp.core.di

import androidx.lifecycle.ViewModel
import com.example.randomuserapp.core.data.CacheDataSource
import com.example.randomuserapp.core.data.CloudDataSource
import com.example.randomuserapp.core.data.MockService
import com.example.randomuserapp.core.data.UserInfoService
import com.example.randomuserapp.load.data.LoadRepository
import com.example.randomuserapp.load.presentation.LoadViewModel
import com.example.randomuserapp.load.presentation.RunAsync
import com.example.randomuserapp.load.presentation.UiObservable
import com.example.randomuserapp.main.data.MainRepository
import com.example.randomuserapp.main.presentation.MainViewModel
import com.example.randomuserapp.user.data.UserRepository
import com.example.randomuserapp.user.presentation.UserViewModel
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface ManageViewModels : ProvideViewModel, ClearViewModel

interface ClearViewModel {

    fun clear(clazz: Class<out ViewModel>)
}

interface ProvideViewModel {

    fun <T : ViewModel> viewModel(clazz: Class<T>): T

    class Factory(
        private val provideViewModel: ProvideViewModel,
    ) : ManageViewModels {

        private val viewModelsMap = mutableMapOf<Class<out ViewModel>, ViewModel?>()

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> viewModel(clazz: Class<T>): T {
            return if (viewModelsMap[clazz] == null) {
                val viewModel = provideViewModel.viewModel(clazz)
                viewModelsMap[clazz] = viewModel
                return viewModel
            } else {
                viewModelsMap[clazz] as T
            }
        }

        override fun clear(clazz: Class<out ViewModel>) {
            viewModelsMap[clazz] = null
        }
    }

    class Make(
        private val core: Core,
    ) : ProvideViewModel {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> viewModel(clazz: Class<T>): T {
            return when (clazz) {

                MainViewModel::class.java -> {
                    MainViewModel(MainRepository.Base(core.lastSavedScreen)) as T
                }

                LoadViewModel::class.java -> {
                    val cacheDataSource = CacheDataSource.Base(
                        core.saveUserInfo,
                        Gson()
                    )

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

                    LoadViewModel(
                        UiObservable.Base(),
                        LoadRepository.Base(
                            core.lastSavedScreen,
                            cacheDataSource,
                            cloudDataSource,
                        ),
                        RunAsync.Base(),
                    ) as T
                }

                UserViewModel::class.java -> {
                    val cacheDataSource = CacheDataSource.Base(
                        core.saveUserInfo,
                        Gson()
                    )

                    UserViewModel(
                        UserRepository.Base(
                            core.lastSavedScreen,
                            cacheDataSource,
                        )
                    ) as T
                }

                else -> throw RuntimeException()
            }
        }
    }
}









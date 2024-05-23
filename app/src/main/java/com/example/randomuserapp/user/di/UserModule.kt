package com.example.randomuserapp.user.di

import androidx.lifecycle.ViewModel
import com.example.randomuserapp.core.data.CacheDataSource
import com.example.randomuserapp.core.di.Core
import com.example.randomuserapp.core.di.Module
import com.example.randomuserapp.core.di.ProvideAbstract
import com.example.randomuserapp.core.di.ProvideViewModel
import com.example.randomuserapp.user.data.UserRepository
import com.example.randomuserapp.user.presentation.UserViewModel
import com.google.gson.Gson

class UserModule(private val core: Core) : Module<UserViewModel> {

    override fun viewModel(): UserViewModel {
        val cacheDataSource = CacheDataSource.Base(core.saveUserInfo, Gson())
        return UserViewModel(UserRepository.Base(core.lastSavedScreen, cacheDataSource))
    }
}

class ProvideUserViewModel(
    core: Core,
    provideOther: ProvideViewModel
) : ProvideAbstract(core, provideOther, UserViewModel::class.java) {

    override fun module(): Module<out ViewModel> = UserModule(core)
}
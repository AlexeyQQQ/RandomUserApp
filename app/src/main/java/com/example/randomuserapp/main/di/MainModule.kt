package com.example.randomuserapp.main.di

import androidx.lifecycle.ViewModel
import com.example.randomuserapp.core.di.Core
import com.example.randomuserapp.core.di.Module
import com.example.randomuserapp.core.di.ProvideAbstract
import com.example.randomuserapp.core.di.ProvideViewModel
import com.example.randomuserapp.main.data.MainRepository
import com.example.randomuserapp.main.presentation.MainViewModel

class MainModule(private val core: Core) : Module<MainViewModel> {

    override fun viewModel(): MainViewModel {
        return MainViewModel(MainRepository.Base(core.lastSavedScreen))
    }
}

class ProvideMainViewModel(
    core: Core,
    provideOther: ProvideViewModel
) : ProvideAbstract(core, provideOther, MainViewModel::class.java) {

    override fun module(): Module<out ViewModel> = MainModule(core)
}
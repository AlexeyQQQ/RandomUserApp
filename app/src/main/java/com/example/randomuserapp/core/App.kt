package com.example.randomuserapp.core

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.randomuserapp.core.di.Core
import com.example.randomuserapp.core.di.ManageViewModels
import com.example.randomuserapp.core.di.ProvideViewModel
import com.example.randomuserapp.user.views.image.ProvidePicEngine

class App : Application(), ManageViewModels, ProvidePicEngine {

    private lateinit var factory: ManageViewModels
    private lateinit var core: Core

    override fun onCreate() {
        super.onCreate()
        core = Core(this)
        factory = ProvideViewModel.Factory(
            ProvideViewModel.Make(core)
        )
    }

    override fun <T : ViewModel> viewModel(clazz: Class<T>): T {
        return factory.viewModel(clazz)
    }

    override fun clear(clazz: Class<out ViewModel>) {
        factory.clear(clazz)
    }

    override fun engine(): PicEngine {
        return if (core.runUiTest) PicEngine.Mock() else PicEngine.Base()
    }
}
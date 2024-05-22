package com.example.randomuserapp.core

import android.app.Application
import androidx.lifecycle.ViewModel

class App : Application(), ManageViewModels {

    private lateinit var factory: ManageViewModels

    override fun onCreate() {
        super.onCreate()
    }

    override fun <T : ViewModel> viewModel(clazz: Class<T>): T {
        return factory.viewModel(clazz)
    }

    override fun clear(clazz: Class<out ViewModel>) {
        factory.clear(clazz)
    }
}
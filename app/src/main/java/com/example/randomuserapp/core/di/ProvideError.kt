package com.example.randomuserapp.core.di

import androidx.lifecycle.ViewModel

class ProvideError : ProvideViewModel {

    override fun <T : ViewModel> viewModel(clazz: Class<T>): T {
        throw IllegalStateException("unknown viewModel $clazz go and add it to ProvideViewModel.Make")
    }
}